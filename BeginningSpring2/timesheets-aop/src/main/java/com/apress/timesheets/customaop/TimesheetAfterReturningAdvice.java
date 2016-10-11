package com.apress.timesheets.customaop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;

import com.apress.timesheets.TimesheetSecurityAdvice;
import com.apress.timesheets.entity.Timesheet;

public class TimesheetAfterReturningAdvice implements AfterReturningAdvice {
   private static final Logger log = Logger.getLogger(TimesheetAfterReturningAdvice.class);
   private final TimesheetSecurityAdvice advice = new TimesheetSecurityAdvice();

   public void afterReturning(final Object retVal, final Method method, final Object[] args, final Object target) throws Throwable {
      log.info("Invoking the after returning custom implementation");
      advice.findTimesheet((Timesheet)retVal);
   }
}
