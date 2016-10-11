package com.apress.timesheets.mvc.webflow;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import com.apress.timesheets.entity.UserAccount;
import com.apress.timesheets.service.UserAccountService;

public class CreateUserAction extends FormAction {
   
   private UserAccountService userAccountService;
   
   public Event edit(final RequestContext ctx) throws Exception {
      Logger.getLogger(CreateUserAction.class).info("save(ctx) called");
      return success();
   }
   
   public Event save(final RequestContext ctx) throws Exception {
      Logger.getLogger(CreateUserAction.class).info("save(ctx) called");
      final CreateUserForm form = (CreateUserForm)getFormObject(ctx);
      final UserAccount account = new UserAccount(form.getUsername());
      userAccountService.createUser(account);
      return success();
   }

   @Required
   public void setUserAccountService(UserAccountService userAccountService) {
      this.userAccountService = userAccountService;
   }
}
