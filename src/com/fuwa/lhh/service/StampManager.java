package com.fuwa.lhh.service;

import java.util.List;

import com.fuwa.lhh.model.Record;
import com.fuwa.lhh.model.SearchType;
import com.fuwa.lhh.model.Stamp;
import com.fuwa.ypq.model.Authority;
import com.fuwa.ypq.model.Claue;
import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWUser;
import com.fuwa.ypq.model.QueryType;
import com.fuwa.ypq.model.Site;

public interface StampManager extends BaseDao<Stamp> {

	List getStampByDepartName(String departName);
	
	List getStampInArray(String[] array);

	int getCount();
	List getStampsByOrder();

	Stamp getStampByDisyName(String waterm_name);

	Stamp getStampById(String id);

	List getStampAfterCK();


}
