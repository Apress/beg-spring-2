package com.apress.timesheets.soap;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface SoapUserAccountService extends Remote {
	public String[] listUserNames() throws RemoteException;
}
