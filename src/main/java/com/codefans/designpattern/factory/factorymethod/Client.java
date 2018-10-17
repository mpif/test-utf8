package com.codefans.designpattern.factory.factorymethod;

public class Client {
	public static void main(String[] args) {
		IFactory factory = new Factory();
		ICar car = factory.createCar();
		car.show();
	}
}
