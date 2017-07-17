package com.sulliar.ypq.service;

import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWLog;
import com.fuwa.ypq.model.FWSeal;
import com.fuwa.ypq.model.FWUser;
import com.fuwa.ypq.model.Site;
import com.fuwa.ypq.model.TreeModel;


public class OrganizationDataManagerService implements OrganizationDataManager {

	SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addUser(FWUser user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void modifyUser(FWUser user) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(user);
	}

	@Override
	public void delUser(FWUser user) {
		// TODO Auto-generated method stub
		Set set = user.getLogs();
		
		if(set!=null){
			Iterator iterator = set.iterator();
		
			while(iterator.hasNext()){
				sessionFactory.getCurrentSession().delete(iterator.next());
			}
		}
		
		sessionFactory.getCurrentSession().delete(user);
	}

	@Override
	public void addDepartment(Department department) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(department);
	}

	@Override
	public void modifyDepartment(Department department) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(department);
	}

	@Override
	public void delDepartment(Department department) {
		// TODO Auto-generated method stub
		
		sessionFactory.getCurrentSession().delete(department);
	}

	@Override
	public void addSite(Site site) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(site);
	}

	@Override
	public void modifySite(Site site) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(site);
	}

	@Override
	public void delSite(Site site) {
		// TODO Auto-generated method stub
		Set set = site.getDepartments();
		if(set!=null){
			Iterator iterator = set.iterator();
		
			while(iterator.hasNext()){
				delDepartment((Department) iterator.next());
			}
		}
		sessionFactory.getCurrentSession().delete(site);
	}

	@Override
	public List<TreeModel> getAllOrganization(String id,String nodeType) {
		// TODO Auto-generated method stub
		List<TreeModel> pris = new ArrayList<TreeModel> ();
		
		if(id==null||id.equals("")||id.equals("root")){
			String hql = "from Site ORDER BY site_name ASC";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			
			List<Site> sites = query.list();
			if(sites!=null&&sites.size()>0){
				for(Site site:sites){
					TreeModel tm = new TreeModel();
					tm.setId(site.getUuid());
					tm.setText(site.getSite_name());
					tm.setIconCls("depart_icon");
					tm.setNodeType("site");
					Set<Department> departments = site.getDepartments();
					
					if(departments.size()>0){
						tm.setLeaf(false);
					}else{
						tm.setLeaf(true);
					}
					
					pris.add(tm);
				}
			}
		}else{
			
			if(nodeType.equals("site")){
				String hql = "from Department where site.uuid = ? ORDER BY department_name ASC";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.setString(0, id);
				
				List<Department> departments = query.list();
				if(departments!=null&&departments.size()>0){
					for(Department department:departments){
						TreeModel tm = new TreeModel();
						tm.setId(department.getUuid());
						tm.setText(department.getDepartment_name());
						tm.setIconCls("depart_icon");
						tm.setNodeType("department");
						Set<FWUser> users = department.getUsers();
						
						if(users.size()>0){
							tm.setLeaf(false);
						}else{
							tm.setLeaf(true);
						}
						tm.setParentId(id);
						pris.add(tm);
					}
				}
			}else if(nodeType.equals("department")){
				//from Student s left join s.course c where s.sname='ภ๎ฯþรท
				String hql = "select u from FWUser u left join u.groups g where g.uuid = ? ORDER BY u.user_name ASC";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.setString(0, id);
				
				List<FWUser> l = query.list();
				
				if(l!=null&&l.size()>0){		
					
					Iterator it = l.iterator();
					
					while(it.hasNext()){
						FWUser user = (FWUser) it.next();
						TreeModel tm = new TreeModel();
						tm.setId(user.getUuid());
						tm.setText(user.getUser_name());
						tm.setIconCls("user_icon");
						tm.setNodeType("user");
						
						tm.setLeaf(true);
						tm.setParentId(id);
						pris.add(tm);
					}
				}
				
				
				
				
			}else if(nodeType.equals("user")){
				
			}
			
		}	
		
		return pris;
	}

	@Override
	public Site findSiteByName(String site_name) {
		// TODO Auto-generated method stub
		String hql = "from Site where site_name = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, site_name);
		List l = query.list();
		if(l.size()>0){
			return (Site) l.get(0);
		}
		
		return null;
	}

	@Override
	public Site findSiteById(String uuid) {
		// TODO Auto-generated method stub
		String hql = "from Site where uuid = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, uuid);
		List l = query.list();
		if(l.size()>0){
			return (Site) l.get(0);
		}
		return null;
	}
	
	@Override
	public int getTotalCount(String tablename)
	{
		Query q = sessionFactory.getCurrentSession().createQuery((new StringBuilder("select count(*) from  ")).append(tablename).toString());
		List ll = q.list();
		Long a = (Long)ll.get(0);
		return a.intValue();
	}

	@Override
	public List<FWUser> getAllUser(int start,int limit) {
		// TODO Auto-generated method stub
		String hql = "from FWUser";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(limit);
		List l = query.list();
		
		return l;
	}

	@Override
	public FWUser findUserByName(String user_name) {
		// TODO Auto-generated method stub
		String hql = "from FWUser where user_name = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, user_name);
		List l = query.list();
		if(l.size()>0){
			return (FWUser) l.get(0);
		}
		return null;
	}
	
	@Override
	public FWUser findUserByNoOrName(String content) {
		// TODO Auto-generated method stub
		String hql = "from FWUser where user_name = ? or user_no = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, content);
		query.setString(1, content);
		List l = query.list();
		if(l.size()>0){
			return (FWUser) l.get(0);
		}
		return null;
	}

	@Override
	public List<Site> getAllSites(int start, int limit) {
		// TODO Auto-generated method stub
		String hql = "from Site ORDER BY site_name ASC";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(limit);
		List l = query.list();
		
		return l;
	}



	@Override
	public List getDepartsBySiteId(String siteId,int start, int limit) {
		// TODO Auto-generated method stub
		
		return sessionFactory.getCurrentSession().createQuery("select uuid,department_name from Department where site.uuid =  ? ORDER BY department_name ASC ")
		.setString(0, siteId)
		.setFirstResult(start)
		.setMaxResults(limit)
		.list();
		
		 
	}

	@Override
	public List getUsersByDepartId(String departId,int start, int limit) {
		return sessionFactory.getCurrentSession().createQuery("select u.uuid,u.user_name from FWUser u left join u.groups g where g.uuid = ? ORDER BY u.user_name ASC")
				.setString(0, departId)
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
	}

	@Override
	public Site findSiteById(String uuid, Set<Department> departs) {
		// TODO Auto-generated method stub
		String hql = "from Site where uuid = ?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, uuid);
		List l = query.list();
		if(l.size()>0){
			Site s = (Site) l.get(0);			
			departs.addAll(s.getDepartments());
			return s;
		}
		return null;
	}

	@Override
	public Department findDepartById(String departId) {
		// TODO Auto-generated method stub
		return (Department) sessionFactory.getCurrentSession().createQuery("from Department where uuid = ?")
		.setString(0, departId)
		.uniqueResult();
		 
	}

	@Override
	public void modifyDepart(Department curentDepart) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(curentDepart);
		
	}

	@Override
	public void delDepart(Department bean) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().delete(bean);
	}

	@Override
	public Department findDepartById(Set<FWUser> users, String uuid) {
		// TODO Auto-generated method stub
		Department depart = (Department) sessionFactory.getCurrentSession().createQuery("from Department where uuid = ?")
		.setString(0, uuid)
		.uniqueResult();
		users.addAll(depart.getUsers());
		return depart;
	}

	@Override
	public Department getDepartById(String uuid) {
		// TODO Auto-generated method stub
		return (Department) sessionFactory.getCurrentSession().createQuery("from Department where uuid = ?")
				.setString(0, uuid)
				.uniqueResult();
	}

	@Override
	public FWUser findUserById(String uuid) {
		// TODO Auto-generated method stub
	
		return (FWUser) sessionFactory.getCurrentSession().createQuery("from FWUser where uuid = ?")
				.setString(0, uuid)
				.uniqueResult();
	}

	@Override
	public void modifyDepart(String departId, Set<FWUser> delUsers) {
		// TODO Auto-generated method stub
		Department depart = (Department) sessionFactory.getCurrentSession().createQuery("from Department where uuid = ?")
		.setString(0, departId)
		.uniqueResult();	
		Set<FWUser> tempDel = new HashSet<FWUser>();		
		for(FWUser f1 : delUsers) {
			for(FWUser f2 : depart.getUsers()) {
				if(f1.getUuid().equals(f2.getUuid()))
					tempDel.add(f2);
				
			}
		}
		depart.getUsers().removeAll(tempDel);
		for(FWUser f : depart.getUsers()) {
			System.out.println("remove after-->"+f.getUser_name());
		}
				
		sessionFactory.getCurrentSession().update(depart);
		
		
	}

	@Override
	public int getDepartCountBySiteId(String siteId) {
		return ((Long)sessionFactory.getCurrentSession().createQuery("select count(*) from  Department where site.uuid = ?")
		 .setString(0, siteId)
		 .uniqueResult()).intValue();
		
		
	}

	@Override
	public int getUserCountBySiteId(String id) {

		return ((Long)sessionFactory.getCurrentSession().createQuery("select count(*) from FWUser u left join u.groups g where g.uuid = ? ")
		 .setString(0, id)
		 .uniqueResult()).intValue();
		
		
	
	}

	@Override
	public Department getDepartBySiteIdAndDepartName(String siteId,
			String departName) {
		// TODO Auto-generated method stub
		return (Department) sessionFactory.getCurrentSession().createQuery("from Department where site.uuid = ? and department_name = ?" )
				.setString(0, siteId)
				.setString(1, departName)
				
				.uniqueResult();
	}

	@Override
	public List<Department> findUserDepartsByUserId(String userId) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession().createQuery("select d from Department d left join d.users u where u.uuid = ?")
				.setString(0, userId)
				.list();
	}

	@Override
	public List<TreeModel> getOrganizationSeal(String id, String nodeType) {

		// TODO Auto-generated method stub
		List<TreeModel> pris = new ArrayList<TreeModel> ();
		
		if(id==null||id.equals("")||id.equals("root")){
			String hql = "from Site ORDER BY site_name ASC";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			
			List<Site> sites = query.list();
			if(sites!=null&&sites.size()>0){
				for(Site site:sites){
					TreeModel tm = new TreeModel();
					tm.setId(site.getUuid());
					tm.setText(site.getSite_name());
					tm.setIconCls("depart_icon");
					tm.setNodeType("site");
					Set<Department> departments = site.getDepartments();
					
					if(departments.size()>0){
						tm.setLeaf(false);
					}else{
						tm.setLeaf(true);
					}
					
					pris.add(tm);
				}
			}
		}else{
			
			if(nodeType.equals("site")){
				String hql = "from Department where site.uuid = ? ORDER BY department_name ASC";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.setString(0, id);
				
				List<Department> departments = query.list();
				if(departments!=null&&departments.size()>0){
					for(Department department:departments){
						TreeModel tm = new TreeModel();
						tm.setId(department.getUuid());
						tm.setText(department.getDepartment_name());
						tm.setIconCls("depart_icon");
						tm.setNodeType("department");
						Set<FWUser> users = department.getUsers();
						
						if(users.size()>0){
							tm.setLeaf(false);
						}else{
							tm.setLeaf(true);
						}
						tm.setParentId(id);
						pris.add(tm);
					}
				}
			}else if(nodeType.equals("department")){
				//from Student s left join s.course c where s.sname='ภ๎ฯþรท
				String hql = "select s from FWSeal s left join s.departs d where d.uuid = ? ORDER BY s.stamp_disy_name ASC";
				Query query = sessionFactory.getCurrentSession().createQuery(hql);
				query.setString(0, id);
				
				List<FWUser> l = query.list();
				
				if(l!=null&&l.size()>0){		
					
					Iterator it = l.iterator();
					
					while(it.hasNext()){
						FWSeal fwseal = (FWSeal) it.next();
						TreeModel tm = new TreeModel();
						tm.setId(fwseal.getUuid());
						tm.setText(fwseal.getSeal_show_name());
						tm.setIconCls("user_icon");
						tm.setNodeType("seal");
						
						tm.setLeaf(true);
						tm.setParentId(id);
						pris.add(tm);
					}
				}
				
				
				
				
			}else if(nodeType.equals("seal")){
				
			}
			
		}	
		
		return pris;
	
	}










}
