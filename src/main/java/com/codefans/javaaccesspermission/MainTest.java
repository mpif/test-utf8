package com.codefans.javaaccesspermission;

import com.codefans.javaaccesspermission.packagepermission.PackagePermission;

public class MainTest {

	/**
	 * 作用域 当前类 同一个Package 子孙类 其它Package public √ √ √ √ protected √ √ √
	 * default(friendly) √ √ private √
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		ChildClass cc = new ChildClass();
		cc.name = "hello"; // ChildClass没有name属性，该属性继承自其父类BaseClass
		// cc.age = 19; //报错，age是BaseClass的私有变量，子类没有访问权限

		PackagePermission pp = new PackagePermission();
		// pp.date = "20120312";
		// //报错，date在PackagePermission中的访问权限是default，只有同一个包中的类可以访问

		PackagePermissionChild ppc = new PackagePermissionChild();
		// ppc.date = "20120312";//报错，default只能在同一个包或同一个类中被访问，不能被子类访问

		// pp.time = "20120312";//报错，protected只能被当前类，同一个包，或子类访问
	}

}