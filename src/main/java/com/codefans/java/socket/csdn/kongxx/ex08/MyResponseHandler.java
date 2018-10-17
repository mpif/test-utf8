package com.codefans.java.socket.csdn.kongxx.ex08;

/*
 * @Author: Sean
 * @Time: 2015-05-18 14:54:00
 */

public interface MyResponseHandler<T> {
	T handle(MyResponse response);
}
