package com.fuwa.lhh.util;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageFactory {
	public static File imgZoom(File res,File des,float x,float y) {


		/********** save������ *****************/  
		try {  
			BufferedImage sourceImage = ImageIO.read(new FileInputStream(res));  
			BufferedImage dstImage = null;  
			AffineTransform transform = AffineTransform.getScaleInstance(x, y);// ���ر�ʾ���ű任�ı任  
			AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);  
			dstImage = op.filter(sourceImage, null);  
			ImageIO.write(dstImage, "png", des);  
			
			System.out.println("img chang finish ---->");
			return des;
		} catch (IOException e) {  
			e.printStackTrace();  
		}  
		return res;

	}

}
