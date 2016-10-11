package com.apress.timesheets.service;

import java.util.List;

import org.acegisecurity.annotation.Secured;

import com.apress.timesheets.entity.UserAccount;

public interface UserAccountService {
   @Secured({"ROLE_ADMINISTRATOR","ROLE_USER"})
   UserAccount findUser(String username);

   @Secured({"ROLE_ADMINISTRATOR"})
   void createUser(UserAccount account);
   
   @Secured({"ROLE_ADMINISTRATOR"})
   void deleteUser(UserAccount account);
   
   @Secured({"ROLE_ADMINISTRATOR"})
   void updateUser(UserAccount account);
   
   @Secured({"ROLE_ADMINISTRATOR","ROLE_USER"})
   List<UserAccount> listUsers();
}
