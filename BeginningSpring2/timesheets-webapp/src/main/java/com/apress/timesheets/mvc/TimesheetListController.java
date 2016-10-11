package com.apress.timesheets.mvc;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.apress.timesheets.entity.UserAccount;
import com.apress.timesheets.service.TimesheetService;
import com.apress.timesheets.util.PrincipalHelper;

public class TimesheetListController extends SimpleFormController {
   private TimesheetService timesheetService;
   
   @Override
   protected Map referenceData(final HttpServletRequest request) throws Exception {
      final UserAccount account = PrincipalHelper.getUser();
      final Map<String,Object> referenceData = new HashMap<String,Object>();
      referenceData.put("timesheets",timesheetService.listTimesheets(account));
      return referenceData;
   }
   
   @Required
   public void setTimesheetService(TimesheetService timesheetService) {
      this.timesheetService = timesheetService;
   }
}
