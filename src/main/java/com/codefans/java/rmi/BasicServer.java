package com.codefans.java.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BasicServer {

	public static void main(String[] args) {
		BasicServer basicServer = new BasicServer();
		basicServer.startup();
	}

	public void startup() {
		try {

			Searcher searcher = new LocalSearcher();

			int port = 4000;

			// create Registry is necessary when we use either method one or
			// method two
			Registry registry = LocateRegistry.createRegistry(port);

			// Registry registry2 = LocateRegistry.createRegistry( port );

			// method one
			// registry.rebind( "email_201501_searcher" , searcher );

			// method two
			// Naming.rebind( "rmi://10.0.0.47:5000/email_201501_searcher" ,
			// searcher );
			Naming.rebind("rmi://10.0.0.50:" + port + "/RemoteSearchAuthorizeService", searcher);
			// Naming.rebind( "rmi://192.168.1.47:5000/email_201501_searcher" ,
			// searcher );
			// Naming.rebind( "rmi://192.168.1.47:5000/email_201501_searcher" ,
			// new LocalSearcher() );

		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}
