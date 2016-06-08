package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;

/**
 * <p>Description:商品有关的控制层</p>
 * <p>ItemController.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年5月22日 上午10:12:09
 * @version: 1.0
 */
@Controller
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	
	
		
	@RequestMapping("/item/{itemId}")
	@ResponseBody
	private TbItem getItemById(@PathVariable Long itemId) {
		TbItem item = itemService.getItemById(itemId);
		return item;
	}
	
	@RequestMapping("/item/list")
	@ResponseBody
	public EasyUIDataGridResult getItemList(Integer page,Integer rows) {
		EasyUIDataGridResult result = itemService.getItemList(page, rows);
		return result;
	}
	
	@RequestMapping(value="/item/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult createItem(TbItem item,String desc,String itemParams,String cname) {
		TaotaoResult result = itemService.createItem(item, desc,itemParams,cname);
		
		return result;
	}
	
	@RequestMapping(value="/rest/item/delete",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteItem(@RequestParam List<Long> ids) {
		TaotaoResult result = itemService.deleteItems(ids);
		
		
		return result;
	}
	
	//GET http://127.0.0.1:8080/rest/page/item-edit?_=1465298951313
	@RequestMapping(value="/rest/page/item-edit")
	@ResponseBody
	public TaotaoResult editItem() {
		
		return TaotaoResult.ok();
	}
}
