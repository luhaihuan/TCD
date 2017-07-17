package com.fuwa.lhh.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;





import com.fuwa.lhh.service.DepartManager;
import com.fuwa.lhh.service.TypeManager;
import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWUser;
import com.fuwa.ypq.model.QueryType;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.User;
import com.sulliar.ypq.service.UserDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

public class GetAllTypeAction extends ActionSupport{

	private boolean flag;
	private List types;
	private int total;
	private int start;
	private int limit;
	private static final long serialVersionUID = 1L;
	
	@Resource
	TypeManager typeManager;
	
	@Resource
	DepartManager departManager;
	

	public GetAllTypeAction()
	{
	}

	public List getTypes()
	{
		return types;
	}

	public void setTypes(List types)
	{
		this.types = types;
	}

	public boolean isFlag()
	{
		return flag;
	}

	public void setFlag(boolean flag)
	{
		this.flag = flag;
	}

	public int getTotal()
	{
		return total;
	}

	public void setTotal(int total)
	{
		this.total = total;
	}

	public int getStart()
	{
		return start;
	}

	public void setStart(int start)
	{
		this.start = start;
	}

	public int getLimit()
	{
		return limit;
	}

	public void setLimit(int limit)
	{
		this.limit = limit;
	}

	@SuppressWarnings("unchecked")
	public String execute()throws Exception {
		
		System.out.println(toString());
		ActionContext context = ActionContext.getContext();
		FWUser fwUser = (FWUser)context.getSession().get("current_user");
		
		Department depart = departManager.getPersonDepartByUserId(fwUser.getUuid());
		if(depart!=null)
		System.out.println("GetAllTypeAction->depart->"+depart.getDepartment_name());
		else
			System.out.println("GetAllTypeAction->nodepart->");	
		if(depart != null && depart.getDepartment_name().equals("dba")) {
			types = typeManager.getTypesByOrder();
		} else {
			types = typeManager.getPersonTypesByOrder(fwUser);
		}
		
		
		
		total = types.size();
		limit = total;
		start = 0;
		flag = true;
		return "JSON";
	}

	@Override
	public String toString() {
		return "GetAllTypeAction [flag=" + flag + ", types=" + types
				+ ", total=" + total + ", start=" + start + ", limit=" + limit
				+ ", typeManager=" + typeManager + "]";
	}
	
}
