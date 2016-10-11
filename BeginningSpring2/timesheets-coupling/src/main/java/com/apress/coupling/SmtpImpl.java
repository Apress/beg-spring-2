package com.apress.coupling;


public class SmtpImpl implements Transport {
   public void send() {
      System.out.println("Sent by SMTP!");
   }
   
   public void send(final String address) {
      System.out.println("Sent by SMTP to " + address);
   }
}
