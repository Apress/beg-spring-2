package com.apress.timesheets.mail;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.apress.timesheets.entity.Timesheet;

public class SimpleMailDaoImpl extends AbstractMailDaoImpl {
   private static final Logger log = 
      Logger.getLogger(SimpleMailDaoImpl.class);
   private MailSender mailSender;

   public void sendTimesheetUpdate(final Timesheet timesheet) {
      try {
         final SimpleMailMessage message = new SimpleMailMessage();
         message.setTo(rcptAddress);
         message.setFrom(fromAddress);
         message.setSubject(subject);
         message.setText("A timesheet has been updated by user: "
                  + timesheet.getConsultant().getAccountName());
         mailSender.send(message);
      } catch (MailException e) {
         log.error("Failed to send timesheet update message", e);
         throw e;
      }
   }

   @Required
   public void setMailSender(MailSender mailSender) {
      this.mailSender = mailSender;
   }
}
