package com.apress.timesheets.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.CancellableFormController;

import com.apress.timesheets.entity.Timesheet;
import com.apress.timesheets.service.TimesheetService;

public class TimesheetUpdateController extends CancellableFormController {
   private TimesheetService timesheetService;
   
   @Override
   protected ModelAndView onSubmit(final HttpServletRequest request, final HttpServletResponse response, final Object command, final BindException errors) throws Exception {
      final TimesheetUpdateForm form = (TimesheetUpdateForm)command;
      final Timesheet timesheet = timesheetService.findTimesheet(form.getId());
      timesheet.setNote(form.getNote());
      timesheetService.updateTimesheet(timesheet);
      return new ModelAndView(getSuccessView(),referenceData(request,command,errors));
   }

   @Override
   protected Map referenceData(final HttpServletRequest request, final Object command, final Errors errors) throws Exception {
      final TimesheetUpdateForm form = (TimesheetUpdateForm)command;
      final Timesheet timesheet = timesheetService.findTimesheet(form.getId());
      form.setId(timesheet.getId());
      form.setNote(timesheet.getNote());
      final Map<String,Object> referenceData = new HashMap<String,Object>();
      referenceData.put("timesheet", timesheet);
      return referenceData;
   }

   @Required
   public void setTimesheetService(TimesheetService timesheetService) {
      this.timesheetService = timesheetService;
   }
}
