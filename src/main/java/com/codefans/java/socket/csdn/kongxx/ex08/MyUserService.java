package com.codefans.java.socket.csdn.kongxx.ex08;

/*
 * @Author: Sean
 * @Time: 2015-05-18 14:49:42
 */

import java.util.List;

public interface MyUserService {

	List<User> list(int size);

	User findByName(String name);

	void test();
}
