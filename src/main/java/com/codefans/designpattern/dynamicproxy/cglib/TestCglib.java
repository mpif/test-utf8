package com.codefans.designpattern.dynamicproxy.cglib;

public class TestCglib {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BookFacadeCglibProxy cglib = new BookFacadeCglibProxy();
		BookFacadeImpl bookCglib = (BookFacadeImpl) cglib.getInstance(new BookFacadeImpl());
		// BookFacadeImpl2 bookCglib=(BookFacadeImpl2)cglib.getInstance(new
		// BookFacadeImpl2());
		bookCglib.addBook();
	}

}
