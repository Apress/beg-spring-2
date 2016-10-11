package com.apress.coupling;

import junit.framework.TestCase;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;

public class TestConfigFromCode extends TestCase {
   public void testConfigFromCode() {
      // Establish the context to 
      // contain the bean definitions
      final DefaultListableBeanFactory bf = new DefaultListableBeanFactory();
      
      // Register the transport implementations
      bf.registerBeanDefinition("smtp", 
               new RootBeanDefinition(SmtpImpl.class,true));
      bf.registerBeanDefinition("soap", 
               new RootBeanDefinition(SoapImpl.class,true));

      // Register and configure the SMTP example as 
      // a bean definition
      BeanDefinitionBuilder builder = null;
      builder = BeanDefinitionBuilder.
         rootBeanDefinition(LooselyCoupled.class);
      builder = builder.setSingleton(true);
      builder = builder.addConstructorArgReference("smtp");
      bf.registerBeanDefinition("looseSmtp",builder.getBeanDefinition());
      
      // Register and configure the SOAP example as
      // a bean definition
      builder = BeanDefinitionBuilder.
         rootBeanDefinition(LooselyCoupled.class);
      builder = builder.setSingleton(true);
      builder = builder.addConstructorArgReference("soap");
      bf.registerBeanDefinition("looseSoap",builder.getBeanDefinition());

      // Instantiate the smtp example and invoke it
      final LooselyCoupled lc1 = (LooselyCoupled)bf.getBean("looseSmtp");
      lc1.sendMessage();

      // Instantiate the soap example and invoke it
      final LooselyCoupled lc2 = (LooselyCoupled)bf.getBean("looseSoap");
      lc2.sendMessage();
   }
}
