package com.codefans.designpattern.factory.factorymethod;

public class Car implements ICar {

	private Underpan underpan;
	private Wheel wheel;
	private Engine engine;

	public Car(Underpan underpan, Wheel wheel, Engine engine) {
		this.underpan = underpan;
		this.wheel = wheel;
		this.engine = engine;
	}

	public void show() {
		this.underpan.getStyle();
		this.wheel.getStyle();
		this.engine.getStyle();
	}

}
