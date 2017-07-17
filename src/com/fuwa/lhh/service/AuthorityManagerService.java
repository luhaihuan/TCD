package com.fuwa.lhh.service;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Query;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;









import com.fuwa.lhh.model.SearchType;
import com.fuwa.ypq.model.Authority;
import com.fuwa.ypq.model.Department;
import com.fuwa.ypq.model.FWUser;
import com.fuwa.ypq.model.QueryType;
import com.fuwa.ypq.model.Site;
import com.sulliar.ypq.model.ACLModel;
import com.sulliar.ypq.model.User;





@Service
public class AuthorityManagerService extends BaseService<Authority> implements AuthorityManager{

	@Resource
	DepartManager departManager;
	@Resource
	SiteManager siteManager;
	@Resource
	UserManager userManager;
	
	@Resource
	TypeManager typeManager;
	
	

	public AuthorityManagerService() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public List getDepartACLByTypeId(String typeId) {
		// TODO Auto-generated method stub
	/*	QueryType type = typeManager.getTypeByName(typeName);
		System.out.println("type------->"+type.getQuery_type_name()+" id-->"+type.getUuid());
		if(type==null)
			return Collections.EMPTY_LIST;*/
		return getCurrentSession().createQuery("from Authority where group_id is not null and query_type_id = ?")
				.setString(0,typeId)
				.list();
	}
	@Override
	public Authority getAuthorityBySiteIdAndDepartId(String siteId,
			String departId) {
		// TODO Auto-generated method stub
		Authority uniqueResult = (Authority) getCurrentSession().createQuery("from Authority where site_id = ? and group_id =? ")
				.setString(0, siteId)
				.setString(1, departId)
				.uniqueResult();
		return uniqueResult;
	}
	@Override
	public List getUsersACLByTypeId(String typeId) {
		// TODO Auto-generated method stub
/*		QueryType type = typeManager.getTypeByName(typeName);
		System.out.println("type------->"+type.getQuery_type_name()+" id-->"+type.getUuid());
		if(type==null)
			return Collections.EMPTY_LIST;*/

		return getCurrentSession().createQuery("from Authority where user_id is not null and query_type_id = ?")
				.setString(0, typeId)
				.list();
	}
	@Override
	public Authority getAuthorityByUserId(String userId) {
		// TODO Auto-generated method stub
		Authority uniqueResult = (Authority) getCurrentSession().createQuery("from Authority where user_id = ? ")
				.setString(0, userId)
				.uniqueResult();
		return uniqueResult;
	}
	@Override
	public int deleteDepartsByTypeId(String typeId) {
		// TODO Auto-generated method stub
	return	getCurrentSession().createQuery("delete from Authority where group_id is not null and query_type_id = ? ")
		.setString(0, typeId)
		.executeUpdate();
		
	}
	@Override
	public void deleteUsersByTypeId(String typeId) {	
		// TODO Auto-generated method stub
		getCurrentSession().createQuery("delete from Authority where user_id is not null and query_type_id = ? ")
		.setString(0, typeId)
		.executeUpdate();
	}
	
	
	
	
	
	
	
	@Override
	public String ExportACLExcel(String realpath)
	{
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String filepath = (new StringBuilder(String.valueOf(realpath))).append("HFTempFiles/ACLDATA").append(sdf.format(new Date())).append(".xlsx").toString();
		String hqlDepart = "select a from Authority a where user_id is null order by site_id asc,group_id asc";
		String hqlUser = "select a from Authority a where user_id is not null order by user_id asc";
		Query qDepart = getCurrentSession().createQuery(hqlDepart);
		List<Authority> departAcls = qDepart.list();
		
		Query qUser = getCurrentSession().createQuery(hqlUser);
		List<Authority> userAcls = qUser.list();
		
		try
		{
			XSSFWorkbook wb = new XSSFWorkbook();
			SXSSFWorkbook swb = new SXSSFWorkbook(wb, -1);
			Sheet sheetDepart = swb.createSheet("部门权限");
			CellStyle cellStyle = wb.createCellStyle();
			cellStyle.setBorderLeft((short)1);
			cellStyle.setBorderBottom((short)1);
			cellStyle.setBorderRight((short)1);
			cellStyle.setBorderTop((short)1);
			Row row = sheetDepart.createRow(0);
			Cell cellSite = row.createCell(0);
			cellSite.setCellStyle(cellStyle);
			cellSite.setCellValue("站点");
			
			Cell cellDepart = row.createCell(1);
			cellDepart.setCellStyle(cellStyle);
			cellDepart.setCellValue("部门");
			
			
			Cell cellFileType = row.createCell(2);
			cellFileType.setCellStyle(cellStyle);
			cellFileType.setCellValue("文件类型");
	
			Cell cellPrintWord = row.createCell(3);
			cellPrintWord.setCellStyle(cellStyle);
			cellPrintWord.setCellValue("打印Word");
			
			Cell cellDownloadWord = row.createCell(4);
			cellDownloadWord.setCellStyle(cellStyle);
			cellDownloadWord.setCellValue("下载Word");
		
			Cell cellPrintExcel = row.createCell(5);
			cellPrintExcel.setCellStyle(cellStyle);
			cellPrintExcel.setCellValue("打印Excel");
			
			Cell cellDownloadExcel = row.createCell(6);
			cellDownloadExcel.setCellStyle(cellStyle);
			cellDownloadExcel.setCellValue("下载Excel");
		
			Cell cellPrintPDF = row.createCell(7);
			cellPrintPDF.setCellStyle(cellStyle);
			cellPrintPDF.setCellValue("打印PDF");
			
			Cell cellDownloadPDF = row.createCell(8);
			cellDownloadPDF.setCellStyle(cellStyle);
			cellDownloadPDF.setCellValue("下载PDF");
			
			
			
			Cell cellBOMSearch = row.createCell(9);
			cellBOMSearch.setCellStyle(cellStyle);
			cellBOMSearch.setCellValue("BOM查询");
			
			Cell cellMaterialSearch = row.createCell(10);
			cellMaterialSearch.setCellStyle(cellStyle);
			cellMaterialSearch.setCellValue("物料属性查询");
			
			Cell cellParentSearch = row.createCell(11);
			cellParentSearch.setCellStyle(cellStyle);
			cellParentSearch.setCellValue("物料反查");
			
///////////////////////////////////////////////////////////////////////////////////user			
			
			Sheet sheetUser = swb.createSheet("用户权限");
		
			Row rowUserTitle = sheetUser.createRow(0);
			Cell cellUser = rowUserTitle.createCell(0);
			cellUser.setCellStyle(cellStyle);
			cellUser.setCellValue("用户");
			

			
			
			Cell cellFileType1 = rowUserTitle.createCell(1);
			cellFileType1.setCellStyle(cellStyle);
			cellFileType1.setCellValue("文件类型");
	
			Cell cellPrintWord1 = rowUserTitle.createCell(2);
			cellPrintWord1.setCellStyle(cellStyle);
			cellPrintWord1.setCellValue("打印Word");
			
			Cell cellDownloadWord1 = rowUserTitle.createCell(3);
			cellDownloadWord1.setCellStyle(cellStyle);
			cellDownloadWord1.setCellValue("下载Word");
		
			Cell cellPrintExcel1 = rowUserTitle.createCell(4);
			cellPrintExcel1.setCellStyle(cellStyle);
			cellPrintExcel1.setCellValue("打印Excel");
			
			Cell cellDownloadExcel1 = rowUserTitle.createCell(5);
			cellDownloadExcel1.setCellStyle(cellStyle);
			cellDownloadExcel1.setCellValue("下载Excel");
		
			Cell cellPrintPDF1 = rowUserTitle.createCell(6);
			cellPrintPDF1.setCellStyle(cellStyle);
			cellPrintPDF1.setCellValue("打印PDF");
			
			Cell cellDownloadPDF1 = rowUserTitle.createCell(7);
			cellDownloadPDF1.setCellStyle(cellStyle);
			cellDownloadPDF1.setCellValue("下载PDF");
			
			
			
			Cell cellBOMSearch1 = rowUserTitle.createCell(8);
			cellBOMSearch1.setCellStyle(cellStyle);
			cellBOMSearch1.setCellValue("BOM查询");
			
			Cell cellMaterialSearch1 = rowUserTitle.createCell(9);
			cellMaterialSearch1.setCellStyle(cellStyle);
			cellMaterialSearch1.setCellValue("物料属性查询");
			
			Cell cellParentSearch1 = rowUserTitle.createCell(10);
			cellParentSearch1.setCellStyle(cellStyle);
			cellParentSearch1.setCellValue("物料反查");
			
			
			
			
			
			int rc = 1;
			for (Iterator iterator = departAcls.iterator(); iterator.hasNext();)
			{
				Authority acl = (Authority)iterator.next();
				Row rowContent = sheetDepart.createRow(rc);
				Site site = siteManager.getSiteById(acl.getSite_id());
				Department depart = departManager.getDepartById(acl.getGroup_id());
				FWUser fwUser = userManager.getUserById(acl.getUser_id());
				SearchType searchType =  typeManager.getTypeById(acl.getQuery_type_id());

					Cell cSite = rowContent.createCell(0);
					cSite.setCellStyle(cellStyle);
					cSite.setCellValue(site==null?"":site.getSite_name());
					
					Cell cDepart = rowContent.createCell(1);
					cDepart.setCellStyle(cellStyle);
					cDepart.setCellValue(depart==null?"":depart.getDepartment_name());
					
				
					Cell cType = rowContent.createCell(2);
					cType.setCellStyle(cellStyle);
					cType.setCellValue(searchType.getType_name());
					
					Cell cPrintWord = rowContent.createCell(3);
					cPrintWord.setCellStyle(cellStyle);
					cPrintWord.setCellValue(boolean2Str(acl.isCan_print_word()));
					
					Cell cDownloadWord = rowContent.createCell(4);
					cDownloadWord.setCellStyle(cellStyle);
					cDownloadWord.setCellValue(boolean2Str(acl.isCan_download_word()));
					
					Cell cPrintExcel = rowContent.createCell(5);
					cPrintExcel.setCellStyle(cellStyle);
					cPrintExcel.setCellValue(boolean2Str(acl.isCan_print_excel()));
					
					Cell cDownloadExcel = rowContent.createCell(6);
					cDownloadExcel.setCellStyle(cellStyle);
					cDownloadExcel.setCellValue(boolean2Str(acl.isCan_download_excel()));
					
					Cell cPrintPDF = rowContent.createCell(7);
					cPrintPDF.setCellStyle(cellStyle);
					cPrintPDF.setCellValue(boolean2Str(acl.isCan_print_pdf()));
					
					Cell cDownloadPDF = rowContent.createCell(8);
					cDownloadPDF.setCellStyle(cellStyle);
					cDownloadPDF.setCellValue(boolean2Str(acl.isCan_download_pdf()));
					
					
					Cell cBOMSearch = rowContent.createCell(9);
					cBOMSearch.setCellStyle(cellStyle);
					cBOMSearch.setCellValue(boolean2Str(acl.isCan_bom_search()));
					
					Cell cMaterialSearch = rowContent.createCell(10);
					cMaterialSearch.setCellStyle(cellStyle);
					cMaterialSearch.setCellValue(boolean2Str(acl.isCan_material_search()));
					
					Cell cParentSearch = rowContent.createCell(11);
					cParentSearch.setCellStyle(cellStyle);
					cParentSearch.setCellValue(boolean2Str(acl.isCan_parent_search()));
					
					rc++;
				
			}
			
			
			rc =1;
			for (Iterator iterator = userAcls.iterator(); iterator.hasNext();)
			{
				Authority acl = (Authority)iterator.next();
				Row rowContent = sheetUser.createRow(rc);
				//Site site = siteManager.getSiteById(acl.getSite_id());
				//Department depart = departManager.getDepartById(acl.getGroup_id());
				FWUser fwUser = userManager.getUserById(acl.getUser_id());
				SearchType searchType =  typeManager.getTypeById(acl.getQuery_type_id());

					
					
					Cell cUser = rowContent.createCell(0);
					cUser.setCellStyle(cellStyle);
					cUser.setCellValue(fwUser==null?"":fwUser.getUser_name());
					
				
					Cell cType = rowContent.createCell(1);
					cType.setCellStyle(cellStyle);
					cType.setCellValue(searchType.getType_name());
					
					Cell cPrintWord = rowContent.createCell(2);
					cPrintWord.setCellStyle(cellStyle);
					cPrintWord.setCellValue(boolean2Str(acl.isCan_print_word()));
					
					Cell cDownloadWord = rowContent.createCell(3);
					cDownloadWord.setCellStyle(cellStyle);
					cDownloadWord.setCellValue(boolean2Str(acl.isCan_download_word()));
					
					Cell cPrintExcel = rowContent.createCell(4);
					cPrintExcel.setCellStyle(cellStyle);
					cPrintExcel.setCellValue(boolean2Str(acl.isCan_print_excel()));
					
					Cell cDownloadExcel = rowContent.createCell(5);
					cDownloadExcel.setCellStyle(cellStyle);
					cDownloadExcel.setCellValue(boolean2Str(acl.isCan_download_excel()));
					
					Cell cPrintPDF = rowContent.createCell(6);
					cPrintPDF.setCellStyle(cellStyle);
					cPrintPDF.setCellValue(boolean2Str(acl.isCan_print_pdf()));
					
					Cell cDownloadPDF = rowContent.createCell(7);
					cDownloadPDF.setCellStyle(cellStyle);
					cDownloadPDF.setCellValue(boolean2Str(acl.isCan_download_pdf()));
					
					
					Cell cBOMSearch = rowContent.createCell(8);
					cBOMSearch.setCellStyle(cellStyle);
					cBOMSearch.setCellValue(boolean2Str(acl.isCan_bom_search()));
					
					Cell cMaterialSearch = rowContent.createCell(9);
					cMaterialSearch.setCellStyle(cellStyle);
					cMaterialSearch.setCellValue(boolean2Str(acl.isCan_material_search()));
					
					Cell cParentSearch = rowContent.createCell(10);
					cParentSearch.setCellStyle(cellStyle);
					cParentSearch.setCellValue(boolean2Str(acl.isCan_parent_search()));
					
					rc++;
				
			}

			FileOutputStream fileOut = new FileOutputStream(filepath);
			swb.write(fileOut);
			fileOut.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		File f = new File(filepath);
		if (f.exists())
			return f.getName();
		else
			return "";
	}
	String boolean2Str(boolean flag) {
		return String.valueOf(flag);
	}
	@Override
	public Authority getPersonACL(String userId, String type_id) {
		// TODO Auto-generated method stub
		
		return (Authority) getCurrentSession().createQuery("from Authority where user_id = ? and  query_type_id = ?")
				.setString(0, userId)
				.setString(1, type_id)
				.uniqueResult();
	}
	@Override
	public Authority getPersonDepartACL(String userId, String type_id) {
		// TODO Auto-generated method stub
	return	(Authority) getCurrentSession().createQuery("from Authority where user_id = ? and query_type_id = ?")
		.setString(0, userId)
		.setString(1, type_id)
		.uniqueResult();
	}
	@Override
   public Authority getUsersDepartACLById(String typeId,String userId) {
		
		FWUser fwUser = (FWUser) getCurrentSession().createQuery("from FWUser where uuid = ?")
		.setString(0, userId)
		.uniqueResult();
		for(Department dp : fwUser.getGroups()) {
			String dpId = dp.getUuid();
			Authority authority = (Authority) getCurrentSession().createQuery("from Authority where group_id = ? and query_type_id = ?")
			
			.setString(0, dpId)
			.setString(1, typeId)
			.uniqueResult();
			if(authority != null) {
				return authority;
			}
		}
		return null;
		 
	}
	@Override
	public void deleteUsersACLByUserId(String userId) {
		getCurrentSession().createQuery("delete from Authority where user_id = ?")
		.setString(0, userId)
		.executeUpdate();
		
	}
	@Override
	public void deleteDepartsACLByDepartId(String departId) {
		getCurrentSession().createQuery("delete from Authority where group_id = ?")
		.setString(0, departId)
		.executeUpdate();
		
	}
	@Override
	public Authority getAuthorityBySiteIdAndDepartId(String siteId,
			String departId, String typeId) {
		// TODO Auto-generated method stub
		Authority uniqueResult = (Authority) getCurrentSession().createQuery("from Authority where site_id = ? and group_id =? and query_type_id = ?")
				.setString(0, siteId)
				.setString(1, departId)
				.setString(2, typeId)
				.uniqueResult();
		return uniqueResult;
	}





}
