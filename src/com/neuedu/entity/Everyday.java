package com.neuedu.entity;

public class Everyday {
	private String date;
	private int pm;
	private String city;
	private int aqi;
	private int temp;
	private int pm2;
	
	public Everyday(String date,int pm,int pm2,int aqi,int temp) {
		this.date=date;
		this.pm=pm;
		this.aqi=aqi;
		this.pm2=pm2;
		this.temp=temp;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getPm() {
		return pm;
	}
	public void setPm(int pm) {
		this.pm = pm;
	}
	public int getAqi() {
		return aqi;
	}
	public void setAqi(int aqi) {
		this.aqi = aqi;
	}
	public int getTemp() {
		return temp;
	}
	public void setTemp(int temp) {
		this.temp = temp;
	}
	public int getPm2() {
		return pm2;
	}
	public void setPm2(int pm2) {
		this.pm2 = pm2;
	}
}
