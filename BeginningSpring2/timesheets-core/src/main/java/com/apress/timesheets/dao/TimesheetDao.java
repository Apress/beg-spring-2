package com.apress.timesheets.dao;

import java.util.List;

import com.apress.timesheets.entity.Period;
import com.apress.timesheets.entity.RateType;
import com.apress.timesheets.entity.Timesheet;
import com.apress.timesheets.entity.UserAccount;

public interface TimesheetDao {
   void persist(Timesheet timesheet);
   void update(Timesheet timesheet);
   void delete(Timesheet timesheet);
   List<Timesheet> list(UserAccount account);
   Timesheet find(Long id);
   
   List<RateType> getRateTypeList();
   void populateDatabase();
   Period findPeriod(Long id);
   
   void persist(Period period);
   RateType getRateType(String rateTypeId);
   void delete(Period period);   
}
