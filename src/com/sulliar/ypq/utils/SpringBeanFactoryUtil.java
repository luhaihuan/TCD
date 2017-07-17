// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SpringBeanFactoryUtil.java

package com.sulliar.ypq.utils;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringBeanFactoryUtil
{
	private static class SpringBeanFactoryUtilHolder
	{

		private static final SpringBeanFactoryUtil INSTANCE = new SpringBeanFactoryUtil(null);



		private SpringBeanFactoryUtilHolder()
		{
		}
	}


	BeanFactory beanFactory;

	private SpringBeanFactoryUtil()
	{
	}

	public static SpringBeanFactoryUtil getInstance()
	{
		return SpringBeanFactoryUtilHolder.INSTANCE;
	}

	public BeanFactory getFactory()
	{
		if (beanFactory == null)
			beanFactory = new ClassPathXmlApplicationContext("applicationContext-*.xml");
		return beanFactory;
	}

	SpringBeanFactoryUtil(SpringBeanFactoryUtil springbeanfactoryutil)
	{
		this();
	}
}
