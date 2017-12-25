package org.ecomileage.bean;

import java.util.Date;

public class Group {
	private Integer id;
	private String imageUrl;
	private String name;
	private Date deleteDate;
	private Integer totalMember;
	
	public Group(Integer id, String imageUrl, String name, Date deleteDate, Integer totalMember) {
		super();
		this.id = id;
		this.imageUrl = imageUrl;
		this.name = name;
		this.deleteDate = deleteDate;
		this.totalMember = totalMember;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDeleteDate() {
		return deleteDate;
	}
	public void setDeleteDate(Date deleteDate) {
		this.deleteDate = deleteDate;
	}
	public Integer getTotalMember() {
		return totalMember;
	}
	public void setTotalMember(Integer totalMember) {
		this.totalMember = totalMember;
	}
	
	
}
