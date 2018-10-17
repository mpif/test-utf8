package com.codefans.designpattern.prototype;

import java.util.HashMap;

public class Client {
	public static void main(String[] args) {
		ConcretePrototype cp = new ConcretePrototype();
		cp.setNum(100);
		HashMap map = new HashMap();
		map.put("hello", "csz");
		cp.setMap(map);

		for (int i = 0; i < 10; i++) {
			ConcretePrototype clonecp = (ConcretePrototype) cp.clone();
			clonecp.show();
		}
	}
}
