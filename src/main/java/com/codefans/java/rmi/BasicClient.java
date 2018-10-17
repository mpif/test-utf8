package com.codefans.java.rmi;

import java.rmi.Naming;

public class BasicClient {

	public static void main(String[] args) {
		BasicClient basicClient = new BasicClient();
		basicClient.call();
	}

	public void call() {
		// String url = "rmi://localhost:5000/email_201501_searcher";
		// String url = "rmi://127.0.0.1:5000/email_201501_searcher";
		// String url = "rmi://10.0.0.47:4000/email_201411_searcher";
		String url = "rmi://10.0.0.127:4000/RemoteSearchAuthorizeService";
		Searcher searcher = null;
		try {
			searcher = this.lookupRemote(url);
			searcher.search();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private Searcher lookupRemote(String url) throws Exception {
		return (Searcher) Naming.lookup(url);
	}

}
