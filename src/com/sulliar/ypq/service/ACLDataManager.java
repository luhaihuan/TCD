// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ACLDataManager.java

package com.sulliar.ypq.service;

import com.sulliar.ypq.model.ACLModel;
import java.util.List;

public interface ACLDataManager
{

	public abstract void add(ACLModel aclmodel);

	public abstract void modify(ACLModel aclmodel);

	public abstract void del(ACLModel aclmodel);

	public abstract List findACLByType(String s, int i, int j);

	public abstract List findACLByAttrs(String s, String s1, String s2, int i, int j);

	public abstract int getTotalCount(String s);

	public abstract int getTotalCount(String s, String s1, String s2);

	public abstract ACLModel findACLByTypeAndUser(String s, String s1);

	public abstract void delACLbyType(String s);

	public abstract String ExportACLExcel(String s);
}
