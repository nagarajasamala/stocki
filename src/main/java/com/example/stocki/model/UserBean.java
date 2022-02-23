package com.example.stocki.model;


public class UserBean {
	private Integer id;
	private String name;
	private String mobile;
	private String pd;
	public UserBean(Users user)
    {
        this.id=user.getId();
        this.name = user.getName();
        this.pd = user.getPd();
        this.mobile=user.getMobile();
    }
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	

	public String getPd() {
		return pd;
	}

	public void setPd(String pd) {
		this.pd = pd;
	}
}
