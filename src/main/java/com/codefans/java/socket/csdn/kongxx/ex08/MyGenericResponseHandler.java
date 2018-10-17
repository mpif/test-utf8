package com.codefans.java.socket.csdn.kongxx.ex08;

/*
 * @Author: Sean
 * @Time: 2015-05-18 14:55:16
 */

public class MyGenericResponseHandler<T> implements MyResponseHandler<T> {
	@Override
	public T handle(MyResponse response) {
		return (T) response.getResult();
	}
}
