package com.taotao.rest.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemCatExample.Criteria;
import com.taotao.rest.pojo.CatNode;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;

/**
 * <p>Description:和目录有关的服务层</p>
 * <p>ItemCatServiceImpl.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年6月1日 下午1:14:30
 * @version: 1.0
 */
@Service
public class ItemCatServiceImpl implements ItemCatService {

	@Autowired
	private TbItemCatMapper itemCatMapper;
	
	@Override
	public ItemCatResult getItemCatList() {
		// 调用递归方法查询商品分类列表
		List<Object> catList = getItemCatList(0L);
		// 返回结果
		ItemCatResult result = new ItemCatResult();
		result.setData(catList);
		return result;
	}
	
	private List<Object> getItemCatList(Long parentId) {
		// 根据parentId查询目录列表
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List<Object> resultList = new ArrayList<>();
		int index = 0;
		for (TbItemCat tbItemCat : list) {
			if (index >= 14) {
				break;
			}
			CatNode node = new CatNode();
			// 如果是父节点
			if (tbItemCat.getIsParent()) {
				node.setUrl("/products/" + tbItemCat.getId() + ".html");
				// 如果当前结点为最上层节点
				if (tbItemCat.getParentId() == 0) {
					node.setName("<a href='/products/"+tbItemCat.getId()+".html'>"+tbItemCat.getName()+"</a>");
					// 前端最多展示14个最上乘节点,所以使用计数器限制查询个数
					index++;
				}else {
					node.setName(tbItemCat.getName());
				}
				node.setItems(getItemCatList(tbItemCat.getId()));
				resultList.add(node);
			}else {
				// 如果是叶子结点
				String item = "/priducts/" + tbItemCat.getId() + ".html|" + tbItemCat.getName();
				resultList.add(item);
			}
		}
		return resultList;
	}

}
