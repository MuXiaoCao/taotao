package com.taotao.service;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
	
	public TbItem getItemById(Long itemId);
	
	public EasyUIDataGridResult getItemList(int page,int rows);
}
