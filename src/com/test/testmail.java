// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   testmail.java

package com.test;

import com.sulliar.ypq.utils.SpringBeanFactoryUtil;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class testmail
{

	public testmail()
	{
	}

	public static void main(String args[])
	{
		BeanFactory beanFactory = SpringBeanFactoryUtil.getInstance().getFactory();
		MailSender sender = (MailSender)beanFactory.getBean("mailSender");
		SimpleMailMessage mailMessage = (SimpleMailMessage)beanFactory.getBean("mailMessage");
		mailMessage.setSubject("ϵͳ��ʾ");
		mailMessage.setText("test�û�testday�������������Ѿ��ﵽ�趨������");
		mailMessage.setTo("kelly.Guo@accudyneindustries.com");
		sender.send(mailMessage);
	}
}
