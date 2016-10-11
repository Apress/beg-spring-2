package com.apress.coupling;

public class LooselyCoupled {
   private Transport transport;

   public LooselyCoupled(final Transport foo) {
      this.transport = foo;
   }

   public void sendMessage() {
      transport.send();
   }
}
