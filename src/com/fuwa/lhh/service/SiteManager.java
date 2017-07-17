package com.fuwa.lhh.service;

import java.util.List;

import com.fuwa.lhh.model.Record;
import com.fuwa.ypq.model.Site;

public interface SiteManager extends BaseDao<Site> {

	Site getSiteByName(String siteName);
	Site getSiteById(String siteId);
	List getSiteACLById(String siteId);
	Site getDepartsSiteByDepartId(String departId);
	
	int getDepartCountBySiteId(String siteId);
	List getDepartBySiteId(String siteId);
	List getDepartBySiteId(String siteId, int start, int limit);
	List getAllSite();
    
}
