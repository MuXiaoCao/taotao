package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.pojo.TbContentCategoryExample.Criteria;
import com.taotao.service.ContentCategoryService;

/**
 * <p>Description:内容分类管理列表service</p>
 * <p>ContentCategoryService.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年6月1日 下午3:55:54
 * @version: 1.0
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService{

	@Autowired
	private TbContentCategoryMapper contentCategoryMapper;

	@Override
	public List<EasyUITreeNode> getContentCatList(Long parentId) {
		// 根据parentid查询子节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		// 生成返回值treenode列表
		List<EasyUITreeNode> result = new ArrayList<>();
		for (TbContentCategory tbContentCategory : list) {
			EasyUITreeNode node = new EasyUITreeNode();
			node.setId(tbContentCategory.getId());
			node.setState(tbContentCategory.getIsParent()? "closed":"open");
			node.setText(tbContentCategory.getName());
			result.add(node);
		}
		
		return result;
	}

	@Override
	public TaotaoResult insertCategory(Long parentId, String name) {

		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		contentCategory.setParentId(parentId);
		contentCategory.setStatus(1);
		contentCategory.setSortOrder(1);
		// 插入数据
		contentCategoryMapper.insert(contentCategory);
		
		// 取返回的主键
		Long id = contentCategory.getId();

		// 判断父节点的isparent属性，如果是叶子节点（false），则其更新为true
		TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parentNode.getIsParent()) {
			// 更新父节点
			parentNode.setIsParent(true);
			contentCategoryMapper.updateByPrimaryKey(parentNode);
		}
		return TaotaoResult.ok(id);
	}

	@Override
	public TaotaoResult updateCategory(Long id, String name) {
		TbContentCategory category = new TbContentCategory();
		category.setId(id);
		category.setName(name);
		int result = contentCategoryMapper.updateByPrimaryKeySelective(category);
		if (result != 0) {
			return TaotaoResult.ok();
		}else {
			return TaotaoResult.build(200, "修改失败");
		}
	}

	/**
	 * 删除节点需要注意一下几点：
	 * 1. 如果删除的是叶子节点，则可以直接删除，但是需要判断其父节点中是否还有子节点，如果没有，需要更高父节点为叶子节点
	 * 2. 如果删除的不是叶子节点，则需要递归删除它的所有子节点
	 */
	@Override
	public TaotaoResult deleteCategory(Long id) {
		// 获得该节点的对象
		TbContentCategory node = contentCategoryMapper.selectByPrimaryKey(id);
		// 获得父节点的对象
		TbContentCategory parentNode = contentCategoryMapper.selectByPrimaryKey(node.getParentId());
		// 如果是叶子节点
		if (!parentNode.getIsParent()) {
			// 直接删除
			contentCategoryMapper.deleteByPrimaryKey(id);
			// 判断父节点是否还有叶子节点
			TbContentCategoryExample example = new TbContentCategoryExample();
			Criteria criteria = example.createCriteria();
			criteria.andParentIdEqualTo(parentNode.getId());
			List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
			// 如果该父节点没有子节点了，则需要把它更新为叶子节点
			if (list.isEmpty()) {
				parentNode.setIsParent(false);
			}
		}else {
			// 如果该节点不是叶子节点，需要递归删除它的所有子节点
			deleteAllNodeByParentId(id);
		}
		return TaotaoResult.ok(id);
	}

	/**
	 * 通过节点的id递归删除该节点的所有子节点
	 * @param id
	 */
	private void deleteAllNodeByParentId(Long id) {
		
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(id);
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		if (!list.isEmpty()) {
			for (TbContentCategory tbContentCategory : list) {
				deleteAllNodeByParentId(tbContentCategory.getId());
			}
		}
		contentCategoryMapper.deleteByPrimaryKey(id);
	}
	
}
