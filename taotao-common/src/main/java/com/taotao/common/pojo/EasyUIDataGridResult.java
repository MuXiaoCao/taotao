package com.taotao.common.pojo;

import java.util.List;

/**
 * <p>Description:商品展示条目pojo，主要包含两部分</p>
 * <p>EasyUIDataGridResult.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年5月22日 上午10:52:27
 * @version: 1.0
 */
public class EasyUIDataGridResult {
	// 总数
	private long total;
	// 每行的数据列表
	private List<?> rows;
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public List<?> getRows() {
		return rows;
	}
	public void setRows(List<?> rows) {
		this.rows = rows;
	}
	
}
