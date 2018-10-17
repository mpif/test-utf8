package com.codefans.designpattern.visitor;

class A {
	public void method1() {
		System.out.println("我是A");
	}

	public void method2(B b) {
		b.showA(this);
	}
}

class B {
	public void showA(A a) {
		a.method1();
	}
}

public class Client01 {
	public static void main(String[] args) {
		A a = new A();
		a.method1();
		a.method2(new B());
	}
}
