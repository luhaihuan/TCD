package com.fuwa.lhh.service;

import java.util.List;

import com.fuwa.lhh.model.Record;
import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWUser;
import com.fuwa.ypq.model.Site;

public interface UserManager extends BaseDao<FWUser> {

	 FWUser getUserByName(String userName);
	 FWUser getUserById(String userId);
	 List getUserACLById(String userId);
	int getActivedCount();
	List getAllUsers(int start, int limit);
	List getAllUsers();
	List getAdminEmail();
	int getCount();
	
}
