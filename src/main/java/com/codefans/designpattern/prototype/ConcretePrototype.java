package com.codefans.designpattern.prototype;

public class ConcretePrototype extends Prototype {
	public void show() {
		System.out.println("原型模式实现类, num=" + num + ", map.get('hello'): " + map.get("hello"));
	}
}
