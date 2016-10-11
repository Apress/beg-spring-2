package com.apress.timesheets;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.apress.timesheets.entity.Timesheet;
import com.apress.timesheets.entity.UserAccount;
import com.apress.timesheets.entity.UserRole;
import com.apress.timesheets.util.PrincipalHelper;

@Aspect
public class TimesheetSecurityAdvice {
   private static final Logger log = Logger
         .getLogger(TimesheetSecurityAdvice.class);
   
   @Pointcut("args(account,..)")
   private void accountParameterOperation(UserAccount account) {}
   
   @Pointcut("execution(com.apress.timesheets.entity.Timesheet *(..))")
   private void timesheetReturningOperation() {}

   @Pointcut("execution(* com.apress.timesheets.service.TimesheetService.*(..))")
   private void accountServiceOperation() {}
   
   @Pointcut("accountServiceOperation() && timesheetReturningOperation()")
   private void findTimesheetOperation() {}
   
   @Pointcut("accountServiceOperation() && accountParameterOperation(account)")
   private void accountTimesheetOperation(UserAccount account) {}   

   /**
    * Applied as an aspect to list methods that retrieve data for a specific
    * user. Checks that the user concerned and the logged-in user match or that
    * the logged-in user has administrative privileges. If neither is the case
    * then a runtime TimesheetSecurityException is thrown.
    * 
    * @param account
    *           The account for which information is to be retrieved
    * @throws TimesheetSecurityException
    *            Thrown if the user has no permissions for the accessed object
    */
   //@Before("com.apress.timesheets.TimesheetSecurityPointcuts.accountTimesheetOperation(account)")
   @Before("accountTimesheetOperation(account)")
   public void list(final UserAccount account) {
      log.info("AOP list method called for user: " + account);
      validateCurrentUser();

      if (usersMatch(account) || isAdministrator()) {
         log.info("User is loading own data, or user is administrator");
         return;
      }

      final UserAccount currentUser = PrincipalHelper.getUser();
      log.info("Current user (" + currentUser
            + ")has no permission to load the target user's data - abending");

      throw new TimesheetSecurityException(
            "Access violation while attempting to list resources", currentUser);
   }

   /**
    * Applied as an aspect to find methods that retrieve Timesheet objects; the
    * method will be invoked on the return value of the method in question.
    * Checks that the user concerned and the logged-in user match or that the
    * logged-in user has administrative privileges. If neither is the case then
    * a runtime TimesheetSecurityException is thrown.
    * 
    * @param timesheet
    *           The returned timesheet whose ownership is in doubt.
    * @throws TimesheetSecurityException
    *            Thrown if the user has no permissions for the accessed object
    */
   @AfterReturning(pointcut=
//"com.apress.timesheets.TimesheetSecurityPointcuts.findTimesheetOperation()",
      "findTimesheetOperation()",
      returning = "timesheet")
   public void findTimesheet(final Timesheet timesheet) {
      log.info("AOP find method called for returned timesheet: " + timesheet);
      validateCurrentUser();

      final UserAccount account = timesheet.getConsultant();
      if (usersMatch(account) || isAdministrator()) {
         log.info("User is loading own data, or user is administrator");
         return;
      }

      final UserAccount currentUser = PrincipalHelper.getUser();
      log.info("Current user (" + currentUser
            + ")has no permission to load the target user's data - abending");

      throw new TimesheetSecurityException(
            "Access violation while attempting to list resources", currentUser);
   }

   /**
    * Check to see if the provided user matches the currently logged in user
    * 
    * @param target
    *           The user to compare with the logged in user
    * @return True if the user accounts match
    */
   private boolean usersMatch(final UserAccount target)
         throws TimesheetSecurityException {
      final UserAccount currentUser = PrincipalHelper.getUser();
      log.info("Comparing target user ID (" + target.getId()
            + ") against current user ID (" + currentUser.getId() + ")");
      return target.getId().equals(currentUser.getId());
   }

   /**
    * Check to see if there is a currently logged in (authenticated) user. The
    * method will throw an exception if there is no such user.
    * 
    * @throws TimesheetSecurityException
    */
   private void validateCurrentUser() throws TimesheetSecurityException {
      final UserAccount currentUser = PrincipalHelper.getUser();
      if (currentUser == null) {
         throw new TimesheetSecurityException("No user logged in", null);
      }
   }

   /**
    * Determine if the currently logged in user has administrative privileges
    * 
    * @return True if the current user is an administrator
    */
   private boolean isAdministrator() {
      final UserAccount currentUser = PrincipalHelper.getUser();
      for (final UserRole role : currentUser.getRoles()) {
         if (UserRole.ROLE_ADMINISTRATOR.equals(role.getRoleName())) {
            log.info("Current user is administrator.");
            return true;
         }
      }
      log.info("Current user is not an administrator.");
      return false;
   }
}
