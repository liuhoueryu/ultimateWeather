package com.neuedu.entity;

public class Product {
	private String name;    //�������
    private int num;        //����
    
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
