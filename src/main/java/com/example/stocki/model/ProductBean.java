package com.example.stocki.model;

public class ProductBean {
	private Integer pid;
	private String name;
	private Integer qty;
    public ProductBean(Products p)
    {
        this.pid=p.getPid();
        this.name = p.getName();
        this.qty = p.getQty();
    }
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}
    	
}
