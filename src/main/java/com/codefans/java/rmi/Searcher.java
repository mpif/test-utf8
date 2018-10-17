package com.codefans.java.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

//extends from Remote is necessary
public interface Searcher extends Remote {
	// throws RemoteException is necessary
	public void search() throws RemoteException;

}
