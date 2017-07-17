package com.fuwa.lhh.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.springframework.stereotype.Service;

import com.fuwa.lhh.model.Record;



@Service
public class RecordManagerService extends BaseService<Record> implements RecordManager{

   
	
	
	public RecordManagerService() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public List getRecordList(String user_name) {
		// TODO Auto-generated method stub
		
		List list =  getCurrentSession().createQuery("from Record where user_name =? order by record_date desc")
				.setString(0, user_name)
				.list();
		return list;
		
	}


	@Override
	public Record getRecordModel(String user_name,String type_name,String item_id, String item_rev) {
		Record uniqueResult =(Record)getCurrentSession().createQuery("from Record where user_name =? and item_id =? and item_rev =? and type_name = ?")
		.setString(0, user_name)		
		.setString(1, item_id)
		.setString(2, item_rev)
		.setString(3, type_name)
		.uniqueResult();
		return uniqueResult;
	}

	@Override
	public void deleteOldData() {
		System.out.println("delete oldRecordData");
		Calendar instance = Calendar.getInstance();
		instance.add(Calendar.DAY_OF_MONTH, -7);
		
		System.out.println(new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(instance.getTime()));
		getCurrentSession().createQuery("delete from Record where record_date <?")
		//.setString(0, instance.getTime().toLocaleString())
		.setDate(0, instance.getTime())
		.executeUpdate();
		
	}


}
