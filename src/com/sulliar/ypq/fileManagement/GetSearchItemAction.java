// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   GetSearchItemAction.java

package com.sulliar.ypq.fileManagement;

import com.fuwa.lhh.model.Record;
import com.fuwa.lhh.service.RecordManager;
import com.fuwa.lhh.service.RecordManagerService;
import com.fuwa.ypq.model.FWUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.User;
import com.sulliar.ypq.service.FileDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

import java.io.PrintStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;

public class GetSearchItemAction extends ActionSupport
{

	private boolean flag;
	private List items;
	private int total;
	private int start;
	private int limit;
	String have_old_code;
	String query_type;
	String item_id;
	String item_id_new;
	String item_id_old;
	String item_name;
	String object_code;
	String item_revision;
	
	private static final long serialVersionUID = 1L;
	@Resource
	RecordManager recordManager;
	
	@Resource(name = "FileDataManager")
	FileDataManager fileManager ;


	public GetSearchItemAction()
	{
	}
    
	public String getHave_old_code() {
		return have_old_code;
	}

	public void setHave_old_code(String have_old_code) {
		this.have_old_code = have_old_code;
	}

	public String getItem_id_new() {
		return item_id_new;
	}

	public void setItem_id_new(String item_id_new) {
		this.item_id_new = item_id_new;
	}

	public String getItem_id_old() {
		return item_id_old;
	}

	public void setItem_id_old(String item_id_old) {
		this.item_id_old = item_id_old;
	}

	public String getObject_code() {
		return object_code;
	}

	public void setObject_code(String object_code) {
		this.object_code = object_code;
	}

	public List getItems()
	{
		return items;
	}

	public void setItems(List items)
	{
		this.items = items;
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

	public String getItem_id()
	{
		return item_id;
	}

	public void setItem_id(String item_id)
	{
		this.item_id = item_id;
	}

	public String getItem_name()
	{
		return item_name;
	}

	public void setItem_name(String item_name)
	{
		this.item_name = item_name;
	}

	public String getItem_revision()
	{
		return item_revision;
	}

	public void setItem_revision(String item_revision)
	{
		this.item_revision = item_revision;
	}

	public String getQuery_type()
	{
		return query_type;
	}

	public void setQuery_type(String query_type)
	{
		this.query_type = query_type;
	}

	public String execute()
		throws Exception
	{
		query_type = URLDecoder.decode(query_type, "utf-8");
		System.out.println(toString());
		//BeanFactory beanFactory = SpringBeanFactoryUtil.getInstance().getFactory();
		//FileDataManager fileManager = (FileDataManager)beanFactory.getBean("FileDataManager");
		
		ActionContext context = ActionContext.getContext();
		FWUser fwUser = (FWUser)context.getSession().get("current_user");
		items = fileManager.getSearchFiles(URLDecoder.decode(item_id_new, "utf-8"),URLDecoder.decode(item_id, "utf-8"),URLDecoder.decode(item_id_old, "utf-8"), URLDecoder.decode(item_name, "utf-8"), URLDecoder.decode(item_revision, "utf-8"), URLDecoder.decode(query_type, "utf-8"), fwUser != null ? fwUser.getUser_name() : "");
		total = items.size();
		limit = total;
		start = 0;
		
		
		
		
		
		
		
		//System.out.println("GetSearchItemAction executed");
		
		//System.out.println(new StringBuilder(" item_id:").append(URLDecoder.decode(item_id, "utf-8")).append(" item_name:").append(item_name).append(" item_rev:").append(item_revision).append(" user:").append(user.getName()));
		/*recordManager.deleteOldData();
		RecordModel recordModel = recordManager.getRecordModel(fwUser.getUser_name(),item_id, item_revision);
		Date date = new Date();
		if(recordModel==null) {
		
		
			RecordModel model=new RecordModel(fwUser.getUser_name(), item_id, item_name, item_revision, date);
			recordManager.save(model);
		} else {
		
			
			recordModel.setRecord_date(date);

			recordManager.update(recordModel);
		}*/
		flag = true;
		return "JSON";
	}

	@Override
	public String toString() {
		return "GetSearchItemAction [flag=" + flag + ", items=" + items
				+ ", total=" + total + ", start=" + start + ", limit=" + limit
				+ ", have_old_code=" + have_old_code + ", query_type="
				+ query_type + ", item_id=" + item_id + ", item_id_new="
				+ item_id_new + ", item_id_old=" + item_id_old + ", item_name="
				+ item_name + ", object_code=" + object_code
				+ ", item_revision=" + item_revision + "]";
	}
	
}
