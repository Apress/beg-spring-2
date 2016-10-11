package com.apress.timesheets.dao.hibernate;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.apress.timesheets.dao.TimesheetDao;
import com.apress.timesheets.entity.Period;
import com.apress.timesheets.entity.RateType;
import com.apress.timesheets.entity.Timesheet;
import com.apress.timesheets.entity.UserAccount;

public class HibernateTimesheetDaoImpl extends HibernateDaoSupport implements
         TimesheetDao {
   public void persist(final Timesheet timesheet) {
      getHibernateTemplate().persist(timesheet);
   }

   public void update(final Timesheet timesheet) {
      getHibernateTemplate().update(timesheet);
   }

   @SuppressWarnings("unchecked")
   public List<Timesheet> list(final UserAccount account) {
      return getHibernateTemplate()
         .findByNamedQueryAndNamedParam("getTimesheetsByUser", "consultant", account);
   }
   
   public Timesheet find(final Long id) {
      return (Timesheet)getHibernateTemplate().get(Timesheet.class, id);
   }

   public void delete(final Timesheet timesheet) {
      getHibernateTemplate().delete(timesheet);
   }

   public void populateDatabase() {
      if( getRateTypeList().size() == 0 ) {
         getHibernateTemplate().persist(new RateType("Standard"));
         getHibernateTemplate().persist(new RateType("Overtime"));
      }
   }

   @SuppressWarnings("unchecked")
   public List<RateType> getRateTypeList() {
      return (List<RateType>)getHibernateTemplate().findByNamedQuery("findRateTypesOrdered");
   }

   public Period findPeriod(final Long id) {
      return (Period)getHibernateTemplate().get(Period.class, id);
   }

   public RateType getRateType(final String rateTypeId) {
      return (RateType)getHibernateTemplate().get(RateType.class, rateTypeId);
   }

   public void persist(final Period period) {
      getHibernateTemplate().persist(period);
   }

   public void delete(final Period period) {
      getHibernateTemplate().delete(period);
   }
}
