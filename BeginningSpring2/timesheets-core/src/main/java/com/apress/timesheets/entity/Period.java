package com.apress.timesheets.entity;

import java.util.Calendar;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Period {
   private Long id;
   private Calendar startTime;
   private Calendar endTime;
   private String note;
   private Rate rate;
   

   public Period() {
   }

   public Period(final Calendar startTime, final Calendar endTime,
            final Rate rate) {
      this.startTime = startTime;
      this.endTime = endTime;
      this.rate = rate;
   }

   @Id
   @GeneratedValue
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public Calendar getEndTime() {
      return endTime;
   }

   public void setEndTime(Calendar endTime) {
      this.endTime = endTime;
   }

   public Calendar getStartTime() {
      return startTime;
   }

   public void setStartTime(Calendar startTime) {
      this.startTime = startTime;
   }

   public String getNote() {
      return note;
   }

   public void setNote(String note) {
      this.note = note;
   }

   @Embedded
   public Rate getRate() {
      return rate;
   }

   public void setRate(Rate rate) {
      this.rate = rate;
   }
   
   public int hashCode() {
      return (id != null) ? id.intValue() : 0; 
   }
   
   public boolean equals(Object obj) {
      return (obj instanceof Period) && ((Period)obj).id.equals(id);
   }
}