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
import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.Site;



@Service
public class SiteManagerService extends BaseService<Site> implements SiteManager{


	public SiteManagerService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Site getSiteByName(String siteName) {
		// TODO Auto-generated method stub
		return (Site) getCurrentSession().createQuery("from Site where site_name = ?")
				.setString(0, siteName).uniqueResult();
	}

	@Override
	public Site getSiteById(String siteId) {
		// TODO Auto-generated method stub
		return (Site) getCurrentSession().createQuery("from Site where uuid = ?")
				.setString(0, siteId).uniqueResult();
	}

	@Override
	public List getSiteACLById(String siteId) {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from Authority where site_id = ?")
				.setString(0, siteId)
				.list();
	}

	@Override
	public Site getDepartsSiteByDepartId(String departId) {
		// TODO Auto-generated method stub
		return (Site) getCurrentSession().createQuery("select s from Site s left join s.departments d where d.uuid = ?")
				.setString(0, departId)
				.uniqueResult();
	}
	@Override
	public List getDepartBySiteId(String siteId) {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("select uuid,department_name from Department where site.uuid = ?  ORDER BY department_name ASC")
				.setString(0, siteId)
				.list();
	}
	
	@Override
	public int getDepartCountBySiteId(String siteId) {
		return ((Long)sessionFactory.getCurrentSession().createQuery("select count(*) from  Department where site.uuid = ?")
		 .setString(0, siteId)
		 .uniqueResult()).intValue();
		
		
	}

	@Override
	public List getDepartBySiteId(String siteId, int start, int limit) {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("select uuid,department_name from Department where site.uuid = ?  ORDER BY department_name ASC")
				.setString(0, siteId)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
	}

	@Override
	public List getAllSite() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("select s.uuid,s.site_name from Site s order by s.site_name asc").list();
	}

	
}
