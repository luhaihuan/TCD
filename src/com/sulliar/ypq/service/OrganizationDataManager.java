package com.sulliar.ypq.service;

import java.util.List;
import java.util.Set;

import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWUser;
import com.fuwa.ypq.model.Site;
import com.fuwa.ypq.model.TreeModel;

public interface OrganizationDataManager {
	public void addUser(FWUser user);
	public void modifyUser(FWUser user);
	public void delUser(FWUser user);
	public List<FWUser> getAllUser(int start,int limit);
	public FWUser findUserByName(String user_name);
	
	public void addDepartment(Department department);
	public void modifyDepartment(Department department);
	public void delDepartment(Department department);
	
	public void addSite(Site site);
	public void modifySite(Site site);
	public void delSite(Site site);
	public Site findSiteByName(String site_name);
	public Site findSiteById(String uuid);
	public Site findSiteById(String uuid,Set<Department> departs);
	public List<Site> getAllSites(int start,int limit);
	
	public List<TreeModel> getAllOrganization(String id, String nodeType);
	public List<TreeModel> getOrganizationSeal(String id, String nodeType);
	
	public int getTotalCount(String tablename);
	


	public Department findDepartById(String departId);
	

	
	public void modifyDepart(Department curentDepart);
	public void delDepart(Department bean);
	public Department findDepartById(Set<FWUser> users,String uuid);
	public Department getDepartById(String uuid);
	public FWUser findUserById(String uuid);
	public void modifyDepart(String departId, Set<FWUser> delUsers);
	List getDepartsBySiteId(String siteId, int start, int limit);
	List getUsersByDepartId(String departId, int start, int limit);
	public int getDepartCountBySiteId(String id);
	public int getUserCountBySiteId(String id);
	public Department getDepartBySiteIdAndDepartName(String siteId,
			String departName);
	public List<Department> findUserDepartsByUserId(String userId);
	FWUser findUserByNoOrName(String content);
	
	
	
	
	
	}
