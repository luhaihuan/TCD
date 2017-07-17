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
  
            // �ȰѶ�������ͼƬ�ŵ�һ��ByteArrayOutputStream�У��Ա����ByteArray  
            ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();  
  
            bufferImg = ImageIO.read(new File("c:/zhiliangbu.png"));  
  
            ImageIO.write(bufferImg, "jpg", byteArrayOut);  
  
            // ����һ��������  
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream("c:/hello.xls"));  
              
            HSSFSheet sheet1 = wb.getSheetAt(0);  
  
            HSSFPatriarch patriarch = sheet1.createDrawingPatriarch();  
            //HSSFClientAnchor�������ֽ��ͣ�3����x��Ŀ�ʼ�ڵ㣬 0�� ��y��Ŀ�ʼ�ڵ㣬1023����x��Ľ����ڵ㣬255����y��Ľ����ڵ㣬1���Ǵ�Excel��2�п�ʼ����ͼƬ��10���Ǵ�excel�ĵ�11�п�ʼ����ͼƬ�� 11��ͼƬռ��11�е�λ�ã�25��ͼƬ������excel��26��  
            HSSFClientAnchor anchor = new HSSFClientAnchor(3, 0, 1023, 255,(short) 1, 10,(short) 11, 25);  
              
            anchor.setAnchorType(2);  
              
            // ����ͼƬ  
            patriarch.createPicture(anchor, wb.addPicture(byteArrayOut.toByteArray(),HSSFWorkbook.PICTURE_TYPE_PNG));  
  
            fileOut = new FileOutputStream("c:/workbook.xls");  
              
            // д��excel�ļ�  
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
