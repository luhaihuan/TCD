package com.fuwa.lhh.action;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import com.fuwa.lhh.model.SearchType;
import com.fuwa.lhh.model.UserACLModel;
import com.fuwa.lhh.service.TypeManager;
import com.fuwa.ypq.model.Authority;
import com.fuwa.ypq.model.FWUser;
import com.sulliar.ypq.service.FileDataManager;

public class GetGCQBOMAction {
	@Resource(name="FileDataManager")
	FileDataManager fileManager;
	
	@Resource
	TypeManager typeManager ;
	
	
	private boolean success;
	private List attrs;
	private int total;
	private int start;
	private int limit;
	String searchType;
	String item_id;
	String item_rev_id;	
	private static final long serialVersionUID = 1L;
	
	public GetGCQBOMAction() {
		
	}
	public String execute()throws Exception {
        System.out.println(toString());
        attrs = fileManager.getGCQBOM(item_id, item_rev_id);
		total = attrs.size();
		limit = total;	
		success = true;
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




	public List getAttrs() {
		return attrs;
	}

	public void setAttrs(List attrs) {
		this.attrs = attrs;
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
	public String getItem_rev_id() {
		return item_rev_id;
	}
	public void setItem_rev_id(String item_rev_id) {
		this.item_rev_id = item_rev_id;
	}
	@Override
	public String toString() {
		return "GetBOMAction [success=" + success + ", attrs=" + attrs
				+ ", total=" + total + ", start=" + start + ", limit=" + limit
				+ ", searchType=" + searchType + ", item_id=" + item_id
				+ ", item_rev_id=" + item_rev_id + "]";
	}


	






	
}
