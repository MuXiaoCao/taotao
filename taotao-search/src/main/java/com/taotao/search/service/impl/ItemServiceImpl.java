package com.taotao.search.service.impl;

import java.util.List;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.mapper.ItemMapper;
import com.taotao.search.service.ItemService;

/**
 * <p>Description:向solr中添加数据的service</p>
 * <p>ItemServiceImpl.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年6月6日 下午1:47:10
 * @version: 1.0
 */
@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private ItemMapper itemMapper;
	
	@Autowired
	private SolrClient httpSolrServer;
	
	@Override
	public TaotaoResult importItems() throws Exception {
		
		List<SearchItem> itemList = itemMapper.getItemList();
		for (SearchItem item : itemList) {
			SolrInputDocument document = new SolrInputDocument();
			document.addField("id", item.getId());
			document.addField("item_title", item.getTitle());
			document.addField("item_sell_point", item.getSell_point());
			document.addField("item_price", item.getPrice());
			document.addField("item_image", item.getImages());
			document.addField("item_category_name", item.getCategory_name());
			document.addField("item_desc", item.getItem_desc());
			httpSolrServer.add(document);
		}
		httpSolrServer.commit();
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult importItem(SearchItem item) throws Exception {
		
		SolrInputDocument document = new SolrInputDocument();
		document.addField("id", item.getId());
		document.addField("item_title", item.getTitle());
		document.addField("item_sell_point", item.getSell_point());
		document.addField("item_price", item.getPrice());
		document.addField("item_image", item.getImages());
		document.addField("item_category_name", item.getCategory_name());
		document.addField("item_desc", item.getItem_desc());
		httpSolrServer.add(document);
		httpSolrServer.commit();
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteItem(Long id) throws Exception {
		
		httpSolrServer.deleteByQuery("item_id","id:" + id);
		
		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteItems(List<String> ids) throws Exception {
		
		for (String id : ids) {
			deleteItem(Long.parseLong(id));
		}
		
		return TaotaoResult.ok();
	}
	
}
