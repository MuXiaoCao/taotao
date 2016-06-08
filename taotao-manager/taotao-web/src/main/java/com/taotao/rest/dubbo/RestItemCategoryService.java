package com.taotao.rest.dubbo;

import com.taotao.common.pojo.TaotaoResult;

/**
 * <p>Description:rest服务器提供的关于商品条目服务（dubbo服务）</p>
 * <p>RestItemParamService.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年6月5日 下午1:52:46
 * @version: 1.0
 */
public interface RestItemCategoryService {
	/**
	 * 同步redis缓存服务器中的商品条目
	 * @return
	 */
	public TaotaoResult sysncItemParam();
	
}
