package com.codefans.test.dynamicproxy;

public class Car implements Transport {

	@Override
	public void run() throws Exception {
		System.out.println("Car running!");
	}

}
