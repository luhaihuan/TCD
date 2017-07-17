package com.fuwa.lhh.model;

import java.util.Date;

import net.sf.json.JSONObject;

public class Record {
	
	int id;
	String user_name;
	String type_name;
	String item_id;
	String item_name;
	String item_rev;
	Date record_date;
	public Record() {
		// TODO Auto-generated constructor stub
		
	}
    


	public Record(String user_name, String item_id,
			String item_name, String item_rev, Date record_date) {
		super();
		this.user_name = user_name;
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_rev = item_rev;
		this.record_date = record_date;
	}
    

    
	public Record(String user_name, String type_name, String item_id,
			String item_name, String item_rev, Date record_date) {
		super();
		this.user_name = user_name;
		this.type_name = type_name;
		this.item_id = item_id;
		this.item_name = item_name;
		this.item_rev = item_rev;
		this.record_date = record_date;
	}



	public String getType_name() {
		return type_name;
	}



	public void setType_name(String type_name) {
		this.type_name = type_name;
	}



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
	public Date getRecord_date() {
		return record_date;
	}
	public void setRecord_date(Date record_date) {
		this.record_date = record_date;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	
	
	
	

}
