package com.taotao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.pojo.EasyUIDataGridResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;
import com.taotao.rest.dubbo.RestContentService;
import com.taotao.service.ContentService;

/**
 * <p>Description:内容管理controller</p>
 * <p>ContentController.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年6月1日 下午6:45:46
 * @version: 1.0
 */
@Controller
@RequestMapping("/content")
public class ContentController {

	@Autowired
	private ContentService contentService;
	
	// 缓存同步处理
	@Autowired
	private RestContentService restContentService;
	
	@RequestMapping("/query/list")
	@ResponseBody
	public EasyUIDataGridResult getContentList(long categoryId,int page,int rows) {
		EasyUIDataGridResult result = contentService.getContentList(categoryId, page, rows);
		return result;
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteContentByIds(@RequestParam List<Long> ids) {
		
		TaotaoResult result = contentService.deleteContentByIds(ids);
		// 添加缓存
		if (result.getData() instanceof Long) {
			restContentService.sysncContent((Long)result.getData());
		}
		return result;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult saveContent(TbContent content) {
		TaotaoResult result = contentService.insertContent(content);
		if (result.getMsg().equals("OK")) {
			// 添加缓存
			restContentService.sysncContent(content.getCategoryId());
		}
		return result;
	}
	
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ResponseBody
	public TaotaoResult editContent(TbContent content) {
		TaotaoResult result = contentService.updateContent(content);
		if (result.getMsg().equals("OK")) {
			// 添加缓存
			restContentService.sysncContent(content.getCategoryId());
		}
		return result;
	}
}
