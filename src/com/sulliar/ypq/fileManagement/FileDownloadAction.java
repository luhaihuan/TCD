// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   FileDownloadAction.java

package com.sulliar.ypq.fileManagement;

import com.opensymphony.xwork2.ActionSupport;

import java.io.*;
import java.net.URLDecoder;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

public class FileDownloadAction extends ActionSupport
{

	private static final long serialVersionUID = 1L;
	private String inputPath;
	private String fileName;
	String dsname;

	public FileDownloadAction()
	{
	}

	public void setInputPath(String inputPath)
	{
		this.inputPath = inputPath;
	}

	public String getInputPath()
	{
		return inputPath;
	}

	public String getFileName()
	{
		return fileName;
	}

	public void setFileName(String fileName) throws UnsupportedEncodingException
	{
		
		this.fileName = fileName;
	}

	public String getDsname()
	{
		return dsname;
	}

	public void setDsname(String dsname)
	{
		this.dsname = dsname;
	}

	public String getDownloadFileName() throws UnsupportedEncodingException
	{
		
		String suffix = inputPath.substring(inputPath.lastIndexOf("."), inputPath.length());
		
		System.out.println(" getDownloadFileName dsname-->"+dsname);
		dsname = new String(dsname.getBytes(), "ISO8859-1");//�����������������
		if (dsname.endsWith(suffix))
		{
			return dsname;
		} else
		{
			String downFileName = (new StringBuilder(String.valueOf(dsname))).append(suffix).toString();
			return downFileName;
		}
	}

	public InputStream getTargetFile()
		throws Exception
	{
		
		System.out.println(inputPath);
		System.out.println(" getTargetFile inputPath-->"+inputPath);
		String realpath = ServletActionContext.getServletContext().getRealPath((new StringBuilder("HFTempFiles/")).append(inputPath).toString());
		return new BufferedInputStream(new FileInputStream(realpath));
	}

	public String execute()
		throws Exception
	{
		
		inputPath = URLDecoder.decode(inputPath, "utf-8");
		dsname = URLDecoder.decode(dsname, "utf-8");
		System.out.println(toString());
		return "success";
	}

	@Override
	public String toString() {
		return "FileDownloadAction [inputPath=" + inputPath + ", fileName="
				+ fileName + ", dsname=" + dsname + "]";
	}
	
}
