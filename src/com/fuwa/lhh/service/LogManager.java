package com.fuwa.lhh.service;

import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;

import com.fuwa.lhh.model.Record;
import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWLog;
import com.fuwa.ypq.model.FWUser;
import com.fuwa.ypq.model.Site;

public interface LogManager extends BaseDao<FWLog> {

	boolean saveFWLog(FWLog object);

	List getAllLogs(int start, int limit);

	int getTotalCount(String string);

	String exportLogByUser(Date from_date, Date to_date, String username,
			String realpath);

	String exportLogByGroup(Date from_date, Date to_date, String bgroup,
			String realpath);

	String exportLogByItem(Date from_date, Date to_date, String itemid,
			String realpath);

	void exportLogFormatByUser(List logs1, Sheet sheet);

	void exportLogFormatByItemid(List logs1, Sheet sheet);

	logFormt findInLogFormat(List lfs, String itemid, String username);

	void SendEmail(String username, String date);

	List<String> getUserOverDownloadLogs();

	



	
	
}
