package com.fuwa.lhh.model;

public class QDQBOMModel {
       String item_id;
       String item_rev_id;
       String axle_type;
       String specif;
       String item_name;
       String material;
       String unit;
       String quantity;
       String weight;
       String class_type;
       String phase;
	   	String client_name ;
		String client_no;
		String remark;
		boolean leaf;
		String iconCls;
		String bomline;
	public String getItem_id() {
		return item_id;
	}
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
	public String getItem_rev_id() {
		return item_rev_id;
	}
	public void setItem_rev_id(String item_rev_id) {
		this.item_rev_id = item_rev_id;
	}
	public String getAxle_type() {
		return axle_type;
	}
	public void setAxle_type(String axle_type) {
		this.axle_type = axle_type;
	}
	public String getSpecif() {
		return specif;
	}
	public void setSpecif(String specif) {
		this.specif = specif;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getClass_type() {
		return class_type;
	}
	public void setClass_type(String class_type) {
		this.class_type = class_type;
	}
	public String getPhase() {
		return phase;
	}
	public void setPhase(String phase) {
		this.phase = phase;
	}
	
	
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public QDQBOMModel() {
		super();
		// TODO Auto-generated constructor stub
		this.iconCls = "item_rev_icon";
	}
	
	public String getClient_name() {
		return client_name;
	}
	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}
	public String getClient_no() {
		return client_no;
	}
	public void setClient_no(String client_no) {
		this.client_no = client_no;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	
	public String getBomline() {
		return bomline;
	}
	public void setBomline(String bomline) {
		this.bomline = bomline;
	}
	public QDQBOMModel(String item_id, String item_rev_id, String axle_type,
			String specif, String item_name, String material, String unit,
			String quantity, String weight, String class_type, String phase,
			String client_name, String client_no, String remark, boolean leaf) {
		super();
		this.item_id = item_id;
		this.item_rev_id = item_rev_id;
		this.axle_type = axle_type;
		this.specif = specif;
		this.item_name = item_name;
		this.material = material;
		this.unit = unit;
		this.quantity = quantity;
		this.weight = weight;
		this.class_type = class_type;
		this.phase = phase;
		this.client_name = client_name;
		this.client_no = client_no;
		this.remark = remark;
		this.leaf = leaf;
		this.iconCls = "item_rev_icon";
	}
	
	public QDQBOMModel(String item_id, String item_rev_id, String axle_type,
			String specif, String item_name, String material, String unit,
			String quantity, String weight, String class_type, String phase,
			String client_name, String client_no, String remark, boolean leaf,
			String iconCls) {
		super();
		this.item_id = item_id;
		this.item_rev_id = item_rev_id;
		this.axle_type = axle_type;
		this.specif = specif;
		this.item_name = item_name;
		this.material = material;
		this.unit = unit;
		this.quantity = quantity;
		this.weight = weight;
		this.class_type = class_type;
		this.phase = phase;
		this.client_name = client_name;
		this.client_no = client_no;
		this.remark = remark;
		this.leaf = leaf;
		this.iconCls = iconCls;
	}
	
	public QDQBOMModel(String item_id, String item_rev_id, String axle_type,
			String specif, String item_name, String material, String unit,
			String quantity, String weight, String class_type, String phase,
			String client_name, String client_no, String remark, boolean leaf,
			String iconCls, String bomline) {
		super();
		this.item_id = item_id;
		this.item_rev_id = item_rev_id;
		this.axle_type = axle_type;
		this.specif = specif;
		this.item_name = item_name;
		this.material = material;
		this.unit = unit;
		this.quantity = quantity;
		this.weight = weight;
		this.class_type = class_type;
		this.phase = phase;
		this.client_name = client_name;
		this.client_no = client_no;
		this.remark = remark;
		this.leaf = leaf;
		this.iconCls = iconCls;
		this.bomline = bomline;
	}
	@Override
	public String toString() {
		return "QDQBOMModel [item_id=" + item_id + ", item_rev_id="
				+ item_rev_id + ", axle_type=" + axle_type + ", specif="
				+ specif + ", item_name=" + item_name + ", material="
				+ material + ", unit=" + unit + ", quantity=" + quantity
				+ ", weight=" + weight + ", class_type=" + class_type
				+ ", phase=" + phase + ", client_name=" + client_name
				+ ", client_no=" + client_no + ", remark=" + remark + ", leaf="
				+ leaf + ", iconCls=" + iconCls + ", bomline=" + bomline + "]";
	}
	
	
   
}
