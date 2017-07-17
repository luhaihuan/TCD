// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ImportUserAcl.java

package com.test;

import com.sulliar.ypq.model.ACLModel;
import com.sulliar.ypq.model.User;
import com.sulliar.ypq.service.ACLDataManager;
import com.sulliar.ypq.service.UserDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;
import java.io.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.BeanFactory;

public class ImportUserAcl
{

	public ImportUserAcl()
	{
	}

	public static void main(String args[])
	{
		JFileChooser filechooser = new JFileChooser();
		filechooser.setCurrentDirectory(new File("c:/"));
		filechooser.setAcceptAllFileFilterUsed(false);
		filechooser.setFileFilter(new FileFilter() {

			public boolean accept(File f)
			{
				return f.getName().endsWith(".xlsx") || f.isDirectory();
			}

			public String getDescription()
			{
				return "Excel文件(*.xlsx)";
			}

		});
		filechooser.setApproveButtonText("open");
		filechooser.setDialogTitle("open file");
		int returnVal = filechooser.showOpenDialog(new JFrame());
		if (returnVal == 0)
		{
			final String filename = filechooser.getSelectedFile().getAbsolutePath();
			System.out.println((new StringBuilder("You chose to open this file: ")).append(filename).toString());
			(new Thread() {

			
				public void run()
				{
					ImportUserAcl.doProcess(filename);
					JOptionPane.showMessageDialog(null, "import ok", "tip", 1);
				}

			
		
			}).start();
		}
	}

	protected static void doProcess(String filename)
	{
		try
		{
			BeanFactory factory = SpringBeanFactoryUtil.getInstance().getFactory();
			UserDataManager userManager = (UserDataManager)factory.getBean("UserDataManager");
			ACLDataManager aclManager = (ACLDataManager)factory.getBean("ACLDataManager");
			FileInputStream fileIn = new FileInputStream(filename);
			XSSFWorkbook wb = new XSSFWorkbook(fileIn);
			Sheet sheet1 = wb.getSheet("Sheet1");
			Sheet sheet2 = wb.getSheet("Sheet2");
			int rowcount = sheet1.getPhysicalNumberOfRows();
			for (int r = 1; r < rowcount; r++)
			{
				Row row = sheet1.getRow(r);
				String username = row.getCell(0).getStringCellValue().toLowerCase();
				String s1 = row.getCell(1).getStringCellValue();
				String s2 = row.getCell(2).getStringCellValue();
				String s3 = row.getCell(3).getStringCellValue();
				String s4 = row.getCell(4).getStringCellValue();
				String s5 = row.getCell(5).getStringCellValue();
				User user = userManager.findUserByName(username);
				if (user != null)
				{
					if (s1.equals("是"))
						user.setIsLDAP(true);
					else
						user.setIsLDAP(false);
					if (s2.equals("是"))
						user.setIsAdmin(true);
					else
						user.setIsAdmin(false);
					user.setEmail(s3);
					user.setSite(s4);
					user.setBgroup(s5);
					userManager.modify(user);
				} else
				{
					User u = new User();
					u.setName(username);
					if (s1.equals("是"))
						u.setIsLDAP(true);
					else
						u.setIsLDAP(false);
					if (s2.equals("是"))
						u.setIsAdmin(true);
					else
						u.setIsAdmin(false);
					u.setEmail(s3);
					u.setSite(s4);
					u.setBgroup(s5);
					u.setActived(true);
					userManager.add(u);
				}
			}

			int rowcount1 = sheet2.getPhysicalNumberOfRows();
			for (int r = 1; r < rowcount1; r++)
			{
				Row row = sheet2.getRow(r);
				String username = row.getCell(0).getStringCellValue().toLowerCase();
				String s1 = row.getCell(1).getStringCellValue();
				String s2 = row.getCell(2).getStringCellValue();
				String s3 = row.getCell(3).getStringCellValue();
				String s4 = row.getCell(4).getStringCellValue();
				String s5 = row.getCell(5).getStringCellValue();
				String s6 = row.getCell(6).getStringCellValue();
				String s7 = row.getCell(7).getStringCellValue();
				String s8 = row.getCell(8).getStringCellValue();
				String s9 = row.getCell(9).getStringCellValue();
				String s10 = row.getCell(10).getStringCellValue();
				ACLModel model = aclManager.findACLByTypeAndUser(s1, username);
				if (model != null)
				{
					if (s2.equals("是"))
						model.setReadWord(true);
					else
						model.setReadWord(false);
					if (s3.equals("是"))
						model.setPrintWord(true);
					else
						model.setPrintWord(false);
					if (s4.equals("是"))
						model.setDownloadWord(true);
					else
						model.setDownloadWord(false);
					if (s5.equals("是"))
						model.setReadExcel(true);
					else
						model.setReadExcel(false);
					if (s6.equals("是"))
						model.setPrintExcel(true);
					else
						model.setPrintExcel(false);
					if (s7.equals("是"))
						model.setDownloadExcel(true);
					else
						model.setDownloadExcel(false);
					if (s8.equals("是"))
						model.setReadPDF(true);
					else
						model.setReadPDF(false);
					if (s9.equals("是"))
						model.setPrintPDF(true);
					else
						model.setPrintPDF(false);
					if (s10.equals("是"))
						model.setDownloadPDF(true);
					else
						model.setDownloadPDF(false);
					aclManager.modify(model);
				} else
				{
					ACLModel m = new ACLModel();
					m.setUsername(username);
					m.setTypeStr(s1);
					if (s2.equals("是"))
						m.setReadWord(true);
					else
						m.setReadWord(false);
					if (s3.equals("是"))
						m.setPrintWord(true);
					else
						m.setPrintWord(false);
					if (s4.equals("是"))
						m.setDownloadWord(true);
					else
						m.setDownloadWord(false);
					if (s5.equals("是"))
						m.setReadExcel(true);
					else
						m.setReadExcel(false);
					if (s6.equals("是"))
						m.setPrintExcel(true);
					else
						m.setPrintExcel(false);
					if (s7.equals("是"))
						m.setDownloadExcel(true);
					else
						m.setDownloadExcel(false);
					if (s8.equals("是"))
						m.setReadPDF(true);
					else
						m.setReadPDF(false);
					if (s9.equals("是"))
						m.setPrintPDF(true);
					else
						m.setPrintPDF(false);
					if (s10.equals("是"))
						m.setDownloadPDF(true);
					else
						m.setDownloadPDF(false);
					aclManager.add(m);
				}
			}

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
