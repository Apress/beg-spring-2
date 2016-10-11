package com.apress.timesheets.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarFormatFunction {
   public static String format(final Calendar cal) {
      final DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
      return format.format(cal.getTime());
   }

   public static String formatDate(final Calendar cal) {
      final DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
      return format.format(cal.getTime());
   }
   
   public static String formatTime(final Calendar cal) {
      final DateFormat format = new SimpleDateFormat("HH:mm");
      return format.format(cal.getTime());
   }

   public static String formatDateTime(final Calendar cal) {
      final DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
      return format.format(cal.getTime());
   }
}
