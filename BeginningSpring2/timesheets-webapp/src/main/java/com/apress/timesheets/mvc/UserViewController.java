package com.apress.timesheets.mvc;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.BaseCommandController;

import com.apress.timesheets.entity.UserAccount;
import com.apress.timesheets.service.UserAccountService;

public class UserViewController extends BaseCommandController {
   private static final Logger log = Logger.getLogger(UserViewController.class);
   private String userView;
   private Pattern pattern;
   private UserAccountService userAccountService;
   

   @Override
   protected ModelAndView handleRequestInternal(final HttpServletRequest request,final HttpServletResponse response) throws Exception {
      final Map<String,UserAccount> refData = new HashMap<String,UserAccount>();
      refData.put("user", loadUserByPath(request.getRequestURI()));
      return new ModelAndView(userView,refData);
   }   
   
   private UserAccount loadUserByPath(final String path) {
      log.info("Loading user by path: " + path);
      final Matcher matcher = pattern.matcher(path);
      if( !matcher.matches() ) {
         log.info("Not a valid path");
         return null;
      }
      
      final UserAccount account = userAccountService.findUser(matcher.group(1));
      log.info("Loaded: " + account);
      return account;
   }

   @Required
   public void setUserAccountService(final UserAccountService userAccountService) {
      this.userAccountService = userAccountService;
   }

   @Required
   public void setRegex(final String regex) {
      this.pattern = Pattern.compile(regex);
   }
   
   @Required
   public void setUserView(final String userView) {
      this.userView = userView;
   }
}
