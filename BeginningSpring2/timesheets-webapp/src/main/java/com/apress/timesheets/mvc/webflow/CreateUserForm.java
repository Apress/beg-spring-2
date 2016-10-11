package com.apress.timesheets.mvc.webflow;

import java.io.Serializable;

public class CreateUserForm implements Serializable {
   private static final long serialVersionUID = 0L;
   private String username;

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }
}
