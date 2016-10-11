package com.apress.coupling;

public class SoapImpl implements Transport {
   public void send() {
      System.out.println("Sent by SOAP!");
   }
   
   public void send(final String address) {
      System.out.println("Sent by SOAP to " + address);
   }
}
