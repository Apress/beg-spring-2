package com.apress.timesheets.dao;

import com.apress.timesheets.entity.Timesheet;

public interface EmailDao {
   void sendTimesheetUpdate(Timesheet timesheet);
}
