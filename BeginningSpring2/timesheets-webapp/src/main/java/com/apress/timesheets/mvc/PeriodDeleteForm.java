package com.apress.timesheets.mvc;

public class PeriodDeleteForm {
   private Long id;
   private Long timesheetId;

   public Long getTimesheetId() {
      return timesheetId;
   }

   public void setTimesheetId(Long timesheetId) {
      this.timesheetId = timesheetId;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }
}
