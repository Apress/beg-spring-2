package com.apress.coupling;

import junit.framework.TestCase;

public class TestCouplingExamples extends TestCase {
   public void testCouplingExamples() {
      final TightlyCoupled tc = new TightlyCoupled();
      tc.sendMessage();
      
      final Transport smtp = new SmtpImpl();
      final LooselyCoupled lc1 = new LooselyCoupled(smtp);
      lc1.sendMessage();
      

      final Transport soap = new SoapImpl();
      final LooselyCoupled lc2 = new LooselyCoupled(soap);
      lc2.sendMessage();
   }
}
