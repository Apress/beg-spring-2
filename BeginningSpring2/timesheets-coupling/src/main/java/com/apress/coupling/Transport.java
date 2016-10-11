package com.apress.coupling;

public interface Transport {
   public void send();
   public void send(final String address);
}
