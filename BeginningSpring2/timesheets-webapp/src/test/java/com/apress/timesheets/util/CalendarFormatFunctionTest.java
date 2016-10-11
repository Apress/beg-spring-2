package com.apress.timesheets.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import junit.framework.TestCase;

public class CalendarFormatFunctionTest extends TestCase {
   
   private Calendar cal;
   
   @Override
   protected void setUp() throws Exception {
      final DateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm");
      final Date date = format.parse("01/27/1972 13:45");
      cal = Calendar.getInstance();
      cal.setTime(date);
   }

   public void testFormat() {
      final String actual = CalendarFormatFunction.format(cal);
      assertEquals("01/27/1972",actual);
   }
   
   public void testFormatDate() {
      final String actual = CalendarFormatFunction.formatDate(cal);
      assertEquals("01/27/1972",actual);
   }
   
   public void testFormatTime() {
      final String actual = CalendarFormatFunction.formatTime(cal);
      assertEquals("13:45",actual);
   }
   
   public void testFormatDateTime() {
      final String actual = CalendarFormatFunction.formatDateTime(cal);
      assertEquals("01/27/1972 13:45",actual);      
   }
}
