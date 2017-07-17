// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Cn2Spell.java

package com.sulliar.ypq.utils;

import java.io.PrintStream;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.*;

public class Cn2Spell
{

	public Cn2Spell()
	{
	}

	public static String converterToSpell(String chines)
	{
		String pinyinStr = "";
		char strChar[] = chines.toCharArray();
		HanyuPinyinOutputFormat dformat = new HanyuPinyinOutputFormat();
		dformat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		dformat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < strChar.length; i++)
			if (strChar[i] > '\200')
				try
				{
					pinyinStr = (new StringBuilder(String.valueOf(pinyinStr))).append(PinyinHelper.toHanyuPinyinStringArray(strChar[i], dformat)[0]).toString();
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			else
				pinyinStr = (new StringBuilder(String.valueOf(pinyinStr))).append(strChar[i]).toString();

		return pinyinStr;
	}

	public static void main(String args[])
	{
		System.out.println(converterToSpell("ÎÄµµÀà_pdf_k9n026k5wehd4"));
	}
}
