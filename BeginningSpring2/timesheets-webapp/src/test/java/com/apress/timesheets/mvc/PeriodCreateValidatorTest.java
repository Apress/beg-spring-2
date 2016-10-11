package com.apress.timesheets.mvc;

import java.util.Calendar;

import junit.framework.TestCase;

import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

public class PeriodCreateValidatorTest extends TestCase {
   private final PeriodCreateValidator validator = new PeriodCreateValidator();
   private PeriodCreateForm form;
   private Errors errors;

   @Override
   protected void setUp() throws Exception {
      form = new PeriodCreateForm();
      errors = new BindException(form,"command");
   }

   public void testTypeCheckOk() {
      assertTrue(validator.supports(form.getClass()));
   }

   public void testTypeCheckFail() {
      assertFalse(validator.supports(Object.class));
   }

   public void testValidateNoteContentOk() {
      // Setup test data
      final Calendar now = Calendar.getInstance();
      final Calendar later = Calendar.getInstance();
      later.add(Calendar.HOUR,1);
      form.setNote("note");
      form.setStartTime(now);
      form.setEndTime(later);
      
      // Carry out validation
      validator.validate(form, errors);
      
      // Check results
      assertEquals(0,errors.getErrorCount());
   }

   public void testValidateNoteContentNull() {
      // Setup test data
      final Calendar now = Calendar.getInstance();
      final Calendar later = Calendar.getInstance();
      later.add(Calendar.HOUR,1);
      form.setNote(null);
      form.setStartTime(now);
      form.setEndTime(later);
      
      // Carry out validation
      validator.validate(form, errors);
      
      // Check results
      assertEquals(1,errors.getErrorCount());
      final FieldError error = errors.getFieldError("note");
      assertNotNull(error);
      assertEquals("create.period.note",error.getCode());
   }

   public void testValidateNoteContentEmpty() {
      // Setup test data
      final Calendar now = Calendar.getInstance();
      final Calendar later = Calendar.getInstance();
      later.add(Calendar.HOUR,1);
      form.setNote("   "); // Whitespace only
      form.setStartTime(now);
      form.setEndTime(later);
      
      // Carry out validation
      validator.validate(form, errors);
      
      // Check results
      assertEquals(1,errors.getErrorCount());
      final FieldError error = errors.getFieldError("note");
      assertNotNull(error);
      assertEquals("create.period.note",error.getCode());
   }

   public void testValidateTimeImpossible() {
      // Setup test data
      final Calendar now = Calendar.getInstance();
      final Calendar later = Calendar.getInstance();
      later.add(Calendar.HOUR,1);
      form.setNote("note");
      form.setStartTime(later); // Times reversed
      form.setEndTime(now);
      
      // Carry out validation
      validator.validate(form, errors);
      
      // Check results
      assertEquals(2,errors.getErrorCount());
      final FieldError startError = errors.getFieldError("startTime");
      final FieldError endError = errors.getFieldError("endTime");
      assertNotNull(startError);
      assertNotNull(endError);
      assertEquals("create.period.startTime.swapped",startError.getCode());
      assertEquals("create.period.endTime.swapped",endError.getCode());
   }
}
