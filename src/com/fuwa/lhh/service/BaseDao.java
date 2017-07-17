package com.fuwa.lhh.service;

import java.util.List;

public interface BaseDao<T> {
	T getObjectById(Long id);
	void save(T t);
	void update(T t);
	void delete(T t);
	void deleteAll();
	List getAll();

}
