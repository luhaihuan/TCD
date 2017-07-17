package com.fuwa.lhh.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;

import com.fuwa.lhh.model.SearchType;
import com.fuwa.lhh.service.TypeManager;
import com.sulliar.ypq.service.FileDataManager;

public class GetMaterialAttributeAction {
	@Resource(name="FileDataManager")
	FileDataManager fileManager;
	
	@Resource
	TypeManager typeManager ;
	
	
	private boolean success;
	private List normal_attrs;
	private List class_attrs;
	private int total;
	private int start;
	private int limit;
	String searchType;
	String item_id;
	String item_revision;	
	private static final long serialVersionUID = 1L;
	
	public GetMaterialAttributeAction() {
		
	}

	public String execute()throws Exception {
        System.out.println("before:"+toString());
        success = false;
        searchType = URLDecoder.decode(searchType, "utf-8");
        System.out.println("after:"+toString());
		if("Çý¶¯ÇÅÁã¼þÍ¼£¨º¬×ÜÍ¼£©".equals(searchType)) {
			normal_attrs = fileManager.getQDQNormalAttrs(item_id, item_revision);
			success = true;
		} else if("Áã¼þÍ¼".equals(searchType)) {
			normal_attrs = fileManager.getGCQPartNormalAttrs(item_id, item_revision);
			class_attrs = fileManager.getGCQPartClassAttrs(item_id, item_revision);
			success = true;
		} else {
			SearchType type = typeManager.getTypeByName(searchType);
			if(type.getType_code1().contains("_CP_")) {
				normal_attrs = fileManager.getGCQCPNormalAttrs(item_id, item_revision);
				success = true;
			}
				
			
		}
	
	
		
		//total = normal_attrs.size();
		limit = total;	
		
		return "JSON";
	
		
	}
   

	
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public boolean isSuccess() {
		return success;
	}




	public void setSuccess(boolean success) {
		this.success = success;
	}






	public List getNormal_attrs() {
		return normal_attrs;
	}

	public void setNormal_attrs(List normal_attrs) {
		this.normal_attrs = normal_attrs;
	}

	public List getClass_attrs() {
		return class_attrs;
	}

	public void setClass_attrs(List class_attrs) {
		this.class_attrs = class_attrs;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getQuery_type() {
		return searchType;
	}

	public void setQuery_type(String query_type) {
		this.searchType = query_type;
	}

	public String getItem_id() {
		return item_id;
	}

	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}


	public String getItem_revision() {
		return item_revision;
	}

	public void setItem_revision(String item_revision) {
		this.item_revision = item_revision;
	}

	@Override
	public String toString() {
		return "GetMaterialAttributeAction [success=" + success
				+ ", normal_attrs=" + normal_attrs + ", class_attrs="
				+ class_attrs + ", total=" + total + ", start=" + start
				+ ", limit=" + limit + ", searchType=" + searchType
				+ ", item_id=" + item_id + ", item_revision=" + item_revision
				+ "]";
	}




	

	
}
