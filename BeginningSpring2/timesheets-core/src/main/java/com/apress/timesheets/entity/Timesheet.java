package com.apress.timesheets.entity;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.CascadeType.ALL;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
   @NamedQuery(name="getTimesheetsByUser",query="from Timesheet where consultant = :consultant order by startDate desc")
})
public class Timesheet implements Serializable {
   private static final long serialVersionUID = 0L;
   private Long id;
   private UserAccount consultant;
   private Calendar created = Calendar.getInstance();
   private Calendar startDate;
   private String note;
   private List<Period> periods = new ArrayList<Period>();

   public Timesheet() {
   }

   public Timesheet(final UserAccount account, final Calendar startDate) {
      this.consultant = account;
      this.startDate = startDate;
   }

   @Id
   @GeneratedValue
   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   @ManyToOne(optional = false)
   @JoinColumn(name = "useraccount")
   public UserAccount getConsultant() {
      return consultant;
   }

   public void setConsultant(UserAccount account) {
      this.consultant = account;
   }

   public Calendar getCreated() {
      return created;
   }

   public void setCreated(Calendar created) {
      this.created = created;
   }

   public Calendar getStartDate() {
      return startDate;
   }

   public void setStartDate(Calendar startDate) {
      this.startDate = startDate;
   }

   public String getNote() {
      return note;
   }

   public void setNote(String note) {
      this.note = note;
   }

   @OneToMany(fetch = LAZY, cascade = ALL)
   public List<Period> getPeriods() {
      return periods;
   }

   public void setPeriods(List<Period> periods) {
      this.periods = periods;
   }
}