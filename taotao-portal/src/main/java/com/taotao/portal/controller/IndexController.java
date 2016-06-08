package com.taotao.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.taotao.portal.service.AdNodeService;

/**
 * <p>Description:首页访问controller</p>
 * <p>IndexController.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年5月26日 上午10:48:25
 * @version: 1.0
 */
@Controller
public class IndexController {
	
	
	// 本地对象，前台轮播图列表对象
	@Autowired
	private AdNodeService adNodeService;
	
	@RequestMapping("/index")
	public String showIndex(Model model) {
		// 取大广告位内容
		String result = adNodeService.getAdNode1List();
		// 传递给页面
		model.addAttribute("ad1",result);
		return "index";
	}
	
}
