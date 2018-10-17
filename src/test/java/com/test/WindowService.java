package com.test;

import java.util.HashSet;
import java.util.Set;

import org.tanukisoftware.wrapper.WrapperListener;

public class WindowService implements WrapperListener {

	@Override
	public void controlEvent(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public Integer start(String[] arg0) {
		System.out.println("service start");
		return null;
	}

	@Override
	public int stop(int arg0) {
		System.out.println("service stop");
		return 0;
	}

	public String sayHello(String name) {
		return "Hello " + name;
	}

	public static void main(String args[]) {
		Set set = new HashSet();
		String str = new String("hello");
		set.add(str);

		str = new String("hello");
		System.out.println(set.contains(str));
		set.add(str);
		System.out.println(set.size());
	}
}
