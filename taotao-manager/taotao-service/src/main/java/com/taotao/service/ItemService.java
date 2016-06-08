package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	
	public TbItem getItemById(Long itemId);
	public EasyUIDataGridResult getItemList(int page,int rows);
	public TaotaoResult createItem(TbItem item,String desc, String paramData,String cname);
	
	//public TaotaoResult updateItem(TbItem item);
	public TaotaoResult deleteItems(List<Long> ids);
}
