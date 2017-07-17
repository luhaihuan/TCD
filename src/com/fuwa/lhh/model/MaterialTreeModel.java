package com.fuwa.lhh.model;

import java.util.List;



public class MaterialTreeModel {
	String id;
	String text;
	String item_id;
	String item_rev_id;
	 String iconCls;
     boolean leaf;
     List<MaterialTreeModel> children;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
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
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public List<MaterialTreeModel> getChildren() {
		return children;
	}
	public void setChildren(List<MaterialTreeModel> children) {
		this.children = children;
	}
	@Override
	public String toString() {
		return "MaterialTreeModel [id=" + id + ", text=" + text + ", item_id="
				+ item_id + ", item_rev_id=" + item_rev_id + ", iconCls="
				+ iconCls + ", leaf=" + leaf + ", children=" + children + "]";
	}
	public MaterialTreeModel(String id, String text, String item_id,
			String item_rev_id, String iconCls, boolean leaf,
			List<MaterialTreeModel> children) {
		super();
		this.id = id;
		this.text = text;
		this.item_id = item_id;
		this.item_rev_id = item_rev_id;
		this.iconCls = iconCls;
		this.leaf = leaf;
		this.children = children;
	}
	public MaterialTreeModel() {
		super();
		// TODO Auto-generated constructor stub
	}
     
     
    
}
