package com.apress.timesheets.customaop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

import com.apress.timesheets.TimesheetSecurityAdvice;
import com.apress.timesheets.entity.UserAccount;

public class TimesheetBeforeAdvice implements MethodBeforeAdvice {
   private static final Logger log = Logger.getLogger(TimesheetBeforeAdvice.class);
   private final TimesheetSecurityAdvice advice = new TimesheetSecurityAdvice();
   
   public void before(final Method method,final Object[] args,final Object target) throws Throwable {
      log.info("Invoking the before advice custom implementation");
      advice.list((UserAccount)args[0]);
   }
}
