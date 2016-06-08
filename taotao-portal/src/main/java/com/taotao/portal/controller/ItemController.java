package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.PortalItem;
import com.taotao.portal.service.ItemService;


/**
 * <p>Description:商品有关的控制层</p>
 * <p>ItemController.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年6月7日 下午9:42:22
 * @version: 1.0
 */
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/item/{itemId}")
	public String showItemInfo(@PathVariable Long itemId,Model model) {
		PortalItem item = itemService.getItemById(itemId);
		model.addAttribute("item", item);
		return "item";
	}
	
	@RequestMapping("/item/param/{itemId}")
	@ResponseBody
	public TbItemParamItem showItemParamInfo(@PathVariable Long itemId){
		TbItemParamItem itemParamItem = itemService.getItemParamItemById(itemId);
		return itemParamItem;
	}
	
	@RequestMapping("/item/desc/{itemId}")
	@ResponseBody
	public TbItemDesc showItemDescInfo(@PathVariable Long itemId){
		TbItemDesc TbItemDesc = itemService.getItemDescById(itemId);
		return TbItemDesc;
	}
}
