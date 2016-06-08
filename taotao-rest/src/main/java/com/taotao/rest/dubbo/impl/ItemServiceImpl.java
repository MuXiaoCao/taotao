package com.taotao.rest.dubbo.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.utils.JsonUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.rest.dubbo.RestItemService;
import com.taotao.rest.service.JedisClientService;

/**
 * <p>
 * Description:rest有关商品的dubbo服务实现类,且有缓存机制
 * </p>
 * <p>
 * ItemServiceImpl.java
 * </P>
 * <p>
 * 个人主页：www.muxiaocao.cn/me
 * </p>
 * 
 * @author 木小草 @date： 2016年6月7日 下午8:46:35
 * @version: 1.0
 */
public class ItemServiceImpl implements RestItemService {

	@Autowired
	private TbItemMapper itemMapper;

	@Autowired
	private TbItemDescMapper itemDestMapper;

	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	// 缓存操作对象
	@Autowired
	private JedisClientService jedisClientService;

	/**
	 * redis中关于商品信息key 为了便于分组使用：的命名方式 例如：REDIS_ITEM:ITEM_BASE_INFO:{itemId}
	 */
	// 商品key
	@Value("${REDIS_ITEM_KEY}")
	private String REDIS_ITEM_KEY;
	// 商品基本信息key
	@Value("${ITEM_BASE_INFO_KEY}")
	private String ITEM_BASE_INFO_KEY;
	// 商品描述信息
	@Value("${ITEM_DESC_KEY}")
	private String ITEM_DESC_KEY;
	// 商品参数信息
	@Value("${ITEM_PARAM_KEY}")
	private String ITEM_PARAM_KEY;

	// redis中关于商品的过去时间key
	@Value("${ITEM_EXPIRE_SECOND}")
	private int ITEM_EXPIRE_SECOND;

	@Override
	public TbItem getItemById(Long id) {
		// 查询缓存，如果有缓存就直接返回，且更新缓存过期时间
		// 在redis中设置过期时间只能应用在key中，而hash中的项是不能设置过期时间的
		try {
			String itemJson = jedisClientService.get(REDIS_ITEM_KEY + ":" +id + ":"+ ITEM_BASE_INFO_KEY);
			if (StringUtils.isNoneBlank(itemJson)) {
				jedisClientService.expire(REDIS_ITEM_KEY + ":" +id + ":"+ ITEM_BASE_INFO_KEY, ITEM_EXPIRE_SECOND);
				// 需要将json转化为java对象
				return JsonUtils.jsonToPojo(itemJson, TbItem.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbItem item = itemMapper.selectByPrimaryKey(id);

				
		// 向redis中添加缓存
		// 添加缓存的原则是不应用业务逻辑
		try {
			jedisClientService.set(REDIS_ITEM_KEY + ":" +id + ":"+ ITEM_BASE_INFO_KEY, JsonUtils.objectToJson(item));
			// 设置过期时间
			jedisClientService.expire(REDIS_ITEM_KEY + ":" +id + ":"+ ITEM_BASE_INFO_KEY, ITEM_EXPIRE_SECOND);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public TbItemDesc getItemDescById(Long id) {
		// 查询缓存，如果有缓存就直接返回，且更新缓存过期时间
		// 在redis中设置过期时间只能应用在key中，而hash中的项是不能设置过期时间的
		try {
			String itemJson = jedisClientService.get(REDIS_ITEM_KEY + ":" + id+ ":" + ITEM_DESC_KEY );
			if (StringUtils.isNoneBlank(itemJson)) {
				jedisClientService.expire(REDIS_ITEM_KEY + ":" + id + ":" + ITEM_DESC_KEY, ITEM_EXPIRE_SECOND);
				// 需要将json转化为java对象
				return JsonUtils.jsonToPojo(itemJson, TbItemDesc.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbItemDesc item = itemDestMapper.selectByPrimaryKey(id);

		// 向redis中添加缓存
		// 添加缓存的原则是不应用业务逻辑
		try {
			jedisClientService.set(REDIS_ITEM_KEY + ":" + id + ":" + ITEM_DESC_KEY, JsonUtils.objectToJson(item));
			// 设置过期时间
			jedisClientService.expire(REDIS_ITEM_KEY + ":" + id + ":" + ITEM_DESC_KEY, ITEM_EXPIRE_SECOND);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}

	@Override
	public TbItemParamItem getItemPatamItemById(Long id) {
		// 查询缓存，如果有缓存就直接返回，且更新缓存过期时间
		// 在redis中设置过期时间只能应用在key中，而hash中的项是不能设置过期时间的
		try {
			String itemJson = jedisClientService.get(REDIS_ITEM_KEY + ":" + id+ ":" + ITEM_PARAM_KEY );
			if (StringUtils.isNoneBlank(itemJson)) {
				jedisClientService.expire(REDIS_ITEM_KEY + ":" + id+ ":" + ITEM_PARAM_KEY, ITEM_EXPIRE_SECOND);
				// 需要将json转化为java对象
				return JsonUtils.jsonToPojo(itemJson, TbItemParamItem.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		TbItemParamItem item = itemParamItemMapper.selectByPrimaryKey(id);

		// 向redis中添加缓存
		// 添加缓存的原则是不应用业务逻辑
		try {
			jedisClientService.set(REDIS_ITEM_KEY + ":" + id+ ":" + ITEM_PARAM_KEY, JsonUtils.objectToJson(item));
			// 设置过期时间
			jedisClientService.expire(REDIS_ITEM_KEY + ":" + id+ ":" + ITEM_PARAM_KEY, ITEM_EXPIRE_SECOND);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;
	}
}
