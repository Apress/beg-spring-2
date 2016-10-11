package com.apress.timesheets.customaop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import com.apress.timesheets.TimesheetSecurityAdvice;
import com.apress.timesheets.entity.Timesheet;
import com.apress.timesheets.entity.UserAccount;

public class TimesheetInterceptor implements MethodInterceptor {
   private static final Logger log = Logger.getLogger(TimesheetInterceptor.class);
   private final TimesheetSecurityAdvice advice = new TimesheetSecurityAdvice();
   
   public Object invoke(final MethodInvocation invocation) throws Throwable {
      log.info("Invoking the custom timesheet interceptor");
      try{
         log.info("Invoking advice logic before the method call");
         final Object[] args = invocation.getArguments();
         if( (args.length >= 1) && (args[0] instanceof UserAccount)) {
            advice.list((UserAccount)args[0]);
         }
         
         log.info("Making the method call");
         final Object retVal = invocation.proceed();
         
         log.info("Invoking advice logic after the method call");
         if( retVal instanceof Timesheet ) {
            advice.findTimesheet((Timesheet)retVal);
         }
         
         log.info("Custom timesheet interceptor threw no exceptions");
         return retVal;
      } finally {
         log.info("Custom timesheet interceptor completed");
      }
   }
}
