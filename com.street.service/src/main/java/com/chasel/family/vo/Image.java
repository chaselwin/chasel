package com.chasel.family.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String type;
	private String name;
	private String title;
	private String memo;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Timestamp createTime;
	private String creator;

	public Image() {
	}

	public Image(int id, String type, String title, String memo) {
		super();
		this.id = id;
		this.type = type;
		this.title = title;
		this.memo = memo;
	}

	public Image(int id, String type, String name, String title, String memo, Timestamp createTime, String creator) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.title = title;
		this.memo = memo;
		this.createTime = createTime;
		this.creator = creator;
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public String getMemo() {
		return memo;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public String getCreator() {
		return creator;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Override
	public String toString() {
		return "Image [id=" + id + ", type=" + type + ", name=" + name + ", title=" + title + ", memo=" + memo
				+ ", createTime=" + createTime + ", creator=" + creator + "]";
	}

}
