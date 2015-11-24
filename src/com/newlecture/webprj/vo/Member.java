package com.newlecture.webprj.vo;

import java.sql.Date;

public class Member {


	private String mid;
	private String email;
	private String pwd;
	private Date regDate;
	private String name;
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getRegdate() {
		return regDate;
	}
	
	public void setRegdate(Date regDate) {
		this.regDate = regDate;
	}

	

	
	
	
	
	
}
