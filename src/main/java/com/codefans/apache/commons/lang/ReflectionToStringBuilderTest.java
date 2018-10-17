package com.codefans.apache.commons.lang;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

public class ReflectionToStringBuilderTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ReflectionToStringBuilderTest toString = new ReflectionToStringBuilderTest();
		toString.build();
	}

	public void build() {
		ReflectionToStringBuilderBean bean = new ReflectionToStringBuilderBean();
		bean.setAge(23);
		bean.setName("zhangsan");
		bean.setAddress("address");
		ReflectionToStringBuilder toStringBuilder = new ReflectionToStringBuilder(bean,
				ToStringStyle.SHORT_PREFIX_STYLE);
		System.out.println(toStringBuilder.toString());// output:
														// ReflectionToStringBuilderBean[age=23,name=zhangsan,address=address]
	}
}

class ReflectionToStringBuilderBean {
	int age;
	String name;
	String address;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
