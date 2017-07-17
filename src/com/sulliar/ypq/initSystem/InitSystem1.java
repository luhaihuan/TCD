// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   InitSystem1.java

package com.sulliar.ypq.initSystem;

import com.sulliar.ypq.model.User;
import com.sulliar.ypq.service.UserDataManager;
import java.io.PrintStream;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class InitSystem1
{

	public InitSystem1()
	{
	}

	public void test()
	{
		BeanFactory beanFactory = new ClassPathXmlApplicationContext("applicationContext-*.xml");
		UserDataManager userManager = (UserDataManager)beanFactory.getBean("UserDataManager");
		userManager.delAllUsers();
		User user = userManager.findUserByName("infodba");
		if (user != null)
		{
			System.out.println("infodba found");
			user.setName("infodba");
			user.setPwd("infodba");
			user.setIsAdmin(true);
			user.setIsLDAP(false);
			userManager.modify(user);
		} else
		{
			System.out.println("can not find infodba, start to add infodba user");
			User infodba = new User();
			infodba.setName("infodba");
			infodba.setPwd("infodba");
			infodba.setIsAdmin(true);
			infodba.setIsLDAP(false);
			userManager.add(infodba);
		}
		User user1 = userManager.findUserByName("Administrator");
		if (user1 != null)
		{
			System.out.println("Administrator found");
			user1.setName("Administrator");
			user1.setIsAdmin(false);
			user1.setIsLDAP(true);
			userManager.modify(user1);
		} else
		{
			System.out.println("can not find Administrator, start to add Administrator user");
			User Administrator = new User();
			Administrator.setName("Administrator");
			Administrator.setIsAdmin(false);
			Administrator.setIsLDAP(true);
			userManager.add(Administrator);
		}
	}
}
