package com.sulliar.ypq.model;

import java.util.Set;

public class UserModel {


	String name;
	String pwd;

	boolean isAdmin;
	String email;
	
	boolean isLDAP;
		
	String site;
	String bgroup;	

	

	public String getBgroup() {
		return bgroup;
	}
	public void setBgroup(String bgroup) {
		this.bgroup = bgroup;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	
	public boolean getIsLDAP() {
		return isLDAP;
	}
	public void setIsLDAP(boolean isLDAP) {
		this.isLDAP = isLDAP;
	}
	public boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

}
