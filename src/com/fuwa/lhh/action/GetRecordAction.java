package com.fuwa.lhh.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;









import com.fuwa.lhh.service.RecordManager;
import com.fuwa.lhh.service.RecordManagerService;
import com.fuwa.ypq.model.FWUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.User;




public class GetRecordAction extends ActionSupport {

	@Resource
	RecordManager recordManager;

	private List recordList;
	boolean success;


	
	

	public GetRecordAction() {
		// TODO Auto-generated constructor stub
	}
	public boolean isSuccess() {
		return success;
	}





	public void setSuccess(boolean success) {
		this.success = success;
	}





	public List getRecordList() {
		return recordList;
	}





	public void setRecordList(List recordList) {
		this.recordList = recordList;
	}





	public String execute()throws Exception {	
		ActionContext context = ActionContext.getContext();
		FWUser fwUser = (FWUser)context.getSession().get("current_user");
		recordList = recordManager.getRecordList(fwUser.getUser_name());
        if(recordList==null)
        	recordList =Collections.EMPTY_LIST;

		success=true;
		return "success";
	}

}
