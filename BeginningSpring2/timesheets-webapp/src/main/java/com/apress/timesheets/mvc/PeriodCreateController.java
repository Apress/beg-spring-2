package com.apress.timesheets.mvc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.CancellableFormController;

import com.apress.timesheets.entity.RateType;
import com.apress.timesheets.entity.Timesheet;
import com.apress.timesheets.service.TimesheetService;
import com.apress.timesheets.util.CalendarPropertyEditor;

public class PeriodCreateController extends CancellableFormController {
   private TimesheetService timesheetService;

   @Override
   protected ModelAndView onSubmit(final Object command, final BindException errors) throws Exception {
      final PeriodCreateForm form = (PeriodCreateForm)command;      
      final Timesheet timesheet = timesheetService.findTimesheet(form.getTimesheetId());
      timesheetService.createPeriod(timesheet,form.getStartTime(),form.getEndTime(),form.getNote(),form.getRate(),form.getRateTypeId());
      final Map<String,Object> referenceData = new HashMap<String,Object>();
      referenceData.put("timesheet", timesheet);
      return new ModelAndView(getSuccessView(),referenceData);
   }

   @Override
   protected Map referenceData(final HttpServletRequest request, final Object command, final Errors errors) throws Exception {
      final PeriodCreateForm form = (PeriodCreateForm)command;
      final Timesheet timesheet = timesheetService.findTimesheet(form.getTimesheetId());
      final List<RateType> rateTypeList = timesheetService.getRateTypeList();
      form.setRateTypeId(rateTypeList.get(0).getId()); // NB: Demands populated rate type list
      final Map<String,Object> referenceData = new HashMap<String,Object>();
      referenceData.put("timesheet", timesheet);
      referenceData.put("rateTypeList", rateTypeList);
      return referenceData;
   }
   
   @Override
   protected void initBinder(final HttpServletRequest request,final ServletRequestDataBinder binder) throws Exception {
      binder.registerCustomEditor(Calendar.class,new CalendarPropertyEditor(new SimpleDateFormat("MM/dd/yyyy HH:mm")));
   }

   @Required
   public void setTimesheetService(TimesheetService timesheetService) {
      this.timesheetService = timesheetService;
   }
}
