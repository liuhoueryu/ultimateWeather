package com.neuedu.entity;

public class CitySet {
	private String city;
	private String start;
	private String end;
	private static CitySet cityset=new CitySet();
	private CitySet() {
	}
	public static CitySet getInstance() {
		return cityset;
	}
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
 
}
