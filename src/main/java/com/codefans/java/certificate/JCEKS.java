package com.codefans.java.certificate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;

import javax.crypto.KeyGenerator;

public class JCEKS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JCEKS jceks = new JCEKS();
		jceks.store();
		jceks.load();
	}

	public void store() {
		try {
			KeyStore keyStore = KeyStore.getInstance("JCEKS");
			keyStore.load(null, null);

			KeyGenerator keyGen = KeyGenerator.getInstance("DES");
			keyGen.init(56);
			;
			Key key = keyGen.generateKey();

			keyStore.setKeyEntry("secret", key, "password".toCharArray(), null);

			keyStore.store(new FileOutputStream("output.jceks"), "password".toCharArray());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void load() {
		try {
			KeyStore keyStore = KeyStore.getInstance("JCEKS");
			keyStore.load(new FileInputStream("output.jceks"), "password".toCharArray());

			Key key = keyStore.getKey("secret", "password".toCharArray());

			System.out.println(key.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
