package com.taotao.portal.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.PortalItem;
import com.taotao.portal.service.ItemService;
import com.taotao.rest.dubbo.RestItemService;

/**
 * <p>Description:portal有关商品的服务层</p>
 * <p>ItemServiceImpl.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年6月7日 下午9:50:19
 * @version: 1.0
 */
@Service
public class ItemServiceImpl implements ItemService{

	@Autowired
	private RestItemService restItemService;
	
	@Override
	public PortalItem getItemById(Long id) {
		TbItem tbItem = restItemService.getItemById(id);
		PortalItem item = new PortalItem(tbItem);
		return item;
	}

	@Override
	public TbItemDesc getItemDescById(Long id) {
		TbItemDesc itemDesc = restItemService.getItemDescById(id);
		return itemDesc;
	}

	@Override
	public TbItemParamItem getItemParamItemById(Long id) {
		TbItemParamItem itemParamItem = restItemService.getItemPatamItemById(id);
		return itemParamItem;
	}
	
}
