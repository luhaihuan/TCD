package com.sulliar.ypq.initSystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Test {
	public static void main(String[] args) throws InvalidFormatException, FileNotFoundException, IOException {
		String fileName="c:/hello.xls";

		
        Workbook workbook=WorkbookFactory.create(new FileInputStream(fileName)); 
		Sheet sheet = workbook.getSheetAt(0);
	/*	for(int i=0;i<sheet.getLastRowNum();i++) {
			Row row = sheet.getRow(i);
			for(int j=0;j<row.getLastCellNum();j++) {
				
				//Cell cell=row.getCell(j);				
				//switch (cell.getCellType()) {}
				sheet.autoSizeColumn(j);
				
			}
			
			for (int j=0;j<row.getLastCellNum();j++) {  
			   int curColWidth = sheet.getColumnWidth(i);  
			    if (curColWidth < 100) {  
			        sheet.setColumnWidth(j, 100);  
			    }  
			} 
			System.out.println();
		}*/
		for(int i=0;i<1;i++) { 
			Row row = sheet.getRow(i);
			for (int j=0;j<row.getLastCellNum();j++) {  
				 /*  int curColWidth = sheet.getColumnWidth(j);  
				    if (curColWidth < 100) {  
				        sheet.setColumnWidth(j, 100);  
				    } */ 
				sheet.autoSizeColumn(j);
				} 
		}
	
		
		workbook.write(new FileOutputStream("c:/result.xls"));
		System.out.println("change finish--->");
	}
	
	File fitExcel(File src,File des) {		    	
		try {
			
			  Workbook workbook=WorkbookFactory.create(new FileInputStream(src)); 
				Sheet sheet = workbook.getSheetAt(0);

				for(int i=0;i<1;i++) { 
					Row row = sheet.getRow(i);
					for (int j=0;j<row.getLastCellNum();j++) {  
						 /*  int curColWidth = sheet.getColumnWidth(j);  
						    if (curColWidth < 100) {  
						        sheet.setColumnWidth(j, 100);  
						    } */ 
						sheet.autoSizeColumn(j);
						} 
				}
			workbook.write(new FileOutputStream(des));
		} catch (IOException | InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("fitExcel change finish--->");
		return des;
		
		
	}
	

}
