package com.codefans.designpattern.dynamicproxy.jdk;

public class TestProxy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BookFacadeProxy proxy = new BookFacadeProxy();
		BookFacade bookProxy = (BookFacade) proxy.bind(new BookFacadeImpl());
		bookProxy.addBook();
	}

}
