package com.apress.timesheets.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.apress.timesheets.entity.Timesheet;
import com.apress.timesheets.service.TimesheetService;

public class TimesheetViewController extends SimpleFormController {
   private TimesheetService timesheetService;

   @Override
   protected Map referenceData(final HttpServletRequest request, final Object command, final Errors errors) throws Exception {
      final TimesheetViewForm form = (TimesheetViewForm)command;      
      final Timesheet timesheet = timesheetService.findTimesheet(form.getId());
      final Map<String,Object> referenceData = new HashMap<String,Object>();
      referenceData.put("timesheet", timesheet);
      return referenceData;
   }

   @Required
   public void setTimesheetService(TimesheetService timesheetService) {
      this.timesheetService = timesheetService;
   }
}
