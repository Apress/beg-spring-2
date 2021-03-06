package com.apress.timesheets.remote;

import java.rmi.RemoteException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.apress.timesheets.burlap.BurlapUserAccountService;

public class BurlapExample {
   public static void main(String... args) throws RemoteException {
      // Get the local configured context
      final ApplicationContext ctx = 
         new ClassPathXmlApplicationContext("timesheet-client.xml");
      
      // Retrieve a bean (the proxy to the remote service)
      final BurlapUserAccountService service = 
         (BurlapUserAccountService)ctx.getBean("burlapUserAccountService");
      
      // Extract data from the bean (via the remote service)
      final List<String> users = service.listUserNames();

      System.out.println("(Burlap) User List");
      System.out.println("==================");
      for( final String user : users ) {
         System.out.println(user);
      }
   }
}
