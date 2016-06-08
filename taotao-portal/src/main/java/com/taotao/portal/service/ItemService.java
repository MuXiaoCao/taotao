package com.taotao.portal.service;

import java.util.List;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParamItem;
import com.taotao.portal.pojo.PortalItem;

public interface ItemService {
	public PortalItem getItemById(Long id);
	public TbItemDesc getItemDescById(Long id);
	public TbItemParamItem getItemParamItemById(Long id);

}
