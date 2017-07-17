// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   test.java

package com.test;

import com.jacob.com.ComThread;
import java.io.PrintStream;

// Referenced classes of package com.test:
//			JacobTest

public class test
{

	public test()
	{
	}

	public static void main(String args[])
	{
		JacobTest jacob = new JacobTest();
		for (int i = 0; i < 100; i++)
		{
			ComThread.InitSTA();
			jacob.wordToPDF("C:\\SulliarProject\\fileAction\\1.doc", (new StringBuilder("C:\\SulliarProject\\fileAction\\s")).append(i).toString(), i);
			System.out.println((new StringBuilder("wordToPDF ok")).append(i).toString());
			ComThread.Release();
		}

		System.out.println("wordToPDF ok");
	}
}
