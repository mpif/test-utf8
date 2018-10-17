package com.test.installservice;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class StartService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		StartService service = new StartService();
		Object[] objs = new Object[] { "start" };
		// service.invokeMain(Start.class.getName(), objs);

		service.invokeMethod(new Start(), "main", objs);

		while (true) {
			System.out.println("running....");
			try {
				Thread.sleep(60 * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void invokeMain(String className, Object[] objs) {
		try {
			Class class1 = Class.forName(className);
			Method method = class1.getMethod("main", Object.class);
			method.invoke(null, objs);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Object invokeMethod(Object owner, String methodName, Object[] args) {

		Class ownerClass = owner.getClass();

		Class[] argsClass = new Class[args.length];

		for (int i = 0, j = args.length; i < j; i++) {
			argsClass[i] = args[i].getClass();
		}

		Object o = null;
		try {
			Method method = ownerClass.getMethod(methodName, argsClass);

			o = method.invoke(owner, args);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return o;
	}

	public static void stop(String[] args) {
		System.out.println(" dfdfdfdfdfd");
		System.exit(0);
	}

}
