package com.codefans.java.socket.csdn.kongxx.ex08;

/*
 * @Author: Sean
 * @Time: 2015-05-18 14:51:52
 */

public interface MyClient {

	public <T> T execute(MyRequest request, MyResponseHandler<T> handler);

	public MyResponse execute(MyRequest request);

}
