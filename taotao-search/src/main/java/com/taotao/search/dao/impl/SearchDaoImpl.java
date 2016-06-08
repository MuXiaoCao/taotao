package com.taotao.search.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import com.taotao.search.dao.SearchDao;

/**
 * <p>Description:通过solr查询</p>
 * <p>SearchDaoImpl.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年6月6日 下午8:40:44
 * @version: 1.0
 */
@Repository
public class SearchDaoImpl implements SearchDao{
	
	@Autowired
	private SolrClient solrServer;

	@Override
	public SearchResult search(SolrQuery solrQuery) throws Exception {
		// 执行查询
		QueryResponse response = solrServer.query(solrQuery);
		// 取得查询结果列表
		SolrDocumentList solrDocumentList = response.getResults();
		List<SearchItem> list = new ArrayList<>();
		for (SolrDocument solrDocument : solrDocumentList) {
			// 创建一个searchitem对象
			SearchItem item = new SearchItem();
			item.setCategory_name((String)solrDocument.get("item_category_name"));
			item.setId((String)solrDocument.get("id"));
			
			item.setImages((String)solrDocument.get("item_image"));
			
			item.setPrice((Long)solrDocument.get("item_price"));
			item.setSell_point((String)solrDocument.get("item_sell_poing"));
			// 取高亮显示
			Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();
			List<String> ll = highlighting.get(solrDocument.get("id")).get("item_title");
			String itemTitle = "";
			if (ll != null && list.size()>0) {
				itemTitle=ll.get(0);
			}else {
				itemTitle = (String)solrDocument.get("item_title");
			}
			item.setTitle(itemTitle);
			list.add(item);
		}
		SearchResult result = new SearchResult();
		result.setItemList(list);
		// 查询结果数量
		result.setRecordCount(solrDocumentList.getNumFound());
		return result;
	}
}
