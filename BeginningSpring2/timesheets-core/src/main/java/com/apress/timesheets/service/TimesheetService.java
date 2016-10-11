package com.apress.timesheets.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import com.apress.timesheets.entity.Period;
import com.apress.timesheets.entity.RateType;
import com.apress.timesheets.entity.Timesheet;
import com.apress.timesheets.entity.UserAccount;

public interface TimesheetService {
   List<Timesheet> listTimesheets(UserAccount account);
   Timesheet findTimesheet(Long id);
   void createTimesheet(Timesheet timesheet);
   void updateTimesheet(Timesheet timesheet);
   void deleteTimesheet(Timesheet timesheet);
   List<RateType> getRateTypeList();
   Period findPeriod(Long id);
   Period createPeriod(Timesheet timesheet,Calendar startTime,Calendar endTime,String note,BigDecimal rate,String rateId);
   void deletePeriod(Timesheet timesheet,Period period);
}
