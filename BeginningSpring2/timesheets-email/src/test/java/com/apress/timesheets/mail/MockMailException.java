package com.apress.timesheets.mail;

import org.springframework.mail.MailException;

public class MockMailException extends MailException {
   private static final long serialVersionUID = 0L;

   public MockMailException(final Throwable cause) {
      super(cause.getMessage(),cause);
   }
}
