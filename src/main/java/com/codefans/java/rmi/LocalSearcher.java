package com.codefans.java.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//extends from UnicastRemoteObject is necessary
public class LocalSearcher extends UnicastRemoteObject implements Searcher {
	// this construction is generate when extends from UnicastRemoteObject
	protected LocalSearcher() throws RemoteException {
		super();
	}

	@Override
	public void search() {
		System.out.println("you are searching!!!");
	}

}
