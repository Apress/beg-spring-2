package com.apress.timesheets.customaop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.support.StaticMethodMatcherPointcut;

import com.apress.timesheets.entity.UserAccount;
import com.apress.timesheets.service.TimesheetService;

/**
 * Defines a pointcut based purely on the class and method signature.
 *
 * @author Dave Minter
 */
public class TimesheetStaticPointcutImpl extends StaticMethodMatcherPointcut {
   private static final Logger log = Logger.getLogger(TimesheetStaticPointcutImpl.class);
   
   public boolean matches(final Method method, final Class type) {
      log.info("Called the STATIC matcher pointcut (class " + type.getName() + ", method " + method.getName() + ")");
      
      // Determine whether the type implements the UserAccountService interface
      if( !TimesheetService.class.isAssignableFrom(type)) {
         log.info("STATIC matcher (class): No match");         
         return false;
      }
      
      // Must take at least one parameter
      if( method.getParameterTypes().length == 0 ) {
         log.info("STATIC matcher (method): No match");         
         return false;
      }
      
      // The first parameter must be UserAccount or a derived class
      if(!UserAccount.class.isAssignableFrom(method.getParameterTypes()[0])) {
         log.info("STATIC matcher (param types): No match ["+method.getParameterTypes()[0].getSimpleName()+"]");         
         return false;
      }

      log.info("STATIC matcher matches - advice will apply");
      return true;
   }
}
