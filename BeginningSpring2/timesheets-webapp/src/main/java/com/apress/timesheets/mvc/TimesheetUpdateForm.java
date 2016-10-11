package com.apress.timesheets.mvc;

public class TimesheetUpdateForm {
   private Long id;
   private String note;
   
   public Long getId() {
      return id;
   }
   
   public void setId(Long id) {
      this.id = id;
   }
   
   public String getNote() {
      return note;
   }
   
   public void setNote(String note) {
      this.note = note;
   }
}
