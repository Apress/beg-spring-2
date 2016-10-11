package com.apress.timesheets.mvc;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.CancellableFormController;

import com.apress.timesheets.entity.Timesheet;
import com.apress.timesheets.entity.UserAccount;
import com.apress.timesheets.service.TimesheetService;
import com.apress.timesheets.util.CalendarPropertyEditor;
import com.apress.timesheets.util.PrincipalHelper;

public class TimesheetCreateController extends CancellableFormController {
   private TimesheetService timesheetService;

   @Override
   protected ModelAndView onSubmit(final Object command) throws Exception {
      final TimesheetCreateForm form = (TimesheetCreateForm)command;
      final UserAccount account = PrincipalHelper.getUser();
      final Timesheet timesheet = new Timesheet(account,form.getStartDate());
      timesheet.setNote(form.getNote());
      timesheetService.createTimesheet(timesheet);
      return new ModelAndView(getSuccessView());
   }

   @Override
   protected void initBinder(final HttpServletRequest request,final ServletRequestDataBinder binder) throws Exception {
      binder.registerCustomEditor(Calendar.class,new CalendarPropertyEditor(new SimpleDateFormat("MM/dd/yyyy")));
   }

   @Required
   public void setTimesheetService(TimesheetService timesheetService) {
      this.timesheetService = timesheetService;
   }
}
