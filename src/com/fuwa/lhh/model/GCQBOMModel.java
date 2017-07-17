package com.fuwa.lhh.model;

public class GCQBOMModel {
     String item_id;
     String item_rev_id;
     String item_id_old;
     String client_object_code;
     String item_name;
     String material;
     String unit;
     String quantity;
     String single_weight;
     String total_weight;
     String part_type;
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
	public String getItem_id_old() {
		return item_id_old;
	}
	public void setItem_id_old(String item_id_old) {
		this.item_id_old = item_id_old;
	}
	public String getClient_object_code() {
		return client_object_code;
	}
	public void setClient_object_code(String client_object_code) {
		this.client_object_code = client_object_code;
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
	public String getSingle_weight() {
		return single_weight;
	}
	public void setSingle_weight(String single_weight) {
		this.single_weight = single_weight;
	}
	public String getTotal_weight() {
		return total_weight;
	}
	public void setTotal_weight(String total_weight) {
		this.total_weight = total_weight;
	}
	public String getPart_type() {
		return part_type;
	}
	public void setPart_type(String part_type) {
		this.part_type = part_type;
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
	
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	
	public String getBomline() {
		return bomline;
	}
	public void setBomline(String bomline) {
		this.bomline = bomline;
	}
	public GCQBOMModel() {
		super();
		// TODO Auto-generated constructor stub
		this.iconCls = "item_rev_icon";
	}
	public GCQBOMModel(String item_id, String item_rev_id, String item_id_old,
			String client_object_code, String item_name, String material,
			String unit, String quantity, String single_weight,
			String total_weight, String part_type, String remark, boolean leaf) {
		super();
		this.item_id = item_id;
		this.item_rev_id = item_rev_id;
		this.item_id_old = item_id_old;
		this.client_object_code = client_object_code;
		this.item_name = item_name;
		this.material = material;
		this.unit = unit;
		this.quantity = quantity;
		this.single_weight = single_weight;
		this.total_weight = total_weight;
		this.part_type = part_type;
		this.remark = remark;
		this.leaf = leaf;
		this.iconCls = "item_rev_icon";
	}
	
	
	public GCQBOMModel(String item_id, String item_rev_id, String item_id_old,
			String client_object_code, String item_name, String material,
			String unit, String quantity, String single_weight,
			String total_weight, String part_type, String remark, boolean leaf,
			String iconCls) {
		super();
		this.item_id = item_id;
		this.item_rev_id = item_rev_id;
		this.item_id_old = item_id_old;
		this.client_object_code = client_object_code;
		this.item_name = item_name;
		this.material = material;
		this.unit = unit;
		this.quantity = quantity;
		this.single_weight = single_weight;
		this.total_weight = total_weight;
		this.part_type = part_type;
		this.remark = remark;
		this.leaf = leaf;
		this.iconCls = iconCls;
	}
	public GCQBOMModel(String item_id, String item_rev_id, String item_id_old,
			String client_object_code, String item_name, String material,
			String unit, String quantity, String single_weight,
			String total_weight, String part_type, String remark, boolean leaf,
			String iconCls, String bomline) {
		super();
		this.item_id = item_id;
		this.item_rev_id = item_rev_id;
		this.item_id_old = item_id_old;
		this.client_object_code = client_object_code;
		this.item_name = item_name;
		this.material = material;
		this.unit = unit;
		this.quantity = quantity;
		this.single_weight = single_weight;
		this.total_weight = total_weight;
		this.part_type = part_type;
		this.remark = remark;
		this.leaf = leaf;
		this.iconCls = iconCls;
		this.bomline = bomline;
	}
	@Override
	public String toString() {
		return "GCQBOMModel [item_id=" + item_id + ", item_rev_id="
				+ item_rev_id + ", item_id_old=" + item_id_old
				+ ", client_object_code=" + client_object_code + ", item_name="
				+ item_name + ", material=" + material + ", unit=" + unit
				+ ", quantity=" + quantity + ", single_weight=" + single_weight
				+ ", total_weight=" + total_weight + ", part_type=" + part_type
				+ ", remark=" + remark + ", leaf=" + leaf + ", iconCls="
				+ iconCls + ", bomline=" + bomline + "]";
	}
	
	
	
}
