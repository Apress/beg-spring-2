package com.apress.timesheets.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Required;

import com.apress.timesheets.dao.SecurityDao;
import com.apress.timesheets.dao.TimesheetDao;

public class PopulateDataProcessor implements InitializingBean {
   private static final Logger log = Logger.getLogger(PopulateDataProcessor.class); 
   private SecurityDao securityDao;
   private TimesheetDao timesheetDao;

   public void afterPropertiesSet() throws Exception {
      log.info("Invoking populateDatabase()");
      securityDao.populateDatabase();
      timesheetDao.populateDatabase();
   }

   @Required
   public void setSecurityDao(SecurityDao securityDao) {
      this.securityDao = securityDao;
   }

   @Required
   public void setTimesheetDao(TimesheetDao timesheetDao) {
      this.timesheetDao = timesheetDao;
   }
}
