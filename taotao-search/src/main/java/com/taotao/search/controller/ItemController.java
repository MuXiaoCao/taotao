package com.taotao.search.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * <p>Description:solr添加数据</p>
 * <p>ItemController.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年6月6日 下午4:11:36
 * @version: 1.0
 */
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.search.service.ItemService;
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/importAll")
	@ResponseBody
	public TaotaoResult importAll() {
		TaotaoResult result = null;
		try {
			result = itemService.importItems();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtils.getStackTrace(e));
		}
	}
	
}
