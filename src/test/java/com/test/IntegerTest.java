package com.test;

import java.lang.reflect.Field;

/**
 * @author: caishengzhi
 * @date: 2016年12月1日上午11:48:00
 * 
 * Integer.valueOf(String) 方法之惑
 * http://www.importnew.com/9162.html
 * 
 * Java基础——原码, 反码, 补码 详解
 * http://www.linuxidc.com/Linux/2015-02/113863.htm
 * 
 */
public class IntegerTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		IntegerTest it = new IntegerTest();
		it.integerEquals();
	}

	public void integerEquals() {
		Integer itg = new Integer("33");
		System.out.println(itg == 33); //true
		
//		Integer a=1000, b=1000;
//		System.out.println("a==b:" + (a == b));
//		Integer c=100, d=100;
//		System.out.println("c=d:" + (c == d));
		
		try {
			
			Class cache = Integer.class.getDeclaredClasses()[0]; //1
			Field myCache = cache.getDeclaredField("cache"); //2
			myCache.setAccessible(true);//3
 
			Integer[] newCache = (Integer[]) myCache.get(cache); //4
			newCache[132] = newCache[133]; //5
 
			int a = 2;
			int b = a + a;
			Integer i = new Integer("12");
			System.out.println(i.intValue()); //12
			System.out.println(a); //2
			System.out.println(b); //4
			System.out.println(b); //4
			System.out.printf("%d + %d = %d", a, a, b); //2 + 2 = 5
			System.out.println(); //
			System.out.println(b); //4
			System.out.println(b); //4
			System.out.println(Integer.valueOf(b + "")); //5
			System.out.println("================="); //
			System.out.print("print(b):");
			print(b); //5
			System.out.println(a+a); //4
			System.out.println(Integer.valueOf(Integer.valueOf(a) + Integer.valueOf(a))); //5
			System.out.println(Integer.parseInt("" + Integer.parseInt("" + a) + Integer.parseInt("" + a))); //22
			System.out.println("new Integer(\"4\"):" + new Integer("4")); //4
			System.out.println("Integer.parseInt(\"4\"):" + Integer.parseInt("4")); //4
			System.out.println("Integer.valueOf(\"4\"):" + Integer.valueOf("4")); //5
//			Object obj = "4";
//			System.out.println("Integer.valueOf(\"4\"):" + ((Integer)obj).intValue()); //
			Integer intg = 4;
			System.out.println("intg:" + intg); //5
			int aa = 4;
			System.out.println("aa: " + aa); //4
			System.out.println("(Integer)aa: " + (Integer)aa); //5
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	    
		
	}
	
	public void print(Object obj) {
		if(obj instanceof Integer) {
			System.out.println(((Integer)obj).intValue());
		}
	}

}
