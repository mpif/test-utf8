package com.codefans.java.socket;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import com.sun.net.ssl.TrustManager;
import com.sun.net.ssl.TrustManagerFactory;
import com.sun.net.ssl.X509TrustManager;

public class SSLClass implements X509TrustManager {

	private X509TrustManager x509 = null;

	public SSLClass(KeyStore key) {
		TrustManagerFactory trustF;
		try {
			trustF = TrustManagerFactory.getInstance("SunX509");
			trustF.init(key);
			TrustManager trusts[] = trustF.getTrustManagers();
			if (trusts.length == 0) {
				throw new Exception("SunX509 trust manager not supported");
			} else {
				x509 = (X509TrustManager) trusts[0];
			}
			return;
		} catch (NoSuchAlgorithmException e) {
			System.out.println(">>>>>[SSLClass]" + e);
		} catch (KeyStoreException e) {
			System.out.println(">>>>>[SSLClass]" + e);
		} catch (Exception e) {
			System.out.println(">>>>>[SSLClass]" + e);
		}
	}

	public X509Certificate[] getAcceptedIssuers() {
		return x509.getAcceptedIssuers();
	}

	public boolean isClientTrusted(X509Certificate[] arg0) {
		return x509.isClientTrusted(arg0);
	}

	public boolean isServerTrusted(X509Certificate[] certificates) {
		if (certificates != null && certificates.length == 1) {
			X509Certificate certificate = certificates[0];
			try {
				certificate.checkValidity();
			} catch (CertificateException e) {
				System.out.println(">>>>>[SSLClass.isServerTrusted]" + e);
				return false;
			}
			return true;
		} else {
			return x509.isServerTrusted(certificates);
		}
	}
}
