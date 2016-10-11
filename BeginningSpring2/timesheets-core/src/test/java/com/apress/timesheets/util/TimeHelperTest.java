package com.apress.timesheets.util;

import java.util.Calendar;

import junit.framework.TestCase;

public class TimeHelperTest extends TestCase {
	public void testStartOfWeekMidyear() {
		Calendar cal = Calendar.getInstance();
		cal.set(2007,Calendar.JULY,10,0,0,0);
		cal = TimeHelper.startOfWeek(cal, Calendar.MONDAY);
		assertNotNull(cal);
		assertEquals(2007,cal.get(Calendar.YEAR));
		assertEquals(Calendar.JULY,cal.get(Calendar.MONTH));
		assertEquals(9,cal.get(Calendar.DAY_OF_MONTH));
		assertEquals(0,cal.get(Calendar.HOUR));
		assertEquals(0,cal.get(Calendar.MINUTE));
		assertEquals(0,cal.get(Calendar.SECOND));
	}
	
	public void testStartOfWeekJanuary() {
		Calendar cal = Calendar.getInstance();
		cal.set(2008,Calendar.JANUARY,1,0,0,0);
		cal = TimeHelper.startOfWeek(cal, Calendar.MONDAY);
		assertNotNull(cal);
		assertEquals(2007,cal.get(Calendar.YEAR));
		assertEquals(Calendar.DECEMBER,cal.get(Calendar.MONTH));
		assertEquals(31,cal.get(Calendar.DAY_OF_MONTH));
		assertEquals(0,cal.get(Calendar.HOUR));
		assertEquals(0,cal.get(Calendar.MINUTE));
		assertEquals(0,cal.get(Calendar.SECOND));

	}
}
