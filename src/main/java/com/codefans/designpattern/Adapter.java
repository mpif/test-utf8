package com.codefans.designpattern;

import java.util.HashMap;
import java.util.Map;

public class Adapter {

	// 接口一及其实现类代码：
	interface IUserInfo {
		public abstract String getUserName();

		public abstract String getHomeAddress();

		public abstract String getMobileNumber();

		public abstract String getOfficeNumber();

		public abstract String getJobPosition();

		public abstract String getHomeTelNumber();
	}

	class UserInfoImpl implements IUserInfo {
		public String getHomeAddress() {
			return "HomeAddress:教工路197号";
		}

		public String getHomeTelNumber() {
			return "HomeTelNumber:110";
		}

		public String getMobileNumber() {
			return "MobileNumber:001";
		}

		public String getOfficeNumber() {
			return "OfficeNumber:002";
		}

		public String getUserName() {
			return "UserName:tang";
		}

		public String getJobPosition() {
			return "JobPosition:CEO";
		}
	}

	// 接口二及其实现类代码：
	interface IOuterUser {
		public abstract Map<String, String> getUserBaseInfo();

		public abstract Map<String, String> getUserOfficeInfo();

		public abstract Map<String, String> getUserHomeInfo();
	}

	class OuterUserImpl implements IOuterUser {
		public Map<String, String> getUserBaseInfo() {
			Map<String, String> map = new HashMap<String, String>();
			map.put("UserName", "he");
			map.put("MobileNumber", "100");
			return map;
		}

		public Map<String, String> getUserHomeInfo() {
			Map<String, String> map = new HashMap<String, String>();
			map.put("HomeAddress", "文一西路197号");
			map.put("HomeTelNumber", "011");
			return map;
		}

		public Map<String, String> getUserOfficeInfo() {
			Map<String, String> map = new HashMap<String, String>();
			map.put("JobPosition", "CFO");
			map.put("OfficeNumber", "200");
			return map;
		}
	}

	/*
	 * 
	 * 显然，接口一和接口二及其各自的实现类，在内容上是相关和相似的，但是在接口定义的结构上却是异构的，
	 * 不相同的。如何将这两个接口整合起来？这就是Adapter适配器需要做的事。
	 * 适配器模式和现实生活中的适配器原理相同，拿熟悉的适配器—变压器为例。当输入端的电压有差异时，
	 * 需要将通过变压器将电压进行转换，转换成符合输出端要求的电压。 变压器的功能就是将输入端的不同电压转换成输出端要求的固定电压。
	 * 上面的两个异构的接口如何整合？代码如下：
	 * 
	 */
	class OuterUserInfo extends OuterUserImpl implements IUserInfo {
		public String getHomeAddress() {
			return super.getUserHomeInfo().get("HomeAddress");
		}

		public String getHomeTelNumber() {
			return super.getUserHomeInfo().get("HomeTelNumber");
		}

		public String getJobPosition() {
			return super.getUserOfficeInfo().get("JobPosition");
		}

		public String getMobileNumber() {
			return super.getUserBaseInfo().get("MobileNumber");
		}

		public String getOfficeNumber() {
			return super.getUserOfficeInfo().get("OfficeNumber");
		}

		public String getUserName() {
			return super.getUserBaseInfo().get("UserName");
		}
	}

	public static void main(String[] args) {
		Adapter ad = new Adapter();
		IUserInfo user = ad.new UserInfoImpl();
		System.out.println(user.getHomeAddress()); // HomeAddress:教工路197号
		System.out.println(user.getHomeTelNumber()); // HomeTelNumber:110

		IOuterUser outUser = ad.new OuterUserImpl();
		System.out.println(outUser.getUserHomeInfo()); // {HomeAddress=文一西路197号,
														// HomeTelNumber=011}

		// 适配器模式往往是在二次开发中，需要扩展类功能时使用。
		IUserInfo outerUserInfo = ad.new OuterUserInfo();
		System.out.println(outerUserInfo.getHomeAddress()); // 文一西路197号
		System.out.println(outerUserInfo.getHomeTelNumber()); // 011
	}

	/*
	 * 上面的例子也许是简单了点，可能我们在不知道什么是适配器模式的情况下， 遇到上面的情况（需要整合两个异构的接口），也能写出相应的正确代码。
	 * 但是例子终究只是例子，它将实际情况抽象和简化了。可不管是例子还是实际的开发中遇到的情况，
	 * 所体现出来的适配器模式的思想还是表现得淋漓尽致的。我觉得，适配器的思想就是：在进行功能扩展时候，
	 * 为了尽可能的兼容已有的代码，使用一个中间过渡类或者接口，将需要编写的新代码和已有代码进行整合，
	 * 使得这个中间过渡类能够满足两者的要求。毕竟，将之前代码全部推倒重来不是明智的选择。
	 * 
	 */
}
