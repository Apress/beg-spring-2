package com.apress.coupling;

import java.util.SortedSet;
import java.util.TreeSet;

public class Mailinglist {
   private SortedSet<String> addresses = new TreeSet<String>();
   private Transport transport = new SmtpImpl();
   
   public void addAddress(final String address) {
      addresses.add(address);
   }
   
   public void send() {
      for( final String address : addresses) {
         transport.send(address);
      }
   }
}
