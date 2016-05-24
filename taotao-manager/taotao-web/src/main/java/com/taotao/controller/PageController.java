package com.taotao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Description:页面展示有关的控制层</p>
 * <p>PageController.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年5月22日 上午10:30:28
 * @version: 1.0
 */
@Controller
public class PageController {
	
	@RequestMapping("/")
	public String showIndex() {
		return "index";
	}
	
	@RequestMapping("/{pageName}")
	public String showPage(@PathVariable String pageName) {
		return pageName;
	}
}
