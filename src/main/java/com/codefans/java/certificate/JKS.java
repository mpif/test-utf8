package com.codefans.java.certificate;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.X509Certificate;

import sun.security.x509.CertAndKeyGen;
import sun.security.x509.X500Name;

public class JKS {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		JKS jks = new JKS();
		jks.create();
		jks.storePrimaryKey();
		jks.storeCertificate();
		jks.loadPrimaryKey();
		jks.loadCertificate();

	}

	public void create() {
		try {
			KeyStore keyStore = KeyStore.getInstance("JKS");
			keyStore.load(null, null);
			keyStore.store(new FileOutputStream("mytestkey.jks"), "password".toCharArray());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void storePrimaryKey() {
		try {
			KeyStore keyStore = KeyStore.getInstance("JKS");
			keyStore.load(new FileInputStream("mytestkey.jks"), "password".toCharArray());

			CertAndKeyGen gen = new CertAndKeyGen("RSA", "SHA1WithRSA");
			gen.generate(1024);

			Key key = gen.getPrivateKey();
			X509Certificate cert = gen.getSelfCertificate(new X500Name("CN=ROOT"), (long) 365 * 24 * 3600);

			X509Certificate[] chain = new X509Certificate[1];
			chain[0] = cert;

			keyStore.setKeyEntry("mykey", key, "password".toCharArray(), chain);

			keyStore.store(new FileOutputStream("mytestkey.jks"), "password".toCharArray());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void storeCertificate() {
		try {
			KeyStore keyStore = KeyStore.getInstance("JKS");
			keyStore.load(new FileInputStream("mytestkey.jks"), "password".toCharArray());

			CertAndKeyGen gen = new CertAndKeyGen("RSA", "SHA1WithRSA");
			gen.generate(1024);

			X509Certificate cert = gen.getSelfCertificate(new X500Name("CN=SINGLE_CERTIFICATE"),
					(long) 365 * 24 * 3600);

			keyStore.setCertificateEntry("single_cert", cert);

			keyStore.store(new FileOutputStream("mytestkey.jks"), "password".toCharArray());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void loadPrimaryKey() {
		try {
			KeyStore keyStore = KeyStore.getInstance("JKS");
			keyStore.load(new FileInputStream("mytestkey.jks"), "password".toCharArray());

			Key key = keyStore.getKey("alias", "password".toCharArray());
			// System.out.println("Private key : "+key.toString()); //You will
			// get a NullPointerException if you uncomment this line

			java.security.cert.Certificate[] chain = keyStore.getCertificateChain("mykey");
			for (java.security.cert.Certificate cert : chain) {
				System.out.println(cert.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void loadCertificate() {
		try {
			KeyStore keyStore = KeyStore.getInstance("JKS");
			keyStore.load(new FileInputStream("mytestkey.jks"), "password".toCharArray());

			java.security.cert.Certificate cert = keyStore.getCertificate("single_cert");

			System.out.println(cert.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
