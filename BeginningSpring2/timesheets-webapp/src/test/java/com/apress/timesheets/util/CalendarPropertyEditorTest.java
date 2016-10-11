package com.apress.timesheets.util;

import java.beans.PropertyEditor;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import junit.framework.TestCase;

public class CalendarPropertyEditorTest extends TestCase {
   public void testParseCalender() {
      final String text = "08/11/2007 21:08";
      final PropertyEditor editor = new CalendarPropertyEditor(new SimpleDateFormat("MM/dd/yyyy HH:mm"));
      editor.setAsText(text);
      final Calendar cal = (Calendar)editor.getValue();
      assertEquals(2007,cal.get(Calendar.YEAR));
      assertEquals(Calendar.AUGUST,cal.get(Calendar.MONTH));
      assertEquals(11,cal.get(Calendar.DAY_OF_MONTH));
      assertEquals(21,cal.get(Calendar.HOUR_OF_DAY));
      assertEquals(8,cal.get(Calendar.MINUTE));
   }
}
