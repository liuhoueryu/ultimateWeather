package com.neuedu.entity;

public class Everyday {
	private String date;
	private int pm;
	public Everyday(String date,int pm) {
		this.date=date;
		this.pm=pm;
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
}
