package com.taotao.portal.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.taotao.pojo.TbItem;

public class PortalItem {

	public PortalItem() {
		
	}
	
	public PortalItem(TbItem item) {
		this.barcode = item.getBarcode();
		this.cid = item.getCid();
		this.created = item.getCreated();
		this.id = item.getId();
		String i = item.getImage();
		images = new ArrayList<>();
		if (i != null) {
			String[] strings = i.split(",");
			for (String image : strings) {
				images.add(image);
			}
		}
		this.num = item.getNum();
		this.price = item.getPrice();
		this.sellPoint = item.getSellPoint();
		this.status = item.getStatus();
		this.title = item.getTitle();
		this.updated = item.getUpdated();
	}
	
	private Long id;

    private String title;

    private String sellPoint;

    private Long price;

    private Integer num;

    private String barcode;

    private List<String> images;

    private Long cid;

    private Byte status;

    private Date created;

    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getSellPoint() {
        return sellPoint;
    }

    public void setSellPoint(String sellPoint) {
        this.sellPoint = sellPoint == null ? null : sellPoint.trim();
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode == null ? null : barcode.trim();
    }

    public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
}
