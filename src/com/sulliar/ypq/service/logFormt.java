// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LogDataManagerService.java

package com.sulliar.ypq.service;


class logFormt
{

	String username;
	String itemid;
	String itemname;
	String lastPreviewTime;
	int previewTimes;
	String lastDownloadTime;
	int downloadTimes;
	String lastPrintTime;
	int printTimes;

	logFormt()
	{
		previewTimes = 0;
		downloadTimes = 0;
		printTimes = 0;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getItemid()
	{
		return itemid;
	}

	public void setItemid(String itemid)
	{
		this.itemid = itemid;
	}

	public String getItemname()
	{
		return itemname;
	}

	public void setItemname(String itemname)
	{
		this.itemname = itemname;
	}

	public String getLastPreviewTime()
	{
		return lastPreviewTime;
	}

	public void setLastPreviewTime(String lastPreviewTime)
	{
		this.lastPreviewTime = lastPreviewTime;
	}

	public int getPreviewTimes()
	{
		return previewTimes;
	}

	public void setPreviewTimes(int previewTimes)
	{
		this.previewTimes = previewTimes;
	}

	public String getLastDownloadTime()
	{
		return lastDownloadTime;
	}

	public void setLastDownloadTime(String lastDownloadTime)
	{
		this.lastDownloadTime = lastDownloadTime;
	}

	public int getDownloadTimes()
	{
		return downloadTimes;
	}

	public void setDownloadTimes(int downloadTimes)
	{
		this.downloadTimes = downloadTimes;
	}

	public String getLastPrintTime()
	{
		return lastPrintTime;
	}

	public void setLastPrintTime(String lastPrintTime)
	{
		this.lastPrintTime = lastPrintTime;
	}

	public int getPrintTimes()
	{
		return printTimes;
	}

	public void setPrintTimes(int printTimes)
	{
		this.printTimes = printTimes;
	}
}
