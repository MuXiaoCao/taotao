package com.taotao.portal.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.common.utils.JsonUtils;
import com.taotao.pojo.TbContent;
import com.taotao.portal.pojo.AdNode;
import com.taotao.portal.service.AdNodeService;
import com.taotao.rest.dubbo.RestContentService;

/**
 * <p>Description:轮播图展示service</p>
 * <p>AdNodeServiceImpl.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年6月2日 下午10:44:32
 * @version: 1.0
 */
@Service
public class AdNodeServiceImpl implements AdNodeService {

	// 数据库轮播图列表，使用远程过程调用
	@Autowired
	private RestContentService contentService;
	
	// 大广告位的cid
	@Value("${REST_CONTENT_AD1_CID}")
	private Long REST_CONTENT_AD1_CID;

	/**
	 * 得到大广告位列表
	 */
	@Override
	public String getAdNode1List() {
		
		// 通过远程过程调用得到大广告位内容列表
		List<TbContent> list = contentService.getContentList(REST_CONTENT_AD1_CID);
		
		List<AdNode> resultList = new ArrayList<>();
		for (TbContent tbContent : list) {
			AdNode node = new AdNode();
			node.setHeight(240);
			node.setWidth(670);
			node.setSrc(tbContent.getPic());
			
			node.setHeightB(240);
			node.setWidthB(550);
			node.setSrcB(tbContent.getPic2());
			
			node.setAlt(tbContent.getSubTitle());
			node.setHref(tbContent.getUrl());
			
			resultList.add(node);
		}
		// 需要把reslutList转换成json数据
		String resultJson = JsonUtils.objectToJson(resultList);
		return resultJson;
	}

}
