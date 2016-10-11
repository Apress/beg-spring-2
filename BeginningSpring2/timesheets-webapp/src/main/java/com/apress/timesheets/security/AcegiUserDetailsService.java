package com.apress.timesheets.security;

import org.acegisecurity.userdetails.UserDetails;
import org.acegisecurity.userdetails.UserDetailsService;
import org.acegisecurity.userdetails.UsernameNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.annotation.Transactional;

import com.apress.timesheets.dao.SecurityDao;

@Transactional
public class AcegiUserDetailsService implements UserDetailsService {
   private static final Logger log = Logger.getLogger(AcegiUserDetailsService.class);
   private SecurityDao securityDao;

   public UserDetails loadUserByUsername(final String username)
      throws UsernameNotFoundException, 
             DataAccessException 
   {
      log.info("Retrieving credentials for: " + username);
      final UserDetails user = securityDao.findUser(username);
      log.info("Obj Retrieved: " + user);
      return user;
   }

   @Required
   public void setSecurityDao(SecurityDao securityDao) {
      this.securityDao = securityDao;
   }
}
