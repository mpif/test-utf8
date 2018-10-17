package com.codefans.test.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TransportHandler implements InvocationHandler {

	private Object o;

	public TransportHandler(Object o) {
		this.o = o;
	}

	// public void run() throws Exception {
	// Class c = t.getClass();
	// Method method = c.getMethod(name, parameterTypes)
	// t.run();
	// }

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("do something before method");
		Object obj = method.invoke(o, args);
		System.out.println("do something before method");
		return obj;
	}
}
