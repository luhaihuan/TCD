package com.fuwa.lhh.model;

public class TypeAndACLModel {
	String type_id;
	String type_name;
	boolean have_old_code;
	boolean bom_search;
	boolean material_search;
	boolean parent_search;
	public TypeAndACLModel(String type_id, String type_name,
			boolean have_old_code, boolean bom_search, boolean material_search,
			boolean parent_search) {
		super();
		this.type_id = type_id;
		this.type_name = type_name;
		this.have_old_code = have_old_code;
		this.bom_search = bom_search;
		this.material_search = material_search;
		this.parent_search = parent_search;
	}
	public TypeAndACLModel() {
		super();
		// TODO Auto-generated constructor stub
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
	public boolean isHave_old_code() {
		return have_old_code;
	}
	public void setHave_old_code(boolean have_old_code) {
		this.have_old_code = have_old_code;
	}
	public boolean isBom_search() {
		return bom_search;
	}
	public void setBom_search(boolean bom_search) {
		this.bom_search = bom_search;
	}
	public boolean isMaterial_search() {
		return material_search;
	}
	public void setMaterial_search(boolean material_search) {
		this.material_search = material_search;
	}
	public boolean isParent_search() {
		return parent_search;
	}
	public void setParent_search(boolean parent_search) {
		this.parent_search = parent_search;
	}
	@Override
	public String toString() {
		return "TypeAndACLModel [type_id=" + type_id + ", type_name="
				+ type_name + ", have_old_code=" + have_old_code
				+ ", bom_search=" + bom_search + ", material_search="
				+ material_search + ", parent_search=" + parent_search + "]";
	}
	
    
	
	
	
}
