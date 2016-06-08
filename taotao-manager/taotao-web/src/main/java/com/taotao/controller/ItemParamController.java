package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ItemParamService;

/**
 * <p>Description:商品参数规格控制层</p>
 * <p>ItemParamController.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年5月25日 下午8:01:15
 * @version: 1.0
 */
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	
	@Autowired
	private ItemParamService itemParamService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	@ResponseBody
	public EasyUIDataGridResult getItemParamList(Integer page,Integer rows) {
		EasyUIDataGridResult result = itemParamService.getItemParamList(page, rows);
		return result;
	}
	
	@RequestMapping("/query/itemcatid/{item_param_id}")
	@ResponseBody
	public TaotaoResult getItemCatByCid(@PathVariable Long item_param_id) {
		TaotaoResult result = itemParamService.getItemParamByCid(item_param_id);
		return result;
	}
	
	@RequestMapping(value="/save/{cid}",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult setItemParam(@PathVariable Long cid,String paramData) {
		TaotaoResult result = itemParamService.insertItemParam(cid, paramData);
		return result;
	}
	
	@RequestMapping("/{itemId}")
	public String showItemParam(@PathVariable Long itemId,Model model) {
		String html = itemParamService.getItemParamHtml(itemId);
		model.addAttribute("myhtml",html);
		return "itemparam";
	}
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteItemParam(@RequestParam List<Long> ids){
		TaotaoResult result = itemParamService.deleteItemParam(ids);
		return result;
	}
}
