package com.apress.timesheets.util;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.log4j.Logger;

import com.apress.timesheets.entity.UserAccount;
import com.apress.timesheets.security.AcegiUserDetails;

/**
 * This class presents a simple mechanism to acquire the 
 * UserAccount entity from the security context.
 * 
 * @author Dave Minter
 */
public class PrincipalHelper {
   private static final Logger log = Logger.getLogger(PrincipalHelper.class);
   
   private PrincipalHelper() {}
   
   public static UserAccount getUser() {
      log.info("Grabbing user account from security context");
      final SecurityContext sc = SecurityContextHolder.getContext();
      final Authentication auth = sc.getAuthentication();
      log.info("Authentication: " + auth);
      if( auth == null ) return null;
      if( !auth.isAuthenticated() ) return null;
      final AcegiUserDetails acegiAccount = (AcegiUserDetails)auth.getPrincipal();
      log.info("Acegi account: " + acegiAccount);
      if( acegiAccount == null ) return null;
      final UserAccount account = acegiAccount.getUserAccount();
      log.info("Timesheet account: " + account);
      return account;
   }
}
