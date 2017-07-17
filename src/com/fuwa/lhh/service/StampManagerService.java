package com.fuwa.lhh.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fuwa.lhh.model.SearchType;
import com.fuwa.lhh.model.Stamp;
import com.fuwa.ypq.model.Claue;
import com.fuwa.ypq.model.QueryType;







@Service
public class StampManagerService extends BaseService<Stamp> implements StampManager{

	@Override
	public List getStampByDepartName(String departName) {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from Stamp where depart_name = ?")
				.setString(0, departName)
				.list();
	}

	@Override
	public int getCount() {
		return ((Long) getCurrentSession().createQuery("select count(*) from Stamp ")			
				.uniqueResult()).intValue();
	}

	@Override
	public Stamp getStampByDisyName(String waterm_name) {
		// TODO Auto-generated method stub
		return (Stamp) getCurrentSession().createQuery(" from Stamp where stamp_disy_name = ? ")
				.setString(0, waterm_name)
				.uniqueResult();
	}

	@Override
	public List getStampsByOrder() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("from Stamp order by cast(stamp_id as integer) asc ").list();
	}

	@Override
	public Stamp getStampById(String id) {
		// TODO Auto-generated method stub
		return (Stamp) getCurrentSession().createQuery("from Stamp where stamp_id = ?")
			   .setString(0, id)
			   .uniqueResult();
	}

	@Override
	public List getStampAfterCK() {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("select s from Stamp s where stamp_id >= 6")
						.list();
	}

	@Override
	public List getStampInArray(String[] array) {
		// TODO Auto-generated method stub
		return getCurrentSession().createQuery("select s from Stamp s where stamp_disy_name in (:alist)")
				.setParameterList("alist", array)
				.list();
	}


}
