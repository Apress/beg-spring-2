package com.apress.coupling.config;

import org.springframework.beans.factory.annotation.Required;

public class RefExample {
   private String text;
   
   @Required
   public void setText(final String text) {
      this.text = text;
   }
   
   public String getText() {
      return this.text;
   }
   
   public String toString() {
      return text;
   }
}
