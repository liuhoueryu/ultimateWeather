package com.neuedu.entity;

public class CitySet {
	private String city;
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
	
}
