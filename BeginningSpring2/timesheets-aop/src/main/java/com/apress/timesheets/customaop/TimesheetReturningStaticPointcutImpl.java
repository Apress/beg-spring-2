package com.apress.timesheets.customaop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import com.apress.timesheets.entity.Timesheet;
import com.apress.timesheets.service.TimesheetService;

/**
 * An additional static pointcut to catch methods returning Timesheet objects
 * 
 * @author Dave Minter
 */
public class TimesheetReturningStaticPointcutImpl extends StaticMethodMatcherPointcut {
   private static final Logger log = Logger.getLogger(TimesheetStaticPointcutImpl.class);
   
   public boolean matches(final Method method, final Class type) {
      log.info("Called the timesheet returning static method pointcut (for class " + type.getSimpleName() + " method " + method.getName());
      
      // Determine whether the type implements the UserAccountService interface
      if( !TimesheetService.class.isAssignableFrom(type)) {
         log.info("Timesheet return matcher (class): No match");         
         return false;
      }
      
      // The return value must be Timesheet or a derived class
      if(!Timesheet.class.isAssignableFrom(method.getReturnType())) {
         log.info("Timesheet return matcher (method): No match");         
         return false;
      }

      log.info("Timesheet return matcher matches - advice will apply");
      return true;
   }
}
