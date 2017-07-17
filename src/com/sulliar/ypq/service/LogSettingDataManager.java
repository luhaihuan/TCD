// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LogSettingDataManager.java

package com.sulliar.ypq.service;

import com.sulliar.ypq.model.LogSetting;
import java.util.List;

public interface LogSettingDataManager
{

	public abstract void add(LogSetting logsetting);

	public abstract void modify(LogSetting logsetting);

	public abstract void del(LogSetting logsetting);

	public abstract List findAllLogSetting();

	public abstract LogSetting findLogSettingByType(String s);
}
