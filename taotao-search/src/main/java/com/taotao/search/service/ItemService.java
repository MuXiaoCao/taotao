package com.taotao.search.service;

import java.util.List;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.TaotaoResult;

public interface ItemService {
	
	public TaotaoResult importItems() throws Exception;
	
	public TaotaoResult importItem(SearchItem item) throws Exception;
	
	public TaotaoResult deleteItem(Long id) throws Exception;
	
	public TaotaoResult deleteItems(List<String> ids) throws Exception;
}
