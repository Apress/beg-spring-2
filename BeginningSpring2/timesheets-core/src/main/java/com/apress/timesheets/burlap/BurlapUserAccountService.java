package com.apress.timesheets.burlap;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface BurlapUserAccountService extends Remote {
	public List<String> listUserNames() throws RemoteException;
}
