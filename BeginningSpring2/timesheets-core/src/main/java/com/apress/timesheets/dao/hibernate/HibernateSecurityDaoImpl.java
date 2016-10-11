package com.apress.timesheets.dao.hibernate;

import java.util.List;

import org.acegisecurity.providers.encoding.MessageDigestPasswordEncoder;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.apress.timesheets.dao.SecurityDao;
import com.apress.timesheets.dao.UserAccountDao;
import com.apress.timesheets.entity.UserAccount;
import com.apress.timesheets.entity.UserRole;
import com.apress.timesheets.security.AcegiUserDetails;

public class HibernateSecurityDaoImpl extends HibernateDaoSupport implements SecurityDao {
   private static final Logger log = Logger.getLogger(HibernateSecurityDaoImpl.class);
   private MessageDigestPasswordEncoder passwordEncoder;
   private UserAccountDao userAccountDao;
   
   @SuppressWarnings("unchecked")
   public AcegiUserDetails findUser(final String username) {
      final List<AcegiUserDetails> users = 
         (List<AcegiUserDetails>)getHibernateTemplate().
            findByNamedQueryAndNamedParam("findAcegiUserByName", "username", username);

      if( users.size() != 0 ) {
         return users.get(0);
      } else {
         return null;
      }
   }

   public AcegiUserDetails createUser(final UserAccount account) {
      // Users are always created with "User" role.
      final UserRole user = userAccountDao.readUserRole(UserRole.ROLE_USER);
      account.getRoles().add(user);
      
      // Create the account entity
      userAccountDao.create(account);
      
      // Create the Acegi (security) entity
      final String password = passwordEncoder.encodePassword("password", "");
      final AcegiUserDetails acegiAccount = new AcegiUserDetails(account,password);
      getHibernateTemplate().persist(acegiAccount);
      return acegiAccount;
   }

   public void deleteUser(final UserAccount account) {
      // TODO:
      // Get the Acegi account
      // Delete it
      // Delete the normal account
      // Done.
   }

   public void populateDatabase() {
      // This method will be invoked from outside
      // a request context (during initialization)
      // so it's our responsibility to manage the
      // session during this call.
      final Session session = getSession();
      try {
         populateUserRoles(session);
         populateAdminUser(session);
      } finally {
         releaseSession(session);
      }
   }
   
   private void populateUserRoles(final Session session) {
      log.info("Counting pre-existing roles");

      // Count the roles
      final Query query = 
         session.createQuery("select count(*) from UserRole");
      final Long count = (Long)query.uniqueResult();
      log.info("Found " + count + " pre-existing role(s)");

      // If there aren't any
      if( count == 0L ) {
         log.info("Creating roles");
         
         // Create the role set
         final UserRole admin = new UserRole(UserRole.ROLE_ADMINISTRATOR);
         final UserRole user = new UserRole(UserRole.ROLE_USER);

         log.info("Persisting roles");
         session.persist(admin);
         session.persist(user);
         session.flush();            
         
         log.info("New roles created");
      }
   }
   
   private void populateAdminUser(final Session session) {
      log.info("Counting pre-existing users");

      // Count the users
      final Query query = 
         session.createQuery("select count(*) from UserAccount");
      final Long count = (Long)query.uniqueResult();
      log.info("Found " + count + " pre-existing user(s)");

      // If there aren't any
      if( count == 0L ) {
         log.info("Creating a new user");
         
         // Create a suitable hashed admin password
         final String password = 
            passwordEncoder.encodePassword("setec", "");

         // Create the general user info (admin users
         // have all permissions, so we just add all
         // the available roles).
         final UserAccount account = new UserAccount("admin");
         account.getRoles().addAll(findAllRoles(session));
         
         // Create the acegi user info
         final AcegiUserDetails details = 
            new AcegiUserDetails(account,password);

         log.info("Persisting new user details");
         // Store all the information in the DB.
         session.persist(account);
         session.persist(details);
         session.flush();            
         
         log.info("New user created");
      }
   }

   @SuppressWarnings("unchecked")
   private List<UserRole> findAllRoles(final Session session) {
      return session.getNamedQuery("findUserRoles").list();
   }

   public MessageDigestPasswordEncoder getPasswordEncoder() {
      return passwordEncoder;
   }

   public void setPasswordEncoder(
            MessageDigestPasswordEncoder passwordEncoder) {
      this.passwordEncoder = passwordEncoder;
   }

   public UserAccountDao getUserAccountDao() {
      return userAccountDao;
   }

   public void setUserAccountDao(UserAccountDao userAccountDao) {
      this.userAccountDao = userAccountDao;
   }
}