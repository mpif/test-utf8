package com.codefans.java.certificate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Enumeration;

/*
 * @Author: Sean
 * @Time: 2015-07-15 17:18:44
 */
public class ConventPFX {

	public static final String PKCS12 = "PKCS12";
	public static final String JKS = "JKS";
	public static final String PFX_KEYSTORE_FILE = "C:\\star.netrics.ch.pfx";// pfx文件位置
	public static final String KEYSTORE_PASSWORD = "you_keystore_password";// 导出为pfx文件的设的密码
	public static final String JKS_KEYSTORE_FILE = "c:\\star.netrics.ch.jks"; // jks文件位置

	public static void coverTokeyStore() {
		try {
			KeyStore inputKeyStore = KeyStore.getInstance("PKCS12");
			FileInputStream fis = new FileInputStream(PFX_KEYSTORE_FILE);
			char[] nPassword = null;
			if ((KEYSTORE_PASSWORD == null) || KEYSTORE_PASSWORD.trim().equals("")) {
				nPassword = null;
			} else {
				nPassword = KEYSTORE_PASSWORD.toCharArray();
			}
			inputKeyStore.load(fis, nPassword);
			fis.close();
			KeyStore outputKeyStore = KeyStore.getInstance("JKS");
			outputKeyStore.load(null, KEYSTORE_PASSWORD.toCharArray());
			Enumeration enums = inputKeyStore.aliases();
			while (enums.hasMoreElements()) { // we are readin just one
												// certificate.
				String keyAlias = (String) enums.nextElement();
				System.out.println("alias=[" + keyAlias + "]");
				if (inputKeyStore.isKeyEntry(keyAlias)) {
					Key key = inputKeyStore.getKey(keyAlias, nPassword);
					Certificate[] certChain = inputKeyStore.getCertificateChain(keyAlias);
					outputKeyStore.setKeyEntry(keyAlias, key, KEYSTORE_PASSWORD.toCharArray(), certChain);
				}
			}
			FileOutputStream out = new FileOutputStream(JKS_KEYSTORE_FILE);
			outputKeyStore.store(out, nPassword);
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		coverTokeyStore(); // pfx to jks
	}

}
