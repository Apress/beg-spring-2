package com.apress.timesheets.mvc;

import java.math.BigDecimal;
import java.util.Calendar;

public class PeriodCreateForm {
   private Long timesheetId;
   private Calendar startTime = Calendar.getInstance();
   private Calendar endTime = Calendar.getInstance();
   private String note;
   private String rateTypeId;
   private BigDecimal rate = new BigDecimal(0);

   public Calendar getEndTime() {
      return endTime;
   }
   
   public void setEndTime(Calendar endTime) {
      this.endTime = endTime;
   }
   
   public String getNote() {
      return note;
   }
   
   public void setNote(String note) {
      this.note = note;
   }
   
   public BigDecimal getRate() {
      return rate;
   }
   
   public void setRate(BigDecimal rate) {
      this.rate = rate;
   }
   
   public Calendar getStartTime() {
      return startTime;
   }
   
   public void setStartTime(Calendar startTime) {
      this.startTime = startTime;
   }
   
   public String getRateTypeId() {
      return rateTypeId;
   }
   
   public void setRateTypeId(String rateTypeId) {
      this.rateTypeId = rateTypeId;
   }

   public Long getTimesheetId() {
      return timesheetId;
   }

   public void setTimesheetId(Long timesheetId) {
      this.timesheetId = timesheetId;
   }
}