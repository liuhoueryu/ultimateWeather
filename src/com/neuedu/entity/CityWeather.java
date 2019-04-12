package com.neuedu.entity;

public class CityWeather {
	private String date;
	private int pm;
	private int aqi;
	private int pm2;
	private int temp;
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public CityWeather(String city , int aqi, int temp,int pm, int pm2) {
		this.aqi = aqi;
		this.city = city;
		this.pm = pm;
		this.pm2 = pm2;
		this.temp = temp;
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

	public int getPm2() {
		return pm2;
	}

	public void setPm2(int pm2) {
		this.pm2 = pm2;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
