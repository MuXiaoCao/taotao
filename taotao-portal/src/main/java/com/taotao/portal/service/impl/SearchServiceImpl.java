package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.SearchResult;
import com.taotao.portal.service.SearchService;
import com.taotao.search.dubbo.SearchItemService;

/**
 * <p>Description:前台搜索服务，dubbo调用search服务器的search服务</p>
 * <p>SearchServiceImpl.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年6月7日 上午9:48:24
 * @version: 1.0
 */
@Service
public class SearchServiceImpl implements SearchService{

	@Autowired
	private SearchItemService itemService;
	
	@Override
	public SearchResult search(String keyword, int page, int rows) {

		SearchResult result = itemService.search(keyword, page, rows);
		return result;
	}
	
}
