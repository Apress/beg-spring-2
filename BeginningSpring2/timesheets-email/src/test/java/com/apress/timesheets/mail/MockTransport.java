package com.apress.timesheets.mail;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.URLName;

public class MockTransport extends Transport {

   public MockTransport(final Session session,final URLName urlName) {
      super(session, urlName);
   }

   @Override
   public void sendMessage(final Message message,final Address[] recipients) throws MessagingException {
   }
}
