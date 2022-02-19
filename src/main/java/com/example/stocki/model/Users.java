package com.example.stocki.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name = "users") // This tells Hibernate to make a table out of this class
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
	@SequenceGenerator(name="user_generator", sequenceName = "user_seq", allocationSize=1)
	@Column(name = "uid", updatable = false, nullable = false)
	private Integer id;
	
	@Column(unique = true)
	private String name;

	@Column(unique = true)
	private String mobile;
	
	private String pd;
	
	
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

	
	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", pd=" + pd + "]";
	}



	

	
}
