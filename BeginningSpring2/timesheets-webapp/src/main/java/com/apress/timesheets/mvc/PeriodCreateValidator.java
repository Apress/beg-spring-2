package com.apress.timesheets.mvc;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class PeriodCreateValidator implements Validator {
   public boolean supports(final Class type) {
      return PeriodCreateForm.class.equals(type);
   }

   public void validate(final Object command, final Errors errors) {
      final PeriodCreateForm form = (PeriodCreateForm)command;
      if(form.getNote() == null || "".equals(form.getNote().trim())) {
         errors.rejectValue("note", "create.period.note");
      }
      
      if(!form.getStartTime().before(form.getEndTime())) {
         errors.rejectValue("startTime", "create.period.startTime.swapped");
         errors.rejectValue("endTime", "create.period.endTime.swapped");
      }
   }
}
