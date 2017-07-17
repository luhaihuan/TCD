package com.fuwa.lhh.service;


import java.util.List;

import com.fuwa.ypq.model.Authority;
import com.fuwa.ypq.model.Department;


public interface DepartManager extends BaseDao<Department> {

    Department getDepartByName(String departName);
    Department getDepartById(String departId);
	
    Authority getUsersDepartACLById(String typeId,String userId);
	List getDepartACLById(String siteId, String departId);

	List getUsersByDepartId(String departId);
	List getUsersByDepartId(String departId, int start, int limit);
	int getUserCountByDepartId(String departId);
	Object[]  getSiteIdandDepartId(String siteName,String departName);
	Department  getPersonDepartByUserId(String userId);
	List getAllDepart(); 
}
