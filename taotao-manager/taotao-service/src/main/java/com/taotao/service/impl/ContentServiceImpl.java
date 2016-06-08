package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.pojo.TbContentExample.Criteria;
import com.taotao.service.ContentService;

/**
 * <p>
 * Description:内容管理Service
 * </p>
 * <p>
 * ContentServiceImpl.java
 * </P>
 * <p>
 * 个人主页：www.muxiaocao.cn/me
 * </p>
 * 
 * @author 木小草 @date： 2016年6月1日 下午3:17:06
 * @version: 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TbContentMapper contentMapper;

	@Override
	public TaotaoResult insertContent(TbContent content) {

		content.setCreated(new Date());
		content.setUpdated(new Date());
		// 插入数据
		contentMapper.insert(content);
		return TaotaoResult.ok();
	}

	/**
	 * 分页显示内容条目
	 */
	@Override
	public EasyUIDataGridResult getContentList(Long categoryId, int page, int rows) {

		// PageHelper.startPage(page, rows);
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		List<TbContent> list = contentMapper.selectByExample(example);

		// PageInfo<TbContent> info = new PageInfo<>(list);
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		// result.setTotal(info.getTotal());
		// result.setRows(info.getList());
		result.setRows(list);
		result.setTotal(list.size());
		return result;
	}

	@Override
	public TaotaoResult deleteContentByIds(List<Long> ids) {
		// 返回cid在result中，以便远端根据cid删除缓存
		Long cid = null;
		for (Long id : ids) {
			TbContent content = contentMapper.selectByPrimaryKey(id);
			if (content != null) {
				cid = content.getCategoryId();
				break;
			}
		}
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);
		contentMapper.deleteByExample(example);
		return TaotaoResult.ok(cid);
	}

	@Override
	public TaotaoResult updateContent(TbContent content) {
		
		content.setUpdated(new Date());
		contentMapper.updateByPrimaryKeySelective(content);
		return TaotaoResult.ok();
	}

}
