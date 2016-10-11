package com.apress.coupling;

public class TightlyCoupled {
   private Transport transport = new SmtpImpl();

   public void sendMessage() {
      transport.send();
   }
}
