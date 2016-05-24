package com.taotao.common.pojo;

/**
 * <p>Description:分类列表pojo，包括三部分组成</p>
 * <p>EasyUITreeNode.java</P>
 * <p>个人主页：www.muxiaocao.cn/me </p>
 * @author 木小草
 * @date：  2016年5月22日 上午11:02:02
 * @version: 1.0
 */
public class EasyUITreeNode {
	// 类目编号
	private long id;
	// 类目名称
	private String text;
	// 类目状态（是否是叶子节点）
	private String state;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
