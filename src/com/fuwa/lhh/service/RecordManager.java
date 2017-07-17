package com.fuwa.lhh.service;

import java.util.List;

import com.fuwa.lhh.model.Record;

public interface RecordManager extends BaseDao<Record> {

	List getRecordList(String user_name);

	Record getRecordModel(String user_name,String type_name, String item_id, String item_rev);

	void deleteOldData();

}
