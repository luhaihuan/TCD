package com.fuwa.lhh.model;

import java.util.Date;

import net.sf.json.JSONObject;

public class Stamp {
	
	int id;
	String stamp_id;
	String stamp_disy_name;
	String stamp_true_name;
	String depart_name;

	public Stamp() {
		// TODO Auto-generated constructor stub
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStamp_disy_name() {
		return stamp_disy_name;
	}

	public void setStamp_disy_name(String stamp_disy_name) {
		this.stamp_disy_name = stamp_disy_name;
	}

	public String getStamp_true_name() {
		return stamp_true_name;
	}

	public void setStamp_true_name(String stamp_true_name) {
		this.stamp_true_name = stamp_true_name;
	}

	public String getDepart_name() {
		return depart_name;
	}

	public void setDepart_name(String depart_name) {
		this.depart_name = depart_name;
	}
	

	public String getStamp_id() {
		return stamp_id;
	}

	public void setStamp_id(String stamp_id) {
		this.stamp_id = stamp_id;
	}

	public Stamp(String stamp_id, String stamp_disy_name,
			String stamp_true_name, String depart_name) {
		super();
		this.stamp_id = stamp_id;
		this.stamp_disy_name = stamp_disy_name;
		this.stamp_true_name = stamp_true_name;
		this.depart_name = depart_name;
	}

	@Override
	public String toString() {
		return "Stamp [id=" + id + ", stamp_id=" + stamp_id
				+ ", stamp_disy_name=" + stamp_disy_name + ", stamp_true_name="
				+ stamp_true_name + ", depart_name=" + depart_name + "]";
	}


    
	
    
    

	
	
	
	

}
