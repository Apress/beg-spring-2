package com.apress.timesheets.dao;

import com.apress.timesheets.entity.UserAccount;
import com.apress.timesheets.security.AcegiUserDetails;

public interface SecurityDao {
	AcegiUserDetails findUser(String username);
   AcegiUserDetails createUser(UserAccount account);
   void deleteUser(UserAccount account);
	void populateDatabase();
}
