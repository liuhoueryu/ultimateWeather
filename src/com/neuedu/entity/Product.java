package com.neuedu.entity;

public class Product {
	private String name;    //类别名称
    private int num;        //销量
    
    public Product(String name, int num) {
        this.name = name;
        this.num = num;
    }
    
    public String getname() {
        return name;
    }
    public void setname(String name) {
        this.name = name;
    }
    public int getNum() {
        return num;
    }
    public void setNum(int num) {
        this.num = num;
    }
}
