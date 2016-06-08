package com.taotao.search.dubbo.impl;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.dao.SearchDao;
import com.taotao.search.dubbo.SearchItemService;
import com.taotao.search.service.ItemService;

/**
 * <p>Description:</p>
 * <p>SearchItemServiceImpl.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年6月6日 下午9:08:52
 * @version: 1.0
 */
public class SearchItemServiceImpl implements SearchItemService {
	
	@Autowired
	private SearchDao searchDao;
	
	@Autowired
	private ItemService itemService;
	
	@Override
	public SearchResult search(String queryString, int page, int rows) {
		// 创建查询条件
		SolrQuery query = new SolrQuery();
		// 设置查询条件
		query.setQuery(queryString);
		// 设置分页条件
		query.setStart((page-1)*rows);
		query.setRows(rows);
		// 设置默认搜索域
		query.set("df", "item_title");
		// 设置高亮
		query.setHighlight(true);
		query.addHighlightField("item_title");
		query.setHighlightSimplePre("<font class=\"skcolor_ljg\">");
		query.setHighlightSimplePost("</font>");
		
		// 执行查询
		SearchResult result = null;
		try {
			result = searchDao.search(query);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 计算总页数
		Long count = result.getRecordCount();
		int pageCount = (int)(count / rows);
		if (count % rows > 0) {
			pageCount++;
		}
		result.setPageCount(pageCount);
		result.setCurPage(page);
		return result;
	}

	@Override
	public TaotaoResult insert(SearchItem item) {
		TaotaoResult result = null;
		try {
			result = itemService.importItem(item);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
		return result;
	}

	@Override
	public TaotaoResult delete(Long id) {
		TaotaoResult result = null;
		try {
			result = itemService.deleteItem(id);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
		return result;
	}

	@Override
	public TaotaoResult deleteItems(List<String> ids) {
		TaotaoResult result = null;
		try {
			result = itemService.deleteItems(ids);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
		return result;
	}
}
