package com.apress.timesheets.mail;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.Provider;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class MockJavaMailSender implements JavaMailSender {

   private static final Logger log = Logger.getLogger(MockJavaMailSender.class);
   private Session session;
   
   private List<Message> mimeMessages = new ArrayList<Message>();
   private List<SimpleMailMessage> simpleMessages = new ArrayList<SimpleMailMessage>();
   
   public MimeMessage createMimeMessage() {
      log.info("Message created");
      try {
         final Provider provider = new Provider(Provider.Type.TRANSPORT,"smtp",MockTransport.class.getName(),"Apress","1.0");
         session = Session.getInstance(new Properties());
         session.setProvider(provider);
         return new MimeMessage(session);
      } catch( final NoSuchProviderException e) {
         throw new RuntimeException("Mock JavaMailSender provider failed",e);
      }
   }

   public MimeMessage createMimeMessage(final InputStream inputStream) throws MailException {
      throw new UnsupportedOperationException("Mock object does not support this method");
   }

   public void send(final MimeMessage message) throws MailException {
      log.info("send MimeMessage");
      mimeMessages.add(message);
   }

   public void send(final MimeMessage[] messages) throws MailException {
      log.info("send MimeMessage[]");
      for( final Message message : messages ) mimeMessages.add(message);
   }

   public void send(final MimeMessagePreparator preparator) throws MailException {
      try {
         log.info("send MimeMessagePreparator");
         final MimeMessage message = createMimeMessage();
         preparator.prepare(message);
         mimeMessages.add(message);
      } catch(Exception e ) {
         throw new MockMailException(e);
      }
   }

   public void send(final MimeMessagePreparator[] preparators) throws MailException {
      try {
         log.info("send MimeMessagePreparator[]");
         for( final MimeMessagePreparator preparator : preparators ) {
            final MimeMessage message = createMimeMessage();
            preparator.prepare(message);
            mimeMessages.add(message);
         }
      } catch(Exception e ) {
         throw new MockMailException(e);
      }
   }

   public void send(final SimpleMailMessage message) throws MailException {
      log.info("send SimpleMailMessage");
      simpleMessages.add(message);
   }

   public void send(final SimpleMailMessage[] messages) throws MailException {
      log.info("send SimpleMailMessage[]");
      for( final SimpleMailMessage message : messages ) simpleMessages.add(message);
   }

   public List<Message> getMimeMessages() {
      return mimeMessages;
   }

   public List<SimpleMailMessage> getSimpleMessages() {
      return simpleMessages;
   }
   
   public int getMessageCount() {
      return mimeMessages.size()+
         simpleMessages.size();
   }
   
   public void clear() {
      log.info("Clearing message lists");
      mimeMessages.clear();
      simpleMessages.clear();      
   }
}
