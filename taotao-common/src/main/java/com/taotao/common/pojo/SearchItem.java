package com.taotao.common.pojo;

import java.io.Serializable;

/**
 * <p>
 * Description:用于添加Solr域中数据的pojo，同时也是dao层用于接收返回数据的pojo
 * </p>
 * <p>
 * SearchItem.java
 * </P>
 * <p>
 * 个人主页：www.muxiaocao.cn/me
 * </p>
 * 
 * @author 木小草 @date： 2016年6月6日 下午1:38:48
 * @version: 1.0
 */
public class SearchItem implements Serializable{

	private static final long serialVersionUID = 5675435082183668731L;
	
	private String id;
	private String title;
	private String sell_point;
	private Long price;
	private String images;
	private String category_name;
	private String item_desc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSell_point() {
		return sell_point;
	}

	public void setSell_point(String sell_point) {
		this.sell_point = sell_point;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getCategory_name() {
		return category_name;
	}

	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}

	public String getItem_desc() {
		return item_desc;
	}

	public void setItem_desc(String item_desc) {
		this.item_desc = item_desc;
	}

}
