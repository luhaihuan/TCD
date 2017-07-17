package com.fuwa.lhh.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.springframework.stereotype.Service;

import com.fuwa.lhh.model.Record;
import com.fuwa.ypq.model.Authority;
import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWUser;
import com.fuwa.ypq.model.Site;



@Service
public class DepartManagerService extends BaseService<Department> implements DepartManager{


	public DepartManagerService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Department getDepartByName(String departName) {
		// TODO Auto-generated method stub
		return (Department) getCurrentSession().createQuery("from Department where department_name = ?")
				.setString(0, departName).uniqueResult();
	}

	@Override
	public Department getDepartById(String departId) {
		// TODO Auto-generated method stub
		
		return (Department) getCurrentSession().createQuery("from Department where uuid = ?")
				.setString(0, departId).uniqueResult();
	}





	@SuppressWarnings("unchecked")
	@Override
	public Authority getUsersDepartACLById(String typeId,String userId) {
		
		FWUser fwUser = (FWUser) getCurrentSession().createQuery("from FWUser where uuid = ?")
		.setString(0, userId)
		.uniqueResult();
		for(Department dp : fwUser.getGroups()) {
			String dpId = dp.getUuid();
			Authority authority = (Authority) getCurrentSession().createQuery("from Authority where group_id = ? and query_type_id = ?")
			
			.setString(0, dpId)
			.setString(1, typeId)
			.uniqueResult();
			if(authority != null) {
				return authority;
			}
		}
		return null;
		 
	}
	
	
	@Override
	public List getDepartACLById(String siteId,String departId) {
		
		return getCurrentSession().createQuery("from Authority where site_id = ?  and group_id = ? ")
				.setString(0, siteId)
				.setString(1, departId)
				.list();
		 
	}


	@Override
	public int getUserCountByDepartId(String departId) {
		return ((Long)sessionFactory.getCurrentSession().createQuery("select count(u) from FWUser u left join u.groups g where g.uuid = ?")
		 .setString(0, departId)
		 .uniqueResult()).intValue();
		
		
	}

	
	@Override
	public List getUsersByDepartId(String departId, int start, int limit) {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("select u.uuid,u.user_name from FWUser u left join u.groups g where g.uuid = ? ORDER BY u.user_name ASC")
		.setString(0, departId)
		.setFirstResult(start)
		.setMaxResults(limit)
		.list();
	}



	@Override
	public List getUsersByDepartId(String departId) {
		// TODO Auto-generated method stub
		//select u.uuid,u.user_name from FWUser u left join u.groups g where g.uuid = ? ORDER BY u.user_name ASC
		return getCurrentSession().createQuery("select u.uuid,u.user_name from FWUser u left join u.groups g where  g.uuid = ? ORDER BY u.user_name ASC")
				.setString(0, departId)
				.list();
	}

	@Override
	public Department getPersonDepartByUserId(String userId) {
		// TODO Auto-generated method stub
		return (Department) getCurrentSession().createQuery("select d from Department d left join d.users u where u.uuid = ?")
				.setString(0, userId)
				.uniqueResult();
	}

	@Override
	public List getAllDepart() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("select uuid,department_name from Department").list();
	}

	@Override
	public Object[] getSiteIdandDepartId(String siteName, String departName) {
		// TODO Auto-generated method stub
		 List list = getCurrentSession().createQuery("select s.uuid,d.uuid from Site s left join s.departments d where  s.site_name = ? and d.department_name = ?")
				.setString(0, siteName)
				.setString(1, departName)
				.list();
		 return  list.isEmpty()?null:(Object[])list.get(0);
	}



}
