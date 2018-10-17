package com.codefans.activemq.test.ex01;

import java.io.Serializable;

/*
 * @Author: Sean
 * @Time: 2015-05-14 10:58:01
 */
public class PreauditEmailMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String subject;
	private String emlFilePath;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getEmlFilePath() {
		return emlFilePath;
	}

	public void setEmlFilePath(String emlFilePath) {
		this.emlFilePath = emlFilePath;
	}

}
