package com.codefans.jbossremoting.test;

import org.jboss.remoting.transporter.TransporterClient;

public class TransporterClientTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MonitoredPool mp = (MonitoredPool) TransporterClient
					.createTransporterClient("socket://localhost:5400/?serializationtype=jboss", MonitoredPool.class);
			mp.getMonitors();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
