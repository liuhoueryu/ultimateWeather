package com.neuedu.entity;

import java.io.Serializable;

public class NewsType implements Serializable {
	
	private Integer typeid;
	private String typename;
	
	//private List<News> newsList;   //Ò»¶Ô¶à
	
	
	public Integer getTypeid() {
		return typeid;
	}
	public void setTypeid(Integer typeid) {
		this.typeid = typeid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	

}
