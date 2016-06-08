package com.taotao.rest.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.taotao.common.utils.JsonUtils;
import com.taotao.rest.pojo.ItemCatResult;
import com.taotao.rest.service.ItemCatService;
import com.taotao.rest.service.JedisClientService;

/**
 * <p>Description:前端使用jsonp进行访问，所以不经过portal。商品目录相关的控制层</p>
 * <p>ItemCatController.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年6月1日 下午1:41:55
 * @version: 1.0
 */
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

	// 本地商品条目服务对象
	@Autowired
	private ItemCatService itemCatService;
	
	// redis缓存对象
	@Autowired
	private JedisClientService contentService;
	
	// 商品条目缓存在redis中的key
	@Value("${REDIS_ITEMCAT_KEY}")
	private String REDIS_ITEMCAT_KEY;

	/**
	 * 方法一：设置相应数据格式为application/json，并且设置charset=utf-8
	 * 加入了redis缓存
	 * @param callback
	 * @return
	 */
	@RequestMapping(value="/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemCatList(String callback) {
		
		// 首先查询缓存，如果redis中存在，则直接返回，不需要查询数据库
		String redis_value = contentService.get(REDIS_ITEMCAT_KEY);
		if (!StringUtils.isBlank(redis_value)) {
			return redis_value;
		}
		
		ItemCatResult result = itemCatService.getItemCatList();
		String json = null;
		if (StringUtils.isBlank(callback)) {
			// 需要把result转换为字符串
			json = JsonUtils.objectToJson(result);
		}else {
			// 如果字符串不为空，需要支持jsonp调用
			json = callback + "(" + JsonUtils.objectToJson(result) + ")";
		}
		
		// 添加缓存
		contentService.set(REDIS_ITEMCAT_KEY, json);
		return json;
	}
	
	/**
	 * 方法二：使用mappingjacksonvalue（spring4.1以上版本）
	 * @param callback
	 * @return
	 */
	/*@RequestMapping(value="/list")
	@ResponseBody
	public Object getItemCatList(String callback) {
		ItemCatResult result = itemCatService.getItemCatList();
		if (StringUtils.isBlank(callback)) {
			return result;
		}
		// 如果字符串不为空，需要支持jsonp调用
		MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
		mappingJacksonValue.setJsonpFunction(callback);
		return mappingJacksonValue;
	}*/
	
}
