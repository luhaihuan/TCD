// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   FileDataManager.java

package com.sulliar.ypq.service;

import java.io.File;
import java.util.List;

import com.fuwa.lhh.model.GCQBOMModel;
import com.fuwa.lhh.model.MaterialAttrs;
import com.fuwa.lhh.model.MaterialTreeModel;
import com.fuwa.lhh.model.QDQBOMModel;
import com.fuwa.lhh.model.Stamp;
import com.sulliar.ypq.model.ItemModel;

public interface FileDataManager
{

	public List<ItemModel> getSearchFiles(String item_id_new,String item_id,String item_id_old, String item_name,
			String item_revision, String type,String username);

	public abstract String FileToSWFPath(File file, String s, String s1);

	public abstract List getAllPrinter();

	public abstract String getDAPath();
	public abstract void Copy(String s, String s1);

	public abstract List getWaterMark(String s, String s1);

	public abstract void PrintFile(File file, String s, String s1, String s2, String s3, String s4, String s5, 
			String s6, String s7, String s8);
	public abstract String DownloadFileto(File sourceFile, String dataset_type,
			String realpath,String username,String bgroup); 

	public abstract String DownloadFile(File file, String s, String s1, List<Stamp> stampList, String s3, String s4, String s5, boolean previewExcel);
	public List<MaterialAttrs> getQDQNormalAttrs(String item_id,String item_rev_id);
	public List<MaterialAttrs> getGCQCPNormalAttrs(String item_id,String item_rev_id);
	public List<MaterialAttrs> getGCQPartNormalAttrs(String item_id,String item_rev_id);
	public List<MaterialAttrs> getGCQPartClassAttrs(String item_id,String item_rev_id);
	public List<MaterialTreeModel> getParentMaterial(String item_id,String item_rev_id);
	public GCQBOMModel getGCQBOMTitle(String item_id,String item_rev_id);
	public QDQBOMModel getQDQBOMTitle(String item_id,String item_rev_id);
	public List<GCQBOMModel>  getGCQBOM(String item_id,String item_rev_id);
	public List<QDQBOMModel>  getQDQBOM(String item_id,String item_rev_id);
	public String getLastItemRev(String item_id);
}
