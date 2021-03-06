package com.apress.timesheets.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.CancellableFormController;

import com.apress.timesheets.entity.Period;
import com.apress.timesheets.entity.Timesheet;
import com.apress.timesheets.service.TimesheetService;

public class PeriodDeleteController extends CancellableFormController {
   private TimesheetService timesheetService;
   
   @Override
   protected ModelAndView onSubmit(final Object command, final BindException errors) throws Exception {
      final PeriodDeleteForm form = (PeriodDeleteForm)command;      
      final Timesheet timesheet = timesheetService.findTimesheet(form.getTimesheetId());
      final Period period = timesheetService.findPeriod(form.getId());
      timesheetService.deletePeriod(timesheet,period);
      return new ModelAndView(getSuccessView());
   }

   @Override
   protected Map referenceData(final HttpServletRequest request, final Object command, final Errors errors) throws Exception {
      final PeriodDeleteForm form = (PeriodDeleteForm)command;      
      final Timesheet timesheet = timesheetService.findTimesheet(form.getTimesheetId());
      final Period period = timesheetService.findPeriod(form.getId());
      final Map<String,Object> referenceData = new HashMap<String,Object>();
      referenceData.put("timesheet", timesheet);
      referenceData.put("period", period);
      return referenceData;
   }

   @Required
   public void setTimesheetService(TimesheetService timesheetService) {
      this.timesheetService = timesheetService;
   }
}
