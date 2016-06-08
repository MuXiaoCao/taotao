package com.taotao.rest.dubbo.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.rest.dubbo.RestContentService;
import com.taotao.rest.service.JedisClientService;

/**
 * <p>
 * Description:轮播图的内容提供service
 * </p>
 * <p>
 * ContentServiceImpl.java
 * </P>
 * <p>
 * 个人主页：www.muxiaocao.cn/me
 * </p>
 * 
 * @author 木小草 @date： 2016年6月1日 下午10:24:36
 * @version: 1.0
 */
public class ContentServiceImpl implements RestContentService {

	// 轮播图数据库操作对象
	@Autowired
	private TbContentMapper contentMapper;

	// 缓存操作对象
	@Autowired
	private JedisClientService jedisClientService;

	// 大广告位缓存中的key
	@Value("${REDIS_CONTENT_KEY}")
	private String REDIS_CONTENT_KEY;

	@Override
	public List<TbContent> getContentList(Long cid) {
		
		// 首先在redis缓存中查询
		try {
			String result = jedisClientService.hget(REDIS_CONTENT_KEY, cid+"");
			if (!StringUtils.isBlank(result)) {
				// 把json转换为list
				List<TbContent> list = JsonUtils.jsonToList(result, TbContent.class);
				return list;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(cid);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);

		// 返回结果之前，向缓存中添加数据
		try {
			// 为了规范key，可以使用hash
			// 定义一个保存内容的key，hash中每个项就是cid
			// value是list的json
			jedisClientService.hset(REDIS_CONTENT_KEY, cid + "", JsonUtils.objectToJson(list));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	// 同步大广告位缓存数据
	@Override
	public TaotaoResult sysncContent(Long cid) {
		try {
			jedisClientService.hdel(REDIS_CONTENT_KEY, cid + "");
			return TaotaoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
	}

	@Override
	public TaotaoResult sysncContentById(List<Long> ids) {
		try {
			for (Long id : ids) {
				TbContent content = contentMapper.selectByPrimaryKey(id);
				if (content!=null) {
					jedisClientService.hdel(REDIS_CONTENT_KEY, content.getCategoryId()+"");
					return TaotaoResult.ok();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
		return TaotaoResult.ok();
	}
}
