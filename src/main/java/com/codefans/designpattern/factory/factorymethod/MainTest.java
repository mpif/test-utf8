package com.codefans.designpattern.factory.factorymethod;

import com.codefans.designpattern.factory.factorymethod.Car;
import com.codefans.designpattern.factory.factorymethod.ICar;

public class MainTest {
	public static void main(String[] args) {
		Engine engine = new Engine();
		Underpan underpan = new Underpan();
		Wheel wheel = new Wheel();
		ICar car = new Car(underpan, wheel, engine);
		car.show();
	}
}
