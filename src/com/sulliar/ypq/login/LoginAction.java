// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   LoginAction.java

package com.sulliar.ypq.login;


import com.fuwa.lhh.model.SearchType;
import com.fuwa.lhh.model.Stamp;
import com.fuwa.lhh.service.AuthorityManager;
import com.fuwa.lhh.service.DepartManager;
import com.fuwa.lhh.service.StampManager;
import com.fuwa.lhh.service.TypeManager;
import com.fuwa.lhh.service.UserManager;
import com.fuwa.ypq.model.Authority;
import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.User;
import com.sulliar.ypq.service.OrganizationDataManager;
import com.sulliar.ypq.service.UserDataManager;
import com.sulliar.ypq.utils.LDAPHelper;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;
import com.sun.mail.iap.Response;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.naming.directory.DirContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;


public class LoginAction extends ActionSupport
{

	private static final long serialVersionUID = 1L;
	private boolean success;
	private String message;
	private FWUser user;
	private boolean rm;
	private HttpServletResponse response;
	
	@Resource
	TypeManager typeManager;
	
	@Resource
	StampManager stampManager;
	
	@Resource
	DepartManager departManager;
	

	@Resource
	UserManager userManager;
	
	
	@Resource
	AuthorityManager authorityManager;


	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public boolean isRm() {
		return rm;
	}

	public void setRm(boolean rm) {
		this.rm = rm;
	}

	public LoginAction()
	{
		String filePath = ServletActionContext.getServletContext().getRealPath("/TypeData/stamp-data.xlsx");
		System.out.println("LoginAction initSystem--->filePath--->"+filePath);
		
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

	public FWUser getUser()
	{
		return user;
	}

	public void setUser(FWUser user)
	{
		this.user = user;
	}

	@Resource()
	OrganizationDataManager organizationDataManager;
	
	public String execute()
		throws Exception
	{
		
		FWUser tlg = organizationDataManager.findUserByNoOrName(user.getUser_name());		
				
		if (tlg != null)
		{  
			
			initTypeData();
			initStampData();
			initUserData();
			//insertNewData();
			if(tlg.getUser_pwd().equals(user.getUser_pwd()) && tlg.isActived()){
				success = true;
				ActionContext context = ActionContext.getContext();
				context.getSession().put("current_user", tlg);
				Department departByUserId = departManager.getPersonDepartByUserId(tlg.getUuid());
				context.getSession().put("current_depart", departByUserId);
				
				user = tlg;
				if(rm){
					String un = tlg.getUser_name();
					Cookie c=new Cookie("username", un);
					c.setMaxAge(30*24*3600);
					response.addCookie(c);
					
				}
			}else
			{
				success = false;
				message = "对不起，未授权的用户不能登录该系统";
			}
		} else
		{
			success = false;
			message = "对不起，未授权的用户不能登录该系统";
		}
		return "success";
	}
	public void insertNewData() {

		
         List<String> typeCode = new ArrayList<String>();
		try {
			String filePath = ServletActionContext.getServletContext().getRealPath("/initData/newACL.xlsx");
			Workbook workbook=WorkbookFactory.create(new FileInputStream(filePath));// 兼容
			Sheet sheet = workbook.getSheetAt(1);
			for(int r=1;r<= sheet.getLastRowNum();r++) {
				Row row = sheet.getRow(r);		
				String type_name = null;				
				for(int c=0;c<row.getLastCellNum();c++) {				
					Cell cell = row.getCell(c);
					System.out.print(getCellValue(cell)+" ");					
					switch (c) {
					case 0:
						type_name = getCellValue(cell);
						String type_id = typeManager.getTypeByName(type_name).getType_id();
						typeCode.add(type_id);
						break;

					default:
						break;
					}
					
				}	
			}
			
			List<Authority> au = new ArrayList<Authority>();
			List<String> userNameList = new ArrayList<String>();
			 sheet = workbook.getSheetAt(0);
			for(int r=1;r<= sheet.getLastRowNum();r++) {
				Row row = sheet.getRow(r);		
				String user_name = null;
				for(int c=0;c<row.getLastCellNum();c++) {				
					Cell cell = row.getCell(c);
					System.out.print(getCellValue(cell)+" ");					
					switch (c) {
					case 0:
						user_name = getCellValue(cell);	
						userNameList.add(user_name);
						break;
					default:
						break;
					}
					
					
					
					
				}	
			}
			System.out.println("userNameList:"+userNameList.size());
			System.out.println("typeCode:"+typeCode.size());
			for(String u : userNameList) {
				for(String type : typeCode) {
					Authority authority = new Authority(true, true, true, true, true, true);
					authority.setUser_id(userManager.getUserByName(u).getUuid());
					authority.setQuery_type_id(type);
					//authorityManager.save();
					au.add(authority);
				}
			}
			System.out.println("hello");
			System.out.println("size:"+au.size());
			for(Authority a : au) {
				//System.out.println(au);
				authorityManager.save(a);
			}
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void initTypeData() {

		if(typeManager.getCount()>0) {
			System.out.println("have TypeData no insert-------------------->");
			return;
		}
		typeManager.deleteAll();

		try {
			String filePath = ServletActionContext.getServletContext().getRealPath("/initData/search-type-data.xlsx");
			
			
			
			Workbook workbook=WorkbookFactory.create(new FileInputStream(filePath));// 兼容
			Sheet sheet = workbook.getSheetAt(0);
			for(int i=0;i<= sheet.getLastRowNum();i++) {
				Row row = sheet.getRow(i);
				String type_id = null;
				String type_name = null;
				String type_code1 = null;
				String type_code2 = null;
				String rev_ds_rel = null;
				Boolean have_old_code = null;
				for(int j=0;j<row.getLastCellNum();j++) {				
					Cell cell=row.getCell(j);
					System.out.print(getCellValue(cell)+" ");
					
					switch (j) {
					case 0:
						type_id = getCellValue(cell);
						break;
					case 1:
						type_name = getCellValue(cell);					
						break;
					case 2:
						type_code1 = getCellValue(cell);	
						break;
					case 3:
						type_code2 = getCellValue(cell);	 
						break;
					case 4:
						rev_ds_rel = getCellValue(cell);	 
						break;
                    case 5:
                    	have_old_code = Boolean.parseBoolean(getCellValue(cell));
						break;

					default:
						break;
					}
					
				}
				SearchType searchType = new SearchType(type_id, type_name, type_code1, type_code2, rev_ds_rel, have_old_code);
				System.out.println(searchType);
				typeManager.save(searchType);
				
				
				System.out.println();
				
			}
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void initStampData() {
	
		if(stampManager.getCount()>0) {
			System.out.println("have StampData no insert-------------------->");
			return;
		}
		stampManager.deleteAll();

		try {
			String filePath = ServletActionContext.getServletContext().getRealPath("/initData/stamp-data.xlsx");							
			Workbook workbook=WorkbookFactory.create(new FileInputStream(filePath));// 兼容
			Sheet sheet = workbook.getSheetAt(0);
			for(int i=0;i<= sheet.getLastRowNum();i++) {
				Row row = sheet.getRow(i);
				String stamp_id = null;
				String stamp_disy_name = null;
				String stamp_true_name = null;
				String depart_name = null;
			
				for(int j=0;j<row.getLastCellNum();j++) {				
					Cell cell=row.getCell(j);
					System.out.print(getCellValue(cell)+" ");
					
					switch (j) {
					case 0:
						stamp_id = getCellValue(cell);
						break;
					case 1:
						stamp_disy_name = getCellValue(cell);
						break;
					case 2:
						stamp_true_name = getCellValue(cell);					
						break;
					case 3:
						depart_name = getCellValue(cell);	
						break;
					
					default:
						break;
					}
					
				}
				Stamp stamp =new Stamp(stamp_id, stamp_disy_name, stamp_true_name, depart_name);
				System.out.println(stamp);
				stampManager.save(stamp);												
			}
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void initUserData() {
		
		if(userManager.getCount() > 1) {
			System.out.println("have UserData dbaData no insert-------------------->");
			return;
		}
		///userManager.deleteAll();

		try {
			String filePath = ServletActionContext.getServletContext().getRealPath("/initData/user-data.xlsx");							
			Workbook workbook=WorkbookFactory.create(new FileInputStream(filePath));// 兼容
			Sheet sheet = workbook.getSheetAt(0);
			for(int i=0;i<= sheet.getLastRowNum();i++) {
				Row row = sheet.getRow(i);
				String user_no = null;
				String user_name = null;
				String user_pwd = null;				
				boolean OA = false;
				boolean dba = false;
				boolean actived = false;
				System.out.print("read excel--->");
				for(int j=0;j<row.getLastCellNum();j++) {				
					Cell cell=row.getCell(j);
					System.out.print(getCellValue(cell)+" ");
					
					switch (j) {
					case 0:
						user_no = getCellValue(cell);
						break;
					case 1:
						user_name = getCellValue(cell);
						break;
					case 2:
						user_pwd = getCellValue(cell);					
						break;
					case 3:
						OA = Boolean.parseBoolean(getCellValue(cell));  
						break;
					case 4:
						dba = Boolean.parseBoolean(getCellValue(cell));  
						break;
					case 5:
						actived = Boolean.parseBoolean(getCellValue(cell));  
						break;
					
					default:
						break;
					}
					
				}
				FWUser fwUser = new FWUser(user_no, user_name, user_pwd, actived, OA, dba);
				System.out.println(fwUser);
				userManager.save(fwUser);												
			}
		} catch (InvalidFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String getCellValue(Cell cell) {
		String value;
		switch (cell.getCellType()) {	
		case Cell.CELL_TYPE_FORMULA :
			System.out.print(cell.getCellFormula()+" ");
			
			try {
				value = String.valueOf(cell.getNumericCellValue());
			} catch (IllegalStateException e) {
				value = String.valueOf(cell.getRichStringCellValue());
			}			
			break;
		case Cell.CELL_TYPE_NUMERIC :
			value = String.format("%.0f", cell.getNumericCellValue()) ;			
			break;
		case Cell.CELL_TYPE_STRING :
			value = cell.getStringCellValue();
			
			break;
		case Cell.CELL_TYPE_BOOLEAN :
			value = String.valueOf(cell.getBooleanCellValue());
			break;
		case Cell.CELL_TYPE_BLANK :
			value = "";
			break;
		default :
			value = "unknown";
			break;
		}
		return value;
	}

}
