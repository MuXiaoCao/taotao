package com.taotao.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtil;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemExample;
import com.taotao.pojo.TbItemExample.Criteria;
import com.taotao.search.dubbo.SearchItemService;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.service.ItemService;

/**
 * <p>
 * Description: 商品有关的service
 * </p>
 * <p>
 * ItemServiceImpl.java
 * </P>
 * <p>
 * 个人主页：www.muxiaocao.cn/me
 * </p>
 * 
 * @author 木小草 @date： 2016年5月22日 上午9:56:51
 * @version: 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	private TbItemMapper itemMapper;
	@Autowired
	private TbItemDescMapper itemDescMapper;
	@Autowired
	private TbItemParamItemMapper itemParamItemMapper;

	/**
	 * 同步search索引表
	 */
	@Autowired
	private SearchItemService searchItemService;

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

	@Override
	public TaotaoResult createItem(TbItem item, String desc, String paramData, String cname) {

		// 生成商品id
		long itemId = IDUtil.getItemId();
		// 补全tbitem属性
		item.setId(itemId);
		// 商品状态：1-正常 2-下架 3-删除
		item.setStatus((byte) 1);
		// 创建时间和更新时间
		Date date = new Date();
		item.setCreated(date);
		item.setUpdated(date);
		// 插入商品表
		itemMapper.insert(item);
		// 商品描述
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemId(itemId);
		itemDesc.setItemDesc(desc);
		itemDesc.setCreated(date);
		itemDesc.setUpdated(date);
		// 插入商品描述数据
		itemDescMapper.insert(itemDesc);

		TbItemParamItem itemParamItem = new TbItemParamItem();
		itemParamItem.setCreated(new Date());
		itemParamItem.setItemId(itemId);
		itemParamItem.setParamData(paramData);
		itemParamItem.setUpdated(new Date());
		itemParamItemMapper.insert(itemParamItem);
		// 同步索引库
		SearchItem searchItem = new SearchItem();
		searchItem.setCategory_name(cname);
		searchItem.setId(item.getId() + "");
		searchItem.setImages(item.getImage());
		searchItem.setItem_desc(desc);
		searchItem.setPrice(item.getPrice());
		searchItem.setSell_point(item.getSellPoint());
		searchItem.setTitle(item.getTitle());
		searchItemService.insert(searchItem);

		return TaotaoResult.ok();
	}

	@Override
	public TaotaoResult deleteItems(List<Long> ids) {
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);
		itemMapper.deleteByExample(example);

		// 同步solr服务器的索引库
		List<String> items = new ArrayList<>();
		for (Long id : ids) {
			items.add(id + "");
		}
		searchItemService.deleteItems(items);

		return TaotaoResult.ok();
	}

}
