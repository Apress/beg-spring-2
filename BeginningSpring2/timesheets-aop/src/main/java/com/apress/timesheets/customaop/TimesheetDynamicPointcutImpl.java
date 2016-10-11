package com.apress.timesheets.customaop;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import com.apress.timesheets.entity.Timesheet;
import com.apress.timesheets.entity.UserRole;
import com.apress.timesheets.service.TimesheetService;
import com.apress.timesheets.util.PrincipalHelper;

/**
 *  Defines a pointcut based on the class and method signature as well as the arguments passed in at runtime
 * @author Dave Minter
 */
public class TimesheetDynamicPointcutImpl extends DynamicMethodMatcherPointcut {
   private static final Logger log = Logger.getLogger(TimesheetDynamicPointcutImpl.class);
   
   @Override
   public boolean matches(final Method method, final Class type) {
      log.info("Called the DYNAMIC matcher pointcut (class " + type.getName() + ", method " + method.getName() + ")");
      
      // Determine whether the type implements the UserAccountService interface
      if( !TimesheetService.class.isAssignableFrom(type)) {
         log.info("DYNAMIC matcher (class): No match");         
         return false;
      }
      
      // The return value must be Timesheet or a derived class
      if(!Timesheet.class.isAssignableFrom(method.getReturnType())) {
         log.info("DYNAMIC matcher (method): No match");         
         return false;
      }

      log.info("DYNAMIC matcher matches - advice MAY apply");
      return true;
   }


   public boolean matches(final Method method, final Class type, final Object[] args) {
      for( final UserRole role : PrincipalHelper.getUser().getRoles() ) {
         if("ROLE_ADMINISTRATOR".equals(role.getRoleName())) {
            log.info("DYNAMIC matcher (user is admin): No match");
            return false;
         }
      }
      
      log.info("DYNAMIC matcher matches - advice WILL apply");
      return true;
   }
}
