package com.taotao.rest.dubbo;

import java.util.List;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

/**
 * <p>Description:Dubbo轮播图服务提供者接口</p>
 * <p>ContentService.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年6月4日 下午8:14:22
 * @version: 1.0
 */
public interface RestContentService {
	public List<TbContent> getContentList(Long cid);
	public TaotaoResult sysncContent(Long cid);
	public TaotaoResult sysncContentById(List<Long> ids);
}
