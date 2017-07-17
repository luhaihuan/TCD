package com.sulliar.ypq.model;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;

import com.fuwa.ypq.model.FWUser;

public class Log {
	int id;
	
	User user;
	String item_id;
	String item_name;
	String item_rev;
	
	String action_date;
	String action_type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	public String getItem_id() {
		return item_id;
	}
	
	
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	@JSON(serialize=false)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getItem_rev() {
		return item_rev;
	}
	public void setItem_rev(String item_rev) {
		this.item_rev = item_rev;
	}
	public String getAction_date() {
		return action_date;
	}
	public void setAction_date(String action_date) {
		this.action_date = action_date;
	}
	public String getAction_type() {
		return action_type;
	}
	public void setAction_type(String action_type) {
		this.action_type = action_type;
	}
}
