package com.apress.timesheets.remote;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.apress.timesheets.entity.UserAccount;
import com.apress.timesheets.service.UserAccountService;

public class RmiExample {
   public static void main(String... args) {
      // Get the local configured context
      final ApplicationContext ctx = 
         new ClassPathXmlApplicationContext("timesheet-client.xml");
      
      // Retrieve a bean (the proxy to the remote service)
      final UserAccountService service = 
         (UserAccountService)ctx.getBean("rmiUserAccountService");
      
      // Extract data from the bean (via the remote service)
      final List<UserAccount> users = service.listUsers();

      System.out.println("(RMI) User List");
      System.out.println("===============");
      for( final UserAccount user : users ) {
         System.out.println(user.getAccountName());
      }
   }
}
