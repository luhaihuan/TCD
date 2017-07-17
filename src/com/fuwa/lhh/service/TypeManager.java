package com.fuwa.lhh.service;

import java.util.List;

import com.fuwa.lhh.model.SearchType;
import com.fuwa.ypq.model.FWUser;



public interface TypeManager extends BaseDao<SearchType> {

    SearchType getTypeByName(String typeName);
    List getTypeByNameArray(String[] array);

    SearchType getTypeById(String typeId);
	int getCount();
	
	List getTypesByOrder();
	List getPersonTypesByOrder(FWUser fwUser);
}
