package com.codefans.java.certificate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

import javax.crypto.KeyGenerator;

import sun.security.x509.CertAndKeyGen;
import sun.security.x509.X500Name;

public class PKCS12 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PKCS12 pkcs12 = new PKCS12();

		pkcs12.create();

		pkcs12.storeKey();

		pkcs12.storePrimaryKey();

		pkcs12.storeCertificate();

		pkcs12.loadPrimaryKey();

		pkcs12.loadCertificate();
		//
		pkcs12.loadCertificateChain();
		////
		pkcs12.loadKeyAndCertificate();
	}

	public void create() {
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(null, null);

			keyStore.store(new FileOutputStream("output.p12"), "password".toCharArray());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void storeKey() {
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(null, null);

			KeyGenerator keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(128);
			Key key = keyGen.generateKey();
			keyStore.setKeyEntry("secret", key, "password".toCharArray(), null);

			keyStore.store(new FileOutputStream("output.p12"), "password".toCharArray());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void storePrimaryKey() {
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			// keyStore.load(new
			// FileInputStream("output.p12"),"password".toCharArray());
			keyStore.load(null, null);
			;

			CertAndKeyGen gen = new CertAndKeyGen("RSA", "SHA1WithRSA");
			gen.generate(1024);

			Key key = gen.getPrivateKey();
			X509Certificate cert = gen.getSelfCertificate(new X500Name("CN=ROOT"), (long) 365 * 24 * 3600);

			X509Certificate[] chain = new X509Certificate[1];
			chain[0] = cert;

			keyStore.setKeyEntry("private", key, "password".toCharArray(), chain);

			keyStore.store(new FileOutputStream("output.p12"), "password".toCharArray());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void storeCertificate() {
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			// keyStore.load(new
			// FileInputStream("output.p12"),"password".toCharArray());
			keyStore.load(null, null);
			;

			CertAndKeyGen gen = new CertAndKeyGen("RSA", "SHA1WithRSA");
			gen.generate(1024);

			X509Certificate cert = gen.getSelfCertificate(new X500Name("CN=ROOT"), (long) 365 * 24 * 3600);

			keyStore.setCertificateEntry("cert", cert);

			keyStore.store(new FileOutputStream("output.p12"), "password".toCharArray());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		// 存储的证书可以通过调用提供别名的KeyStore.getCertificate() 来提取，例如：
		// Certificate cert = keyStore.getCertificate("cert");

	}

	public void loadPrimaryKey() {
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(new FileInputStream("output.p12"), "password".toCharArray());

			Key pvtKey = keyStore.getKey("private", "password".toCharArray());
			System.out.println(pvtKey.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void loadCertificate() {
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(new FileInputStream("output.p12"), "password".toCharArray());

			// java.security.cert.Certificate cert =
			// keyStore.getCertificate("private");
			java.security.cert.Certificate cert = keyStore.getCertificate("cert");

			System.out.println(cert);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void loadCertificateChain() {
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(new FileInputStream("output.p12"), "password".toCharArray());

			Key pvtKey = keyStore.getKey("private", "password".toCharArray());
			System.out.println(pvtKey.toString());

			java.security.cert.Certificate[] chain = keyStore.getCertificateChain("private");
			for (java.security.cert.Certificate cert : chain) {
				System.out.println(cert.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void loadKeyAndCertificate() {
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(new FileInputStream("output.p12"), "password".toCharArray());

			Key pvtKey = keyStore.getKey("private", "password".toCharArray());
			java.security.cert.Certificate[] chain = keyStore.getCertificateChain("private");

			KeyStore jksStore = KeyStore.getInstance("JKS");
			jksStore.load(null, null);
			;
			jksStore.setKeyEntry("jksPrivate", pvtKey, "newpassword".toCharArray(), chain);
			jksStore.store(new FileOutputStream("output.jks"), "password".toCharArray());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
