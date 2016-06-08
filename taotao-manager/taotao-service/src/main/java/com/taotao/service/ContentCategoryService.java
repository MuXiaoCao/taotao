package com.taotao.service;

import java.util.List;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

public interface ContentCategoryService {
	
	public List<EasyUITreeNode> getContentCatList(Long parentId);
	
	public TaotaoResult insertCategory(Long parentId,String name);
	
	public TaotaoResult updateCategory(Long id,String name);
	
	public TaotaoResult deleteCategory(Long id);
}
