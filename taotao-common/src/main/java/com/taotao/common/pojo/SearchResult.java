package com.taotao.common.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * <p>Description:搜索服务返回结果，供portal和search信息传递（使用dubbo）</p>
 * <p>SearchResult.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年6月6日 下午8:11:20
 * @version: 1.0
 */
public class SearchResult implements Serializable{
	
	private static final long serialVersionUID = 3219867727605373348L;
	// 查询结果列表
	private List<SearchItem> itemList;
	// 总记录数
	private Long recordCount;
	// 总页数
	private int pageCount;
	// 当前页
	private int curPage;
	
	public List<SearchItem> getItemList() {
		return itemList;
	}
	public void setItemList(List<SearchItem> itemList) {
		this.itemList = itemList;
	}
	public Long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(Long recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
}
