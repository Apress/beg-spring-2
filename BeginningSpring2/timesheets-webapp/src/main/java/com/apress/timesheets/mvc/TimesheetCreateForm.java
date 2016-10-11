package com.apress.timesheets.mvc;

import java.util.Calendar;

public class TimesheetCreateForm {
   private Long id;
   private Calendar startDate = Calendar.getInstance();
   private String note;
   
   public String getNote() {
      return note;
   }
   
   public void setNote(String note) {
      this.note = note;
   }
   
   public Calendar getStartDate() {
      return startDate;
   }
   
   public void setStartDate(Calendar startDate) {
      this.startDate = startDate;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }
}
