package com.apress.timesheets.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.apress.timesheets.service.UserAccountService;

public class UserListController extends SimpleFormController {
   private UserAccountService userAccountService;
   
   @Override
   protected Map referenceData(final HttpServletRequest request) throws Exception {
      final Map<String,Object> refData = new HashMap<String,Object>();
      refData.put("users", userAccountService.listUsers());
      return refData;
   }

   @Required
   public void setUserAccountService(final UserAccountService userAccountService) {
      this.userAccountService = userAccountService;
   }
}
