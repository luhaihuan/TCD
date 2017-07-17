package com.sulliar.ypq.model;

import java.util.Set;

import org.apache.struts2.json.annotations.JSON;

public class User {
	int id;
	String name;
	String pwd;

	boolean isAdmin;
	String email;
	
	boolean isLDAP;
		
	String site;
	String bgroup;
	
	Set logs;
	
	boolean actived;
	
	public boolean getActived() {
		return actived;
	}
	public void setActived(boolean actived) {
		this.actived = actived;
	}
	@JSON(serialize=false)
	public Set getLogs() {
		return logs;
	}
	public void setLogs(Set logs) {
		this.logs = logs;
	}
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
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
