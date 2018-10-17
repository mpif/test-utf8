package com.codefans.test.dynamicproxy;

import java.lang.reflect.Proxy;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Car c = new Car();
		TransportHandler tp = new TransportHandler(c);
		Transport tProxy = (Transport) Proxy.newProxyInstance(c.getClass().getClassLoader(),
				c.getClass().getInterfaces(), tp);
		try {
			tProxy.run();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
