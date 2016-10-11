package com.apress.timesheets.mail;

import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.apress.timesheets.entity.Timesheet;

public class VelocityImageMailDaoImpl extends AbstractMailDaoImpl {
   private JavaMailSender mailSender;
   private String velocityMacroPath;
   private VelocityEngine velocityEngine;
   private Resource attachment;
   private Resource image;

   public void sendTimesheetUpdate(final Timesheet timesheet) {
      final MimeMessagePreparator preparator = new MimeMessagePreparator() {
         public void prepare(MimeMessage mimeMessage) throws Exception {
            final MimeMessageHelper message = new MimeMessageHelper(
                     mimeMessage, true);
            message.setTo(rcptAddress);
            message.setSubject(subject);
            message.setFrom(fromAddress);
            message.addAttachment(attachment.getFilename(), attachment);
            final Map<String, Object> model = new HashMap<String, Object>();
            model.put("timesheet", timesheet);
            final String text = VelocityEngineUtils
                     .mergeTemplateIntoString(velocityEngine,
                              velocityMacroPath, model);
            message.setText(text, true);
            message.addInline("inlineImage", image);
         }
      };
      mailSender.send(preparator);
   }

   @Required
   public void setMailSender(final JavaMailSender mailSender) {
      this.mailSender = mailSender;
   }

   @Required
   public void setVelocityEngine(final VelocityEngine velocityEngine) {
      this.velocityEngine = velocityEngine;
   }

   @Required
   public void setVelocityMacroPath(final String velocityMacroPath) {
      this.velocityMacroPath = velocityMacroPath;
   }

   @Required
   public void setAttachment(final Resource attachment) {
      this.attachment = attachment;
   }

   @Required
   public void setImage(final Resource image) {
      this.image = image;
   }
}
