package com.sulliar.ypq.fileManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

import com.fuwa.lhh.model.Stamp;
import com.fuwa.lhh.service.StampManager;
import com.fuwa.ypq.model.FWUser;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.sulliar.ypq.model.ItemModel;
import com.sulliar.ypq.model.User;
import com.sulliar.ypq.service.FileDataManager;
import com.sulliar.ypq.utils.SpringBeanFactoryUtil;

public class SBDownloadAndWaterMarkAction extends ActionSupport {

	String file_path;
	String waterm_name;
	String wp;
	@Resource(name = "FileDataManager")
	FileDataManager fileManager;
	
	
	@Resource
	StampManager stampManager;
	public String getWp() {
		return wp;
	}

	public void setWp(String wp) {
		this.wp = wp;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	public String getWaterm_name() {
		return waterm_name;
	}

	public void setWaterm_name(String waterm_name) {
		this.waterm_name = waterm_name;
	}

	boolean flag;
	boolean success;
	
	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	String path;
	String dsname;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDsname() {
		return dsname;
	}

	public void setDsname(String dsname) {
		this.dsname = dsname;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		//BeanFactory beanFactory = SpringBeanFactoryUtil.getInstance().getFactory();
		//FileDataManager fileManager = (FileDataManager)beanFactory.getBean("FileDataManager");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String wn = URLDecoder.decode(waterm_name, "utf-8");
		
		
		String[] water_array = waterm_name.split(",");
		List<Stamp> stampList = stampManager.getStampInArray(water_array);
		
		
		
		String fp = URLDecoder.decode(file_path, "utf-8");
		String wp_t = URLDecoder.decode(wp, "utf-8");
		
		FWUser fwUser = (FWUser)ActionContext.getContext().getSession().get("current_user");
		String realpath = ServletActionContext.getServletContext().getRealPath("/");
		
		File xlsxFile = new File(fp);
		if(xlsxFile.exists()){
			try{
				List<String> paths = new ArrayList<String>();
				
				String filepath = realpath + "HFTempFiles/特殊批量下载反馈"+sdf.format(new Date())+".xlsx";
		        				
				XSSFWorkbook wb1 = new XSSFWorkbook();
				SXSSFWorkbook swb1 = new SXSSFWorkbook(wb1, -1);
				
				Sheet sheet11 = swb1.createSheet();
				
				int fkrc = 0;
				
				Row row1 = sheet11.createRow(fkrc);
				//Part Number	Problem Feedback	Table Figure Number

				row1.createCell(0).setCellValue("Part Number");
				row1.createCell(1).setCellValue("Problem Feedback");
				row1.createCell(2).setCellValue("Table Figure Number");
				
				fkrc++;
				
				FileInputStream fileIn = new FileInputStream(xlsxFile);
				XSSFWorkbook wb = new XSSFWorkbook(fileIn);
				//SXSSFWorkbook swb = new SXSSFWorkbook(wb, -1);
				Sheet sheet1 = wb.getSheetAt(0);			
				
				int rowCount = sheet1.getPhysicalNumberOfRows();
				
				for(int i=1;i<rowCount;i++){
					
					Row row = sheet1.getRow(i);
					int cellCount = row.getPhysicalNumberOfCells();
					
					if(cellCount>0){
						Cell cell = row.getCell(0);
						String value = getCellValue(cell);
												
						if(value.trim().length()>0){
							
							List<ItemModel> items = fileManager.getSearchFiles("",value,"", "", "", "", fwUser.getUser_name());
							
							if(items.size()==0){
								Row row11 = sheet11.createRow(fkrc);
								//Part Number	Problem Feedback	Table Figure Number

								row11.createCell(0).setCellValue(value);
								row11.createCell(1).setCellValue("无图");
								row11.createCell(2).setCellValue("");
								
								fkrc++;
							}else{
								
								for(ItemModel item : items){
									
									if(item.getReferenceId()!=null&&item.getReferenceId().trim().length()>0){
										
										Row row11 = sheet11.createRow(fkrc);
										//Part Number	Problem Feedback	Table Figure Number

										row11.createCell(0).setCellValue(item.getItem_id());
										row11.createCell(1).setCellValue(item.getDataset_name()+"类型为"+item.getDataset_type()+"是表格图");
										row11.createCell(2).setCellValue(item.getReferenceId());
										
										fkrc++;
										
									}else{
										File sourceFile = new File(item.getFilePath());
										if(sourceFile.exists()){
											String itempath = fileManager.DownloadFile(sourceFile, item.getDataset_type(), realpath, stampList, fwUser.getUser_name(), wp_t, "文控中心",false);
											
											String suffix = itempath.substring(itempath.lastIndexOf("."), itempath.length());						
											
											String tfp = (new StringBuilder(String.valueOf(realpath))).append("HFTempFiles/").append(item.getItem_id()).append("_").append(item.getItem_revision()).append("_").append(item.getDataset_name().replace("/", "_")).append(item.getDataset_type()).append(sdf.format(new Date())).append(suffix).toString();
											fileManager.Copy((new StringBuilder(String.valueOf(realpath))).append("HFTempFiles/").append(itempath).toString(), tfp);
											if (!paths.contains(tfp))
												paths.add(tfp);
											
										}else{
											Row row11 = sheet11.createRow(fkrc);
											//Part Number	Problem Feedback	Table Figure Number

											row11.createCell(0).setCellValue(item.getItem_id());
											row11.createCell(1).setCellValue(item.getDataset_name()+"类型为"+item.getDataset_type()+"图纸无法下载");
											row11.createCell(2).setCellValue("");
											
											fkrc++;
										}
										
										
									}
									
								}
								
							}
						}
						
					}
				}			
				
				FileOutputStream fileOut = new FileOutputStream(filepath);
				swb1.write(fileOut);
				fileOut.close();
				
				dsname = (new StringBuilder("特殊批量下载")).append(sdf.format(new Date())).toString();
				String zipPath = (new StringBuilder(String.valueOf(realpath))).append("HFTempFiles/").append(dsname).append(".zip").toString();
				byte buffer[] = new byte[1024];
				ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipPath));
				
				{
					File tf = new File(filepath);
					FileInputStream fis = new FileInputStream(tf);
					out.putNextEntry(new ZipEntry(tf.getName()));
					int len;
					while ((len = fis.read(buffer)) > 0) 
						out.write(buffer, 0, len);
					out.closeEntry();
					fis.close();
				}
				
				for (int i = 0; i < paths.size(); i++)
				{
										
					File tf = new File((String)paths.get(i));
					FileInputStream fis = new FileInputStream(tf);
					out.putNextEntry(new ZipEntry(tf.getName()));
					int len;
					while ((len = fis.read(buffer)) > 0) 
						out.write(buffer, 0, len);
					out.closeEntry();
					fis.close();
				}

				out.close();
				System.out.println("生成zip成功");
				path = (new StringBuilder(String.valueOf(dsname))).append(".zip").toString();
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
			xlsxFile.delete();
		}
		
		flag = true;
		success = true;
		return "success";
	}
	
	String getCellValue(Cell cell){
		String value = "";
		switch (cell.getCellType()) {
         case Cell.CELL_TYPE_NUMERIC:
          //System.out.println(cell.getNumericCellValue());
        	 value = cell.getNumericCellValue()+"";
          break;
         case Cell.CELL_TYPE_STRING:
          //System.out.println(cell.getStringCellValue());
        	 value = cell.getStringCellValue();
          break;
         case Cell.CELL_TYPE_BOOLEAN:
          //System.out.println(cell.getBooleanCellValue());
        	 value = cell.getBooleanCellValue()+"";
          break;		                
         default:
          //System.out.println("unsuported sell type");
          break;
         }
		
		return value;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
