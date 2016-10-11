package com.apress.timesheets;

import com.apress.timesheets.entity.UserAccount;

public class TimesheetSecurityException extends RuntimeException {
   private static final long serialVersionUID = 0L;
   private UserAccount account;

   public TimesheetSecurityException(final String message, final UserAccount account) {
      super(message);
      this.account = account;
   }

   public UserAccount getAccount() {
      return account;
   }

   public void setAccount(UserAccount account) {
      this.account = account;
   }
}
