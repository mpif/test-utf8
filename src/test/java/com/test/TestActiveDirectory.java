package com.test;

import java.util.Properties;
import javax.naming.*;
import javax.naming.directory.*;

public class TestActiveDirectory {

	// include the JNDI in the classpath. You should use the same JDK used by
	// WebSphere Application server.
	public static void main(String[] args) {
		// ***************** user information to be authenticated
		// ********************************
		// *****************Please modify the following three properties
		// accordingly ************
		String ldapHost = "ldap://cliang1.austin.ibm.com:389"; // ldap host +
																// port number
		String DN = "cn=user1, ou=Austin,o=ibm,c=us"; // DN to be authenticated
		String password = "security"; // DN's password
		// ***************** End of user information

		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		// for websphere 4.0 and 5.0
		// props.put(Context.INITIAL_CONTEXT_FACTORY,
		// "com.ibm.jndi.LDAPCtxFactory");
		// for WebSphere 3.5 release
		props.put(Context.SECURITY_AUTHENTICATION, "simple"); // use simple
																// authentication
																// mechanism
		props.put(Context.SECURITY_CREDENTIALS, password);
		props.put(Context.SECURITY_PRINCIPAL, DN);
		props.put(Context.PROVIDER_URL, ldapHost);

		long start = System.currentTimeMillis();
		long end = 0;
		long time = 0;

		try {
			System.out.println("authenticating");
			DirContext ctx = new InitialDirContext(props);
			System.out.println("authenticated");
			end = System.currentTimeMillis();
			time = end - start;
			System.out.println("authentication takes = " + time + " millis");
			System.out.println("successfully authenticate DN: " + DN);

		} catch (Exception ex) {
			end = System.currentTimeMillis();
			time = end - start;
			System.out.println("Exception is " + ex.toString());
			ex.printStackTrace();
			System.out.println("authentication takes = " + time + " millis");
			System.out.println("fail to authenticate DN: " + DN);
		}
	}

}
