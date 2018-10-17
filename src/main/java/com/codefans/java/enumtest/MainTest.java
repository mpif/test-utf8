package com.codefans.java.enumtest;

public class MainTest {

	public static void main(String[] args) {
		String type = "EMAIL_BODY";
		System.out.println(type.equals(StoreFor.EMAIL_BODY));
		System.out.println(type.equals(StoreFor.EMAIL_BODY.toString()));
	}

	public static enum StoreFor {
		/** The EMAI l_ body. */
		EMAIL_BODY,
		/** The EMAI l_ attachment. */
		EMAIL_ATTACHMENT,
		/** The FILE. */
		FILE,
		/** The SHAREPOINT. */
		SHAREPOINT
	}

}
