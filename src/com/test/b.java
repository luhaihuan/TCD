// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   b.java

package com.test;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class b
{

	public b()
	{
	}

	public static void main(String args[])
	{
		try
		{
			addPdfMark("C:\\SulliarProject\\fileAction\\test123.xlsx.pdf", "C:\\SulliarProject\\fileAction\\test123.pdf", "C:\\SulliarProject\\fileAction\\章\\普通用户方章1.png", 0, 515);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.println("write text to pdf ok!");
	}

	static void pdfAddText(String s, String s1, String s2, int i, int j)
	{
	}

	public static void addPdfMark(String InPdfFile, String outPdfFile, String markImagePath, int x, int y)
		throws Exception
	{
		PdfReader reader = new PdfReader(InPdfFile, "PDF".getBytes());
		PdfStamper stamp = new PdfStamper(reader, new FileOutputStream(outPdfFile));
		Image img = Image.getInstance(markImagePath);
		for (int i = 1; i <= reader.getNumberOfPages(); i++)
		{
			float pageHeight = reader.getPageSize(i).getHeight();
			float imgHeight = img.getHeight();
			if (y == 0)
				img.setAbsolutePosition(x, pageHeight - imgHeight);
			else
				img.setAbsolutePosition(x, y);
			PdfContentByte under = stamp.getUnderContent(i);
			under.addImage(img);
		}

		stamp.close();
	}

	static void pdfAddWaterMark(String s, String s1, String s2)
	{
	}
}
