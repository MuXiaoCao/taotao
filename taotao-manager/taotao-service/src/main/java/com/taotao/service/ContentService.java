package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

public interface ContentService {

	public TaotaoResult insertContent(TbContent content);
	
	public EasyUIDataGridResult getContentList(Long categoryId,int page,int rows);

	public TaotaoResult deleteContentByIds(List<Long> ids);

	public TaotaoResult updateContent(TbContent content);
	
}
