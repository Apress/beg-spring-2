package com.apress.coupling;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestConfigFromXml extends TestCase {
   public void testConfigFromXml() {
      final BeanFactory bf = 
         new ClassPathXmlApplicationContext("exampleContext.xml");
      
      final LooselyCoupled c1 = 
         (LooselyCoupled)bf.getBean("looseSmtp");
      c1.sendMessage();
      
      final LooselyCoupled c2 = 
         (LooselyCoupled)bf.getBean("looseSoap");
      c2.sendMessage();
   }
}
