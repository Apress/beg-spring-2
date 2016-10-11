package com.apress.timesheets.util;

import java.util.Calendar;

public final class TimeHelper {
   public static void midnight(final Calendar cal) {
      cal.set(Calendar.HOUR, 0);
      cal.set(Calendar.HOUR_OF_DAY, 0);
      cal.set(Calendar.MINUTE, 0);
      cal.set(Calendar.SECOND, 0);
      cal.set(Calendar.MILLISECOND, 0);
      cal.set(Calendar.AM_PM, Calendar.AM);
   }
   
   /**
    * This helper method returns a Calendar initialized to the first
    * day of the week relative to the date passed in (with the day of
    * the week in question specified by the day parameter).
    * 
    * For example, startOfWeek passed a Calendar representing 2007-07-10
    * and Calendar.MONDAY will return a Calendar representing 2007-07-09.
    * 
    * The method will correctly handle weeks straddling month and year
    * boundaries.
    * 
    * @param cal
    * @param day
    * @return
    */
   public static Calendar startOfWeek(final Calendar cal, final int day) {
	   final Calendar target = (Calendar)cal.clone();
	   final int currentDay = target.get(Calendar.DAY_OF_WEEK);
	   final int delta = day - currentDay;
	   target.add(Calendar.DATE, delta);
	   return target;
   }
}
