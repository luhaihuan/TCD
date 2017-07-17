package com.sulliar.ypq.initSystem;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class Test1 {
	public static void main(String[] args) {  
		  
        FileOutputStream fileOut = null;  
  
        BufferedImage bufferImg = null;  
  
        try {  
  
            // 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray  
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();  
  
            bufferImg = ImageIO.read(new File("c:/zhiliangbu.png"));  
  
            ImageIO.write(bufferImg, "jpg", byteArrayOut);  
  
            // 创建一个工作薄  
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream("c:/hello.xls"));  
              
            HSSFSheet sheet1 = wb.getSheetAt(0);  
  
            HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();  
            //HSSFClientAnchor几个数字解释：3：是x轴的开始节点， 0： 是y轴的开始节点，1023：是x轴的结束节点，255：是y轴的结束节点，1：是从Excel的2列开始插入图片，10：是从excel的第11行开始插入图片， 11：图片占用11列的位置，25：图片结束在excel的26行  
            HSSFClientAnchor anchor = new HSSFClientAnchor(3, 0, 1023, 255,(short) 1, 10,(short) 11, 25);  
              
            anchor.setAnchorType(2);  
              
            // 插入图片  
            patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(),HSSFWorkbook.PICTURE_TYPE_PNG));  
  
            fileOut = new FileOutputStream("c:/workbook.xls");  
              
            // 写入excel文件  
            wb.write(fileOut);  
              
            fileOut.close();  
  
        } catch (IOException io) {  
              
            io.printStackTrace();  
  
            System.out.println("io erorr :  " + io.getMessage());  
  
        } finally {  
              
            if (fileOut != null) {  
  
                try {  
                      
                    fileOut.close();  
                      
                } catch (IOException e) {  
                      
                    e.printStackTrace();  
                      
                }  
            }  
        }  
    }  

}
