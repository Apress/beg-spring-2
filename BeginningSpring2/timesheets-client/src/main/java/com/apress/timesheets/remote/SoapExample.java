package com.apress.timesheets.remote;

import java.rmi.RemoteException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.apress.timesheets.soap.SoapUserAccountService;

public class SoapExample {
	public static void main(String[] args) throws RemoteException {
	      // Get the local configured context
	      final ApplicationContext ctx = 
	         new ClassPathXmlApplicationContext("timesheet-client.xml");
	      
	      // Retrieve a bean (the proxy to the remote service)
	      final SoapUserAccountService service = 
	         (SoapUserAccountService)ctx.getBean("soapUserAccountService");
	      
	      // Extract data from the bean (via the remote service)
	      final String[] users = service.listUserNames();

	      System.out.println("(SOAP) User List");
	      System.out.println("================");
	      for( final String user : users ) {
	         System.out.println(user);
	      }
	}
}
