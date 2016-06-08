package com.taotao.search.dubbo;

import java.util.List;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;

/**
 * <p>Description:搜索服务提供接口</p>
 * <p>SearchItemService.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年6月6日 下午10:38:22
 * @version: 1.0
 */
public interface SearchItemService {
	public SearchResult search(String queryString,int page,int rows);
	public TaotaoResult insert(SearchItem item);
	public TaotaoResult delete(Long id);
	public TaotaoResult deleteItems(List<String> ids);
}
