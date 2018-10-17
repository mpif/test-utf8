package com.codefans.java.socket.ex01.services.impl;

import com.codefans.java.socket.ex01.services.TransferService;

/*
 * @Author: Sean
 * @Time: 2015-05-15 11:24:17
 */
public class TransferServiceImpl implements TransferService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.messagesolution.java.socket.ex01.services.TransferService#transfer()
	 */
	@Override
	public String transfer(String username) {
		String password = "";
		password = username + "'password!";
		return password;
	}

}
