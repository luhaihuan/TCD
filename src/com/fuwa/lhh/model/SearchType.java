package com.fuwa.lhh.model;

public class SearchType {
	int id;
	String type_id;
	String type_name;
	String type_code1;
	String type_code2;
	String rev_ds_rel;
	boolean have_old_code;
	public SearchType() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SearchType(String type_id, String type_name, String type_code1,
			String type_code2, boolean have_old_code) {
		super();
		this.type_id = type_id;
		this.type_name = type_name;
		this.type_code1 = type_code1;
		this.type_code2 = type_code2;
		this.have_old_code = have_old_code;
	}
	
	public SearchType(String type_id, String type_name, String type_code1,
			String type_code2, String rev_ds_rel, boolean have_old_code) {
		super();
		this.type_id = type_id;
		this.type_name = type_name;
		this.type_code1 = type_code1;
		this.type_code2 = type_code2;
		this.rev_ds_rel = rev_ds_rel;
		this.have_old_code = have_old_code;
	}
	public String getRev_ds_rel() {
		return rev_ds_rel;
	}
	public void setRev_ds_rel(String rev_ds_rel) {
		this.rev_ds_rel = rev_ds_rel;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType_id() {
		return type_id;
	}
	public void setType_id(String type_id) {
		this.type_id = type_id;
	}
	public String getType_name() {
		return type_name;
	}
	public void setType_name(String type_name) {
		this.type_name = type_name;
	}
	public String getType_code1() {
		return type_code1;
	}
	public void setType_code1(String type_code1) {
		this.type_code1 = type_code1;
	}
	public String getType_code2() {
		return type_code2;
	}
	public void setType_code2(String type_code2) {
		this.type_code2 = type_code2;
	}
	public boolean isHave_old_code() {
		return have_old_code;
	}
	public void setHave_old_code(boolean have_old_code) {
		this.have_old_code = have_old_code;
	}
	@Override
	public String toString() {
		return "SearchType [id=" + id + ", type_id=" + type_id + ", type_name="
				+ type_name + ", type_code1=" + type_code1 + ", type_code2="
				+ type_code2 + ", have_old_code=" + have_old_code + "]";
	}
    

}
