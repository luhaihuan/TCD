package com.fuwa.lhh.service;

import java.util.List;

import com.fuwa.lhh.model.Record;
import com.fuwa.ypq.model.Authority;
import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWUser;
import com.fuwa.ypq.model.Site;

public interface AuthorityManager extends BaseDao<Authority> {

    List getDepartACLByTypeId(String typeId);
    Authority  getAuthorityBySiteIdAndDepartId(String siteId,String departId);
    Authority  getAuthorityBySiteIdAndDepartId(String siteId,String departId,String typeId);
    int deleteDepartsByTypeId(String typeId);
	List getUsersACLByTypeId(String typeId);
	Authority  getAuthorityByUserId(String userId);
    void deleteUsersByTypeId(String typeId);
	String ExportACLExcel(String realpath);
	Authority getPersonACL(String userId, String type_id);
	Authority getPersonDepartACL(String userId, String type_id);
	Authority getUsersDepartACLById(String typeId,String userId);
	void deleteUsersACLByUserId(String userId);
	void deleteDepartsACLByDepartId(String departId);
	
	
	

    
}
