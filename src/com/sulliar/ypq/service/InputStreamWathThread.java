// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   InputStreamWathThread.java

package com.sulliar.ypq.service;

import java.io.*;

public class InputStreamWathThread extends Thread
{

	private Process process;
	private boolean over;

	public InputStreamWathThread(Process p)
	{
		process = null;
		over = false;
		process = p;
		over = false;
	}

	public void run()
	{
		if (process == null)
			return;
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(), "GB18030"));
			String temp;
			while (process != null && !over) 
				while ((temp = br.readLine()) != null) 
					System.out.println((new StringBuilder("输入流信息:")).append(temp).toString());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return;
	}

	public void setOver(boolean over)
	{
		this.over = over;
	}
}
