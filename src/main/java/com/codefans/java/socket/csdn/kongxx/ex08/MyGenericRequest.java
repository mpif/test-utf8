package com.codefans.java.socket.csdn.kongxx.ex08;

/*
 * @Author: Sean
 * @Time: 2015-05-18 14:54:32
 */

public class MyGenericRequest implements MyRequest {

	private static final long serialVersionUID = 1L;

	private Class<?> requestClass;
	private String requestMethod;
	private Class<?>[] requestParameterTypes;
	private Object[] requestParameterValues;

	public MyGenericRequest(Class<?> requestClass, String requestMethod, Class<?>[] requestParameterTypes,
			Object[] requestParameterValues) {
		this.requestClass = requestClass;
		this.requestMethod = requestMethod;
		this.requestParameterTypes = requestParameterTypes;
		this.requestParameterValues = requestParameterValues;
	}

	@Override
	public Class<?> getRequestClass() {
		return requestClass;
	}

	@Override
	public String getRequestMethod() {
		return requestMethod;
	}

	@Override
	public Class<?>[] getRequestParameterTypes() {
		return requestParameterTypes;
	}

	@Override
	public Object[] getRequestParameterValues() {
		return requestParameterValues;
	}
}
