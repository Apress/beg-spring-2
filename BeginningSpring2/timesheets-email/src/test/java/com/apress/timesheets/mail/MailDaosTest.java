package com.apress.timesheets.mail;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import junit.framework.TestCase;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import com.apress.timesheets.entity.Timesheet;
import com.apress.timesheets.entity.UserAccount;

public class MailDaosTest extends TestCase {
   private static final Logger log = Logger.getLogger(MailDaosTest.class);

   private static final String FROM = "from@example.com";
   private static final String TO = "to@example.com";
   private static final String SUBJECT = "subject";
   
   private final MockJavaMailSender mailSender = new MockJavaMailSender();
   private Timesheet timesheet;

   @Override
   protected void setUp() throws Exception {
      final UserAccount account = new UserAccount("username");
      final Calendar startDate = Calendar.getInstance();
      timesheet = new Timesheet(account,startDate);
   }

   @Override
   protected void tearDown() throws Exception {
      mailSender.clear();
   }
   
   public void testSimpleMailDao()
      throws MessagingException,IOException
   {
      final SimpleMailDaoImpl impl = new SimpleMailDaoImpl();
      impl.setFromAddress(FROM);
      impl.setRcptAddress(TO);
      impl.setSubject(SUBJECT);
      impl.setMailSender(mailSender);
      impl.sendTimesheetUpdate(timesheet);
      
      assertEquals(1,mailSender.getMessageCount());      
      final List<SimpleMailMessage> received = mailSender.getSimpleMessages();
      assertEquals(1,received.size());
      
      final SimpleMailMessage message = received.get(0);
      assertEquals(1,message.getTo().length);
      assertEquals(TO,message.getTo()[0]);
      assertEquals(FROM,message.getFrom());
      assertEquals(SUBJECT,message.getSubject());
      assertEquals("A timesheet has been updated by user: username",message.getText());
   }
   
   public void testVelocityMailDao()
      throws Exception
   {
      final VelocityEngineFactoryBean factory = new VelocityEngineFactoryBean();
      final Properties props = new Properties();
      props.setProperty("resource.loader", "class");
      props.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
      factory.setVelocityProperties(props);
      factory.afterPropertiesSet();
      final VelocityEngine engine = (VelocityEngine)factory.getObject();
      
      final VelocityMailDaoImpl impl = new VelocityMailDaoImpl();
      impl.setFromAddress(FROM);
      impl.setRcptAddress(TO);
      impl.setSubject(SUBJECT);
      impl.setVelocityEngine(engine);
      impl.setVelocityMacroPath("velocity/timesheet/update.vm");
      impl.setMailSender(mailSender);
      
      impl.sendTimesheetUpdate(timesheet);
      
      assertEquals(1,mailSender.getMessageCount());
      final List<Message> messages = mailSender.getMimeMessages();
      assertEquals(1,messages.size());
      final Message message = messages.get(0);
      
      final Address[] senders = message.getFrom();
      assertEquals(1,senders.length);
      final InternetAddress from = (InternetAddress)senders[0];
      assertEquals(FROM,from.getAddress());
      
      final Address[] recipients = message.getAllRecipients();
      assertEquals(1,recipients.length);
      final InternetAddress to = (InternetAddress)recipients[0];
      assertEquals(TO,to.getAddress());
      
      final String subject = message.getSubject();
      assertEquals(SUBJECT,subject);
      
      // Unipart message, so the content is a String
      final String content = (String)message.getContent();
      assertTrue(content.contains("User username has updated one of their timesheets."));
   }
   
   public void testVelocityImageMailDao() 
      throws Exception 
   {
      final VelocityEngineFactoryBean factory = new VelocityEngineFactoryBean();
      final Properties props = new Properties();
      props.setProperty("resource.loader", "class");
      props.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
      factory.setVelocityProperties(props);
      factory.afterPropertiesSet();
      final VelocityEngine engine = (VelocityEngine)factory.getObject();
      
      final VelocityImageMailDaoImpl impl = new VelocityImageMailDaoImpl();
      impl.setFromAddress(FROM);
      impl.setRcptAddress(TO);
      impl.setSubject(SUBJECT);
      impl.setVelocityEngine(engine);
      impl.setVelocityMacroPath("velocity/timesheet/attachments.vm");
      impl.setMailSender(mailSender);
      
      final ClassPathResource image = new ClassPathResource("strawberry.jpg");
      impl.setImage(image);
      impl.setAttachment(image);
      
      impl.sendTimesheetUpdate(timesheet);
      
      assertEquals(1,mailSender.getMessageCount());
      final List<Message> messages = mailSender.getMimeMessages();
      assertEquals(1,messages.size());
      final MimeMessage message = (MimeMessage)messages.get(0);
      
      final Address[] senders = message.getFrom();
      assertEquals(1,senders.length);
      final InternetAddress from = (InternetAddress)senders[0];
      assertEquals(FROM,from.getAddress());
      
      final Address[] recipients = message.getAllRecipients();
      assertEquals(1,recipients.length);
      final InternetAddress to = (InternetAddress)recipients[0];
      assertEquals(TO,to.getAddress());
      
      final String subject = message.getSubject();
      assertEquals(SUBJECT,subject);

      boolean messageTextSent = false;
      boolean messageInlineImageSent = false;
      boolean messageAttachmentSent = false;

      final MimeMultipart multipart = (MimeMultipart)message.getContent();
      for(int i=0; i< multipart.getCount();i++) {
         final MimeBodyPart part = (MimeBodyPart)multipart.getBodyPart(i);
         if( partType(part,"multipart/related")) {
            final MimeMultipart mm = (MimeMultipart)part.getDataHandler().getContent();
            for(int j = 0; j < mm.getCount(); j++) {
               final BodyPart bp = mm.getBodyPart(j);
               if( partType(bp,"text/html")) {
                  log.info("Message Text sent");
                  messageTextSent = true;
               } else if( partType(bp,"image/jpeg")) {
                  log.info("Message Inline Image sent");
                  messageInlineImageSent = true;
               }
            }
         } else if( partType(part,"image/jpeg")) {
            log.info("Message Attachment sent");
            messageAttachmentSent = true;
         }
      }
      
      assertTrue(messageTextSent);
      assertTrue(messageInlineImageSent);
      assertTrue(messageAttachmentSent);
   }
   
   private boolean partType(final BodyPart part, final String type) throws MessagingException {
      return part.getDataHandler().getContentType().startsWith(type);
   }
}
