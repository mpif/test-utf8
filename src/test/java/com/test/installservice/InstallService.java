package com.test.installservice;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.support.DaemonLoader;
import org.apache.commons.daemon.support.DaemonWrapper;

public class InstallService extends DaemonWrapper {

	// private static Controller controller = null;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DaemonLoader.Context context = new DaemonLoader.Context();
		context.setArguments(args);

		// String fileName = "";
		// DaemonConfiguration config = new DaemonConfiguration();
		// config.load(fileName);

		// controller = new Controller();
		InstallService service = new InstallService();
		try {
			service.init(context);

			if (args[0].equals("start")) {
				service.start();
			} else if (args[0].equals("stop")) {
				service.stop();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void init(DaemonContext arg0) throws Exception {
		System.out.println("service init");
		super.init(arg0);
	}

	@Override
	public void start() throws Exception {
		System.out.println("service start");
		int port = 12000;
		int backlog = 20;
		ServerSocket serverSocket = new ServerSocket(port, backlog);
		while (true) {
			Socket socket = serverSocket.accept();
			DataInputStream in = new DataInputStream(socket.getInputStream());
			PrintStream out = new PrintStream(socket.getOutputStream());
			String str = "";
			while ((str = in.readLine()) != null) {
				out.println("hello: " + str);
			}
		}
		// super.start();
	}

	@Override
	public void stop() throws Exception {
		System.out.println("service stop");
		System.exit(0);
		super.stop();
	}

}
