package com.taotao.rest.dubbo;

import com.taotao.common.pojo.SearchItem;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;

/**
 * <p>Description:rest提供的有关商品的dubbo服务</p>
 * <p>RestItemService.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年6月7日 下午8:39:56
 * @version: 1.0
 */
public interface RestItemService {
	// 根据商品id获取商品基本信息
	public TbItem getItemById(Long id);
	// 根据商品id获取商品详细信息
	public TbItemDesc getItemDescById(Long id);
	// 根据商品id获取商品参数信息
	public TbItemParamItem getItemPatamItemById(Long id);
	
}
