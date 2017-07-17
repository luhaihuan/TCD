package com.fuwa.lhh.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;

import com.fuwa.ypq.model.FWUser;
import com.sulliar.ypq.model.User;





@Service
public class UserManagerService extends BaseService<FWUser> implements UserManager{


	public UserManagerService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public FWUser getUserByName(String userName) {
		// TODO Auto-generated method stub
		return (FWUser) getCurrentSession().createQuery("from FWUser where user_name = ?")
				.setString(0, userName)
				.uniqueResult();
	}

	@Override
	public FWUser getUserById(String userId) {
		// TODO Auto-generated method stub
		return (FWUser) getCurrentSession().createQuery("from FWUser where uuid = ?")
				.setString(0, userId)
				.uniqueResult();
	}

	@Override
	public List getUserACLById(String userId) {
		return getCurrentSession().createQuery("from Authority where user_id = ?")
				.setString(0, userId)
				.list();
	}

	@Override
	public int getActivedCount() {
		// TODO Auto-generated method stub
		return ((Long)getCurrentSession().createQuery("select count(*) from  FWUser where  actived = 1").uniqueResult()).intValue();
		
	}

	@Override
	public List getAllUsers(int start, int limit) {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("select uuid,user_name from  FWUser where  actived = 1 order by user_name asc")
				.setFirstResult(start)
				.setMaxResults(limit)
				.list();
	}

	@Override
	public List getAllUsers() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("select uuid,user_name from  FWUser where  actived = 1 order by user_name asc")
				.list();
	}
	
	
	public List getAdminEmail()
	{
		List emails = new ArrayList();
		String hql = "from FWUser where dba = 1 and actived = 1";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List l = query.list();
		for (Iterator iterator = l.iterator(); iterator.hasNext();)
		{
			User user = (User)iterator.next();
			if (!emails.contains(user.getEmail()) && user.getEmail() != null)
				emails.add(user.getEmail());
		}

		return emails;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		 return ((Long)getCurrentSession().createQuery("select count(u) from FWUser u").uniqueResult()).intValue();
	}

}
