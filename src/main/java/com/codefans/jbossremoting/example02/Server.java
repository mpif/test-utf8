package com.codefans.jbossremoting.example02;

import org.jboss.remoting.transporter.TransporterServer;

public class Server {
	private String locatorURI = "socket://localhost:5400";
	private TransporterServer server = null;

	public void start() throws Exception {
		server = TransporterServer.createTransporterServer(locatorURI, new CustomerProcessorImpl());
	}

	public void stop() {
		if (server != null) {
			server.stop();
		}
	}

	public static void main(String[] args) {
		Server server = new Server();
		try {
			server.start();

			Thread.currentThread().sleep(60000);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			server.stop();
		}
	}
}
