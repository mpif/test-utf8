package com.codefans.jbossremoting.example01;

import org.jboss.remoting.transporter.TransporterServer;

public class Server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TransporterServer server = TransporterServer.createTransporterServer("socket://localhost:5400",
					new DateProcessorImpl(), DateProcessor.class.getName());
			// Thread.sleep(10000);
			// server.stop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
