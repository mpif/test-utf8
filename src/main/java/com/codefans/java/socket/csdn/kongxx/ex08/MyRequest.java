package com.codefans.java.socket.csdn.kongxx.ex08;

/*
 * @Author: Sean
 * @Time: 2015-05-18 14:53:18
 */

import java.io.Serializable;

public interface MyRequest extends Serializable {

	Class<?> getRequestClass();

	String getRequestMethod();

	Class<?>[] getRequestParameterTypes();

	Object[] getRequestParameterValues();

}
