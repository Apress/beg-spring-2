package com.apress.timesheets.mail;

import org.springframework.beans.factory.annotation.Required;

import com.apress.timesheets.dao.EmailDao;
import com.apress.timesheets.entity.Timesheet;

abstract public class AbstractMailDaoImpl implements EmailDao {
   protected String fromAddress;
   protected String rcptAddress;
   protected String subject;

   @Required
   public void setFromAddress(String fromAddress) {
      this.fromAddress = fromAddress;
   }

   @Required
   public void setRcptAddress(String rcptAddress) {
      this.rcptAddress = rcptAddress;
   }

   @Required
   public void setSubject(String subject) {
      this.subject = subject;
   }

   abstract public void sendTimesheetUpdate(Timesheet timesheet);
}
