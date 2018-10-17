package com.codefans.java.socket.ex01.services;

import java.lang.reflect.Method;

import com.codefans.java.socket.ex01.services.impl.TransferServiceImpl;

/*
 * @Author: Sean
 * @Time: 2015-05-15 11:26:05
 */
public class ReflectionMain {

	/**
	 * @param args
	 * @throws Throwable
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception, Throwable {
		ReflectionMain rm = new ReflectionMain();
		rm.reflection();
	}

	public void reflection() throws Exception, Throwable {
		Class cls = TransferService.class;
		TransferService ts = new TransferServiceImpl();
		// Method method = cls.getMethod("transfer", String.class);
		// Object result = method.invoke(ts, new Object[]{"zhangsan"});
		// System.out.println(String.valueOf(result));
		System.out.println(ts.transfer("zhangsan"));
	}

}
