package com.apress.timesheets.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.apress.timesheets.dao.SecurityDao;
import com.apress.timesheets.dao.UserAccountDao;
import com.apress.timesheets.entity.UserAccount;

@Transactional
public class UserAccountServiceImpl implements UserAccountService {
   private UserAccountDao userAccountDao;
   private SecurityDao securityDao;

   public void createUser(final UserAccount account) {
      securityDao.createUser(account);
   }

   public void deleteUser(final UserAccount account) {
      securityDao.deleteUser(account);
   }

   public void updateUser(final UserAccount account) {
      userAccountDao.update(account);
   }

   public UserAccount findUser(final String username) {
      return userAccountDao.read(username);
   }
   
   public List<UserAccount> listUsers() {
      return userAccountDao.list();
   }

   @Required
   public void setSecurityDao(SecurityDao securityDao) {
      this.securityDao = securityDao;
   }

   @Required
   public void setUserAccountDao(final UserAccountDao userAccountDao) {
      this.userAccountDao = userAccountDao;
   }
}
