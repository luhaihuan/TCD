package com.fuwa.lhh.model;

public class MaterialAttrs {
       String title;
       String content;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public MaterialAttrs(String title, String content) {
		super();
		this.title = title;
		this.content = content;
	}
	public MaterialAttrs() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MaterialAttrs [title=" + title + ", content=" + content + "]";
	}
       
}
