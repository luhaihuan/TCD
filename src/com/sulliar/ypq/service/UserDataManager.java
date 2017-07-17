// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   UserDataManager.java

package com.sulliar.ypq.service;

import com.sulliar.ypq.model.User;
import java.util.List;

public interface UserDataManager
{

	public abstract void add(User user);

	public abstract void modify(User user);

	public abstract void del(User user);

	public abstract User findUserByName(String s);

	public abstract List getAllUsers(int i, int j);

	public abstract void delAllUsers();

	public abstract List getAllSite();

	public abstract List getAllGroup();

	public abstract List getAdminEmail();

	public abstract int getActivedCount();

	public abstract List getActivedUsers(int i, int j);

	public abstract List getTypeByUser(String s);
}
