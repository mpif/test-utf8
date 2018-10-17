package com.codefans.java.socket.ex01;

import java.io.Serializable;

/*
 * @Author: Sean
 * @Time: 2015-05-15 10:42:52
 */
public class TransferObject implements Serializable {

	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
