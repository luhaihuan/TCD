// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LogDataManager.java

package com.sulliar.ypq.service;

import com.fuwa.ypq.model.FWLog;
import com.sulliar.ypq.model.Log;

import java.util.Date;
import java.util.List;

public interface LogDataManager
{

	public abstract boolean add(Log log);

	public abstract List getAllLogs(int i, int j);

	public abstract int getTotalCount(String s);

	public abstract String exportLogByGroup(Date date, Date date1, String s, String s1);

	public abstract String exportLogByItem(Date date, Date date1, String s, String s1);

	public abstract String exportLogByUser(Date date, Date date1, String s, String s1);
	
	public void add(FWLog log);
}
