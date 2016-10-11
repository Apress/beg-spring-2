package com.apress.timesheets.dao;

import java.util.List;

import com.apress.timesheets.entity.UserAccount;
import com.apress.timesheets.entity.UserRole;

public interface UserAccountDao {
   // Create methods
   public void create(UserAccount account);
   
   // Read methods
   public List<UserAccount> list();
   public UserAccount read(Long id);
   public UserAccount read(String accountName);   
   public UserRole readUserRole(String roleName);
   
   // Update methods
   public void update(UserAccount account);
   
   // Delete methods
   public void delete(UserAccount account);
   public void delete(Long id);
}
