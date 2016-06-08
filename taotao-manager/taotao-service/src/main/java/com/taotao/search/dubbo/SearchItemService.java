package com.taotao.search.dubbo;

import java.util.List;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;

public interface SearchItemService {
	
	public SearchResult search(String queryString,int page,int rows);
	public TaotaoResult insert(SearchItem item);
	public TaotaoResult delete(Long id);
	public TaotaoResult deleteItems(List<String> ids);
}
