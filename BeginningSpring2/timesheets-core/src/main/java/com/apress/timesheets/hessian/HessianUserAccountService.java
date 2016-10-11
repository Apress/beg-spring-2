package com.apress.timesheets.hessian;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface HessianUserAccountService extends Remote {
	public List<String> listUserNames() throws RemoteException;
}
