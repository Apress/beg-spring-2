package com.apress.timesheets.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import com.apress.timesheets.dao.EmailDao;
import com.apress.timesheets.dao.TimesheetDao;
import com.apress.timesheets.entity.Period;
import com.apress.timesheets.entity.Rate;
import com.apress.timesheets.entity.RateType;
import com.apress.timesheets.entity.Timesheet;
import com.apress.timesheets.entity.UserAccount;

@Transactional
public class TimesheetServiceImpl implements TimesheetService {
   private TimesheetDao timesheetDao;
   private EmailDao emailDao;

   public void createTimesheet(final Timesheet timesheet) {
      timesheetDao.persist(timesheet);
   }

   public List<Timesheet> listTimesheets(final UserAccount account) {
      return timesheetDao.list(account);
   }
   
   public Timesheet findTimesheet(Long id) {
      return timesheetDao.find(id);
   }

   public void updateTimesheet(final Timesheet timesheet) {
      timesheetDao.update(timesheet);
      emailDao.sendTimesheetUpdate(timesheet);
   }

   public void deleteTimesheet(final Timesheet timesheet) {
      timesheetDao.delete(timesheet);
   }
   
   public List<RateType> getRateTypeList() {
      return timesheetDao.getRateTypeList();
   }
   
   public Period findPeriod(final Long id) {
      return timesheetDao.findPeriod(id);
   }

   public Period createPeriod(Timesheet timesheet, Calendar startTime, Calendar endTime, String note, BigDecimal rate, String rateId) {
    final RateType rateType = timesheetDao.getRateType(rateId);
    final Period period = new Period(startTime,endTime,new Rate(rateType,rate));
    period.setNote(note);
    timesheet.getPeriods().add(period);
    timesheetDao.persist(period);
    timesheetDao.update(timesheet);
    return period;
   }

   public void deletePeriod(final Timesheet timesheet, final Period period) {
      timesheet.getPeriods().remove(period);
      timesheetDao.update(timesheet);
      timesheetDao.delete(period);
   }

   @Required
   public void setEmailDao(EmailDao emailDao) {
      this.emailDao = emailDao;
   }

   @Required
   public void setTimesheetDao(final TimesheetDao timesheetDao) {
      this.timesheetDao = timesheetDao;
   }
}
