package com.sulliar.ypq.fileManagement;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;

import com.fuwa.lhh.model.SearchType;
import com.fuwa.lhh.service.AuthorityManager;
import com.fuwa.lhh.service.DepartManager;
import com.fuwa.lhh.service.StampManager;
import com.fuwa.lhh.service.TypeManager;
import com.fuwa.lhh.service.UserManager;
import com.fuwa.ypq.model.Authority;
import com.fuwa.ypq.model.FWUser;
import com.opensymphony.xwork2.ActionSupport;

public class UpLoadACLAction extends ActionSupport {

	
	
	private String msg; 
    private String contentType; 
    private String uploadFileName;
    private boolean success;
    private File upload;
    
    
    
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

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	
	@Override
	public String execute() throws Exception {
		System.out.println(toString());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String realPath = ServletActionContext.getServletContext().getRealPath("/")
				+"HFTempFiles/ACLImport"+sdf.format(new Date())+getFilePrefixName(uploadFileName);
		File targetFile = new File(realPath);
		if(upload.exists()) {
			 FileInputStream fis = new FileInputStream(upload);
			 FileOutputStream fos = new FileOutputStream(targetFile);
			 byte[] buffer = new byte[1024];
			 int length = 0;
			 
			 while ((length = fis.read(buffer))!= -1) {
				 fos.write(buffer, 0, length);
			}
			 fis.close();
			 fos.close();
			 
			 if(targetFile.exists()) {
				 
				 importACL(targetFile);
				 
				 
				 
				 msg="文件上传成功"; 
				 success = true; 
			 } else {
				 msg="文件缓存出错"; 
				 success = false; 
			 }
		
			
			  
		} else {
			  msg="文件上传失败"; 
			  success = false;
		}
		return "success";
	}

	
	

	private void importACL(File targetFile) {
		// TODO Auto-generated method stub
		try {
			
			
			List<String> fileList = new ArrayList<String>();
			List<SearchType> searchTypeList = new ArrayList<SearchType>();
			
			List<Authority> departACLs = new ArrayList<Authority>();
			List<Authority> userACLs = new ArrayList<Authority>();
			Workbook workbook = WorkbookFactory.create(targetFile);
			Sheet sheetFileType = workbook.getSheet("文件类型");
			for (int r = 1; r <= sheetFileType.getLastRowNum(); r++) {
				Row row = sheetFileType.getRow(r);				
				fileList.add(getCellValue(row.getCell(0)));
			}
			
			if(!fileList.isEmpty())
			searchTypeList = typeManager.getTypeByNameArray(fileList.toArray(new String[0]));
			
			
			Sheet sheetDepart = workbook.getSheet("部门权限");
			for (int r = 1; r <= sheetDepart.getLastRowNum(); r++) {
				Row row = sheetDepart.getRow(r);				
				String siteName = getCellValue(row.getCell(0));
				String departName = getCellValue(row.getCell(1));
				boolean printWord = str2Boolean(getCellValue(row.getCell(2)));
				boolean downloadWord = str2Boolean(getCellValue(row.getCell(3)));
				boolean printExcel = str2Boolean(getCellValue(row.getCell(4)));
				boolean downloadExcel = str2Boolean(getCellValue(row.getCell(5)));
				boolean printPDF = str2Boolean(getCellValue(row.getCell(6)));
				boolean downloadPDF = str2Boolean(getCellValue(row.getCell(7)));
				boolean searchBOM = str2Boolean(getCellValue(row.getCell(8)));
				boolean searchMaterial = str2Boolean(getCellValue(row.getCell(9)));
				boolean searchParent = str2Boolean(getCellValue(row.getCell(10)));
				
				Object[] siteIdandDepartId = departManager.getSiteIdandDepartId(siteName, departName);
				
				for(SearchType s : searchTypeList) {
					
					if(siteIdandDepartId != null) {
						Authority authority = new Authority();
						authority.setSite_id(siteIdandDepartId[0].toString());
						authority.setGroup_id(siteIdandDepartId[1].toString());
						authority.setQuery_type_id(s.getType_id());
						authority.setCan_print_word(printWord);
						authority.setCan_download_word(downloadWord);
						authority.setCan_print_excel(printExcel);
						authority.setCan_download_excel(downloadExcel);
						authority.setCan_print_pdf(printPDF);
						authority.setCan_download_pdf(downloadPDF);
						authority.setCan_bom_search(searchBOM);
						authority.setCan_material_search(searchMaterial);
						authority.setCan_parent_search(searchParent);
						departACLs.add(authority);
					} else {
						break;
					}
				}
			}
			
			
			
			Sheet sheetUser = workbook.getSheet("用户权限");
			for (int r = 1; r <= sheetUser.getLastRowNum(); r++) {
				Row row = sheetUser.getRow(r);				
				String userName = getCellValue(row.getCell(0));
				
				boolean printWord = str2Boolean(getCellValue(row.getCell(1)));
				boolean downloadWord = str2Boolean(getCellValue(row.getCell(2)));
				boolean printExcel = str2Boolean(getCellValue(row.getCell(3)));
				boolean downloadExcel = str2Boolean(getCellValue(row.getCell(4)));
				boolean printPDF = str2Boolean(getCellValue(row.getCell(5)));
				boolean downloadPDF = str2Boolean(getCellValue(row.getCell(6)));
				boolean searchBOM = str2Boolean(getCellValue(row.getCell(7)));
				boolean searchMaterial = str2Boolean(getCellValue(row.getCell(8)));
				boolean searchParent = str2Boolean(getCellValue(row.getCell(9)));
				
			    FWUser user = userManager.getUserByName(userName);
				
				for(SearchType s : searchTypeList) {
					
					if(user != null) {
						Authority authority = new Authority();
						authority.setUser_id(user.getUuid());						
						authority.setQuery_type_id(s.getType_id());
						authority.setCan_print_word(printWord);
						authority.setCan_download_word(downloadWord);
						authority.setCan_print_excel(printExcel);
						authority.setCan_download_excel(downloadExcel);
						authority.setCan_print_pdf(printPDF);
						authority.setCan_download_pdf(downloadPDF);
						authority.setCan_bom_search(searchBOM);
						authority.setCan_material_search(searchMaterial);
						authority.setCan_parent_search(searchParent);
						userACLs.add(authority);
					} else {
						break;
					}
				}
			}
			
			
			
			
			for(Authority authorityNew : departACLs) {
				
				Authority authorityOld = authorityManager.getAuthorityBySiteIdAndDepartId(authorityNew.getSite_id(), authorityNew.getGroup_id(), authorityNew.getQuery_type_id());
				
				if(authorityOld == null) {
					authorityManager.save(authorityNew);
				} else {
					authorityOld.setCan_print_word(authorityNew.isCan_print_word());
					authorityOld.setCan_download_word(authorityNew.isCan_download_word());
					authorityOld.setCan_print_excel(authorityNew.isCan_print_excel());
					authorityOld.setCan_download_excel(authorityNew.isCan_download_excel());
					authorityOld.setCan_print_pdf(authorityNew.isCan_print_pdf());
					authorityOld.setCan_download_pdf(authorityNew.isCan_download_pdf());
					authorityOld.setCan_material_search(authorityNew.isCan_material_search());
					authorityOld.setCan_bom_search(authorityNew.isCan_bom_search());
					authorityOld.setCan_parent_search(authorityNew.isCan_parent_search());
					authorityManager.update(authorityOld);
					
				}
			}
			
			
			
			
           for(Authority authorityNew : userACLs) {
				
				Authority authorityOld = authorityManager.getPersonACL(authorityNew.getUser_id(), authorityNew.getQuery_type_id());
				
				if(authorityOld == null) {
					authorityManager.save(authorityNew);
				} else {
					authorityOld.setCan_print_word(authorityNew.isCan_print_word());
					authorityOld.setCan_download_word(authorityNew.isCan_download_word());
					authorityOld.setCan_print_excel(authorityNew.isCan_print_excel());
					authorityOld.setCan_download_excel(authorityNew.isCan_download_excel());
					authorityOld.setCan_print_pdf(authorityNew.isCan_print_pdf());
					authorityOld.setCan_download_pdf(authorityNew.isCan_download_pdf());
					authorityOld.setCan_material_search(authorityNew.isCan_material_search());
					authorityOld.setCan_bom_search(authorityNew.isCan_bom_search());
					authorityOld.setCan_parent_search(authorityNew.isCan_parent_search());
					authorityManager.update(authorityOld);
					
				}
			}
			
			
		
			
			
			
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public String toString() {
		return "UpLoadACLAction [msg=" + msg + ", contentType=" + contentType
				+ ", uploadFileName=" + uploadFileName + ", success=" + success
				+ ", upload=" + upload + "]";
	}

	public  String getSimpleFileName(String fileName) {
		return fileName.substring(0, fileName.indexOf("."));
	}
	
	public  String getFilePrefixName(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
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
	
	
	public boolean str2Boolean(String s) {
		return Boolean.parseBoolean(s);
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
