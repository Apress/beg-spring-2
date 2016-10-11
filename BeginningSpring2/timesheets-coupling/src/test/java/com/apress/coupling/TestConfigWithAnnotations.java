package com.apress.coupling;

import junit.framework.TestCase;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.apress.coupling.config.PropertyTypeExample;

public class TestConfigWithAnnotations extends TestCase {
   public void testConfigWithAnnotations() {
      final BeanFactory bf = 
         new ClassPathXmlApplicationContext("propertyExampleContext.xml");
      
      final PropertyTypeExample pte = 
         (PropertyTypeExample)bf.getBean("pte");
      pte.dump();
   }
}
