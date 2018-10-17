package com.codefans.java.socket.csdn.kongxx.ex08;

/*
 * @Author: Sean
 * @Time: 2015-05-18 14:54:57
 */

public class MyGenericResponse implements MyResponse {

	private Object obj = null;

	public MyGenericResponse(Object obj) {
		this.obj = obj;
	}

	@Override
	public Object getResult() {
		return obj;
	}
}
