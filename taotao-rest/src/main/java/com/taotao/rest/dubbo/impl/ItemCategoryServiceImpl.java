package com.taotao.rest.dubbo.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.rest.dubbo.RestItemCategoryService;
import com.taotao.rest.service.JedisClientService;

public class ItemCategoryServiceImpl implements RestItemCategoryService {

	@Value("${REDIS_ITEMCAT_KEY}")
	private String REDIS_ITEMCAT_KEY;
	
	// 缓存操作对象
	@Autowired
	private JedisClientService jedisClientService;
	
	@Override
	public TaotaoResult sysncItemParam() {
		jedisClientService.delete(REDIS_ITEMCAT_KEY);
		return TaotaoResult.ok();
	}
}
