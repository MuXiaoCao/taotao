package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;

public interface ItemParamService {
	
	public EasyUIDataGridResult getItemParamList(int page,int rows); 
	public TaotaoResult getItemParamByCid(Long cid);
	public TaotaoResult insertItemParam(Long cid,String paramdata);
	public String getItemParamHtml(Long itemId);
	public TaotaoResult deleteItemParam(List<Long> ids);
}
