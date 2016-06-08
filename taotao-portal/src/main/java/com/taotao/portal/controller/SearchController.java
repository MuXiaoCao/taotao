package com.taotao.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taotao.common.pojo.SearchItem;
import com.taotao.common.pojo.SearchResult;
import com.taotao.portal.service.SearchService;

/**
 * <p>
 * Description:前台有关搜索的控制层
 * </p>
 * <p>
 * SearchController.java
 * </P>
 * <p>
 * 个人主页：www.muxiaocao.cn/me
 * </p>
 * 
 * @author 木小草 @date： 2016年6月7日 上午9:52:10
 * @version: 1.0
 */
@Controller
public class SearchController {

	@Autowired
	private SearchService searchService;

	@RequestMapping("/search")
	public String search(@RequestParam("q") String keyword, @RequestParam(defaultValue = "1") Integer page,
			@RequestParam(defaultValue = "60") Integer rows, Model model) {
		// get乱码处理
		try {
			keyword = new String(keyword.getBytes("iso8859-1"), "utf-8");
		} catch (Exception e) {
			keyword = "";
			e.printStackTrace();
		}
		SearchResult searchResult = searchService.search(keyword, page, rows);
		
		List<SearchItem> itemList = searchResult.getItemList();
		for (SearchItem searchItem : itemList) {
			System.err.println(searchItem.getImages());
		}
		// 参数传递给页面
		model.addAttribute("query", keyword);
		model.addAttribute("totalPages", searchResult.getPageCount());
		model.addAttribute("itemList", searchResult.getItemList());
		model.addAttribute("page", searchResult.getCurPage());
		
		// 返回逻辑视图
		return "search";
	}

}
