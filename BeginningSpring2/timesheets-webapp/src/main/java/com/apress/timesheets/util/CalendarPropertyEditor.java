package com.apress.timesheets.util;

import java.beans.PropertyEditorSupport;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class CalendarPropertyEditor extends PropertyEditorSupport {
   private static final Logger log = Logger.getLogger(CalendarPropertyEditor.class);
   private DateFormat format;
   
   public CalendarPropertyEditor(final DateFormat format) {
      this.format = format;
   }
   
   @Override
   public String getAsText() {
      final Calendar value = (Calendar)getValue();
      return format.format(value.getTime());
   }

   @Override
   public void setAsText(final String text) throws IllegalArgumentException {
      try {
         final Calendar cal = Calendar.getInstance();
         final Date date = format.parse(text);
         log.info("Unparsed date: " + text);
         log.info("Parsed date: " + date.toString());
         cal.setTime(date);
         setValue(cal);
      } catch( final ParseException e ) {
         throw new IllegalArgumentException("Calendar value has invalid format ("+text+")",e);
      }
   }
}
