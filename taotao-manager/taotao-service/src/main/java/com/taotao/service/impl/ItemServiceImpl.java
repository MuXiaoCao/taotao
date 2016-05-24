package com.taotao.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.mapper.TbItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.service.ItemService;

/**
 * <p>Description: 商品有关的service</p>
 * <p>ItemServiceImpl.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年5月22日 上午9:56:51
 * @version: 1.0
 */
@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private TbItemMapper itemMapper;
	

	@Override
	public TbItem getItemById(Long itemId) {
		// 创建查询样例类
		TbItemExample example = new TbItemExample();
		// 创建查询条件
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(itemId);
		// 查询
		List<TbItem> list = itemMapper.selectByExample(example);
		// 返回结果
		TbItem item = null;
		if (list != null && list.size() > 0) {
			item = list.get(0);
		}
		return item;
	}


	@Override
	public EasyUIDataGridResult getItemList(int page, int rows) {
		// 分页处理
		PageHelper.startPage(page, rows);
		// 执行查询
		TbItemExample example = new TbItemExample();
		List<TbItem> list = itemMapper.selectByExample(example);
		// 得到分页结果
		PageInfo<TbItem> pageInfo = new PageInfo<>(list);
		// 封装返回结果
		EasyUIDataGridResult result = new EasyUIDataGridResult();
		result.setTotal(pageInfo.getTotal());
		result.setRows(pageInfo.getList());
		return result;
	}
	
}	
