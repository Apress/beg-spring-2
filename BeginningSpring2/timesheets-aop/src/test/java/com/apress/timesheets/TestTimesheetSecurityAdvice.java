package com.apress.timesheets;

import junit.framework.TestCase;

import org.acegisecurity.context.SecurityContext;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.log4j.Logger;

import com.apress.timesheets.entity.Timesheet;
import com.apress.timesheets.entity.UserAccount;
import com.apress.timesheets.entity.UserRole;
import com.apress.timesheets.security.AcegiUserDetails;

public class TestTimesheetSecurityAdvice extends TestCase {
	private static final Logger log = Logger.getLogger(TestTimesheetSecurityAdvice.class);
	private final TimesheetSecurityAdvice advice = new TimesheetSecurityAdvice(); 
	private MockAuthentication auth;
	
	@Override
	protected void setUp() throws Exception {
		final UserAccount account = new UserAccount("current");
		account.setId(0L);
		final AcegiUserDetails acegi = new AcegiUserDetails(account,"");
		this.auth = new MockAuthentication(acegi);
		final SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(null);		
	}

	public void testFindTimesheetPassMatch() {
		// Create a valid authentication context
		final SecurityContext sc = SecurityContextHolder.getContext();
		auth.setAuthenticated(true);
		sc.setAuthentication(auth);
		// Make the timesheet owner the logged-in user
		final UserAccount owner = new UserAccount("current");
		owner.setId(0L);
		final Timesheet timesheet = new Timesheet(owner,null);
		try {
			// Execute the method (i.e. do the test)
			advice.findTimesheet(timesheet);
		} catch(final TimesheetSecurityException e ) {
			fail("Failed authentication");
		}
	}

	public void testFindTimesheetPassAdmin() {
		// Create a valid authentication context
		final SecurityContext sc = SecurityContextHolder.getContext();
		auth.setAuthenticated(true);
		sc.setAuthentication(auth);
		// Make the logged-in user an administrator
		final AcegiUserDetails acegi = (AcegiUserDetails)auth.getPrincipal();
		acegi.getUserAccount().getRoles().add(new UserRole("ROLE_ADMINISTRATOR"));
		final UserAccount owner = new UserAccount("other");
		owner.setId(1L);
		final Timesheet timesheet = new Timesheet(owner,null);
		try {
			// Execute the method (i.e. do the test)
			advice.findTimesheet(timesheet);
		} catch(final TimesheetSecurityException e ) {
			fail("Failed authentication");
		}
	}

	public void testFindTimesheetFail() {
		// Create a valid authentication context
		final SecurityContext sc = SecurityContextHolder.getContext();
		auth.setAuthenticated(true);
		sc.setAuthentication(auth);
		// Make the timesheet owner some other
		final UserAccount owner = new UserAccount("other");
		owner.setId(1L);
		final Timesheet timesheet = new Timesheet(owner,null);
		try {
			// Execute the method (i.e. do the test)
			advice.findTimesheet(timesheet);
			fail("Passed authentication for invalid user");
		} catch(final TimesheetSecurityException e ) {
			log.info("Exception thrown as required");
		}
	}

	public void testFindTimesheetNoAuthentication() {
		// Remove the logged-in user
		final SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(null);
		// Make the timesheet owner some other user
		final UserAccount owner = new UserAccount("current");
		owner.setId(0L);
		final Timesheet timesheet = new Timesheet(owner,null);
		try {
			// Execute the method (i.e. do the test)
			advice.findTimesheet(timesheet);
			fail("Passed authentication for invalid user");
		} catch(final TimesheetSecurityException e ) {
			log.info("Exception thrown as required");
		}
	}
	
	public void testFindTimesheetNotAuthenticated() {
		// Create an invalid valid authentication context
		final SecurityContext sc = SecurityContextHolder.getContext();
		auth.setAuthenticated(false);
		sc.setAuthentication(auth);
		// Set the user to match the "current" user (current but failed
		// authentication
		final UserAccount owner = new UserAccount("current");
		owner.setId(0L);
		final Timesheet timesheet = new Timesheet(owner,null);
		try {
			// Execute the method (i.e. do the test)
			advice.findTimesheet(timesheet);
			fail("Passed authentication for invalid user");
		} catch(final TimesheetSecurityException e ) {
			log.info("Exception thrown as required");
		}
	}
	
	public void testListPassMatch() {
		// Create a valid authentication context
		final SecurityContext sc = SecurityContextHolder.getContext();
		auth.setAuthenticated(true);
		sc.setAuthentication(auth);
		// The user whose timesheets we're requesting
		final UserAccount owner = new UserAccount("current");
		owner.setId(0L);
		try {
			// Execute the method (i.e. do the test)
			advice.list(owner);
		} catch(final TimesheetSecurityException e ) {
			fail("Failed authentication");
		}
	}

	public void testListPassAdmin() {
		// Create a valid authentication context
		final SecurityContext sc = SecurityContextHolder.getContext();
		auth.setAuthenticated(true);
		sc.setAuthentication(auth);
		// Make the logged-in user an administrator
		final AcegiUserDetails acegi = (AcegiUserDetails)auth.getPrincipal();
		acegi.getUserAccount().getRoles().add(new UserRole("ROLE_ADMINISTRATOR"));
		// A different user whose timesheets we're requesting
		final UserAccount owner = new UserAccount("other");
		owner.setId(1L);
		
		try {
			// Execute the method (i.e. do the test)
			advice.list(owner);
		} catch(final TimesheetSecurityException e ) {
			fail("Failed authentication");
		}
	}
	
	public void testListFail() {
		// Create a valid authentication context
		final SecurityContext sc = SecurityContextHolder.getContext();
		auth.setAuthenticated(true);
		sc.setAuthentication(auth);
		// A different user whose timesheets we're requesting
		final UserAccount owner = new UserAccount("other");
		owner.setId(1L);
		try {
			// Execute the method (i.e. do the test)
			advice.list(owner);
			fail("Passed authentication for invalid user");
		} catch(final TimesheetSecurityException e ) {
			log.info("Exception thrown as required");
		}
	}
	
	public void testListNoAuthentication() {
		// Remove the logged-in user
		final SecurityContext sc = SecurityContextHolder.getContext();
		sc.setAuthentication(null);
		// Make the timesheets' owner some other user
		final UserAccount owner = new UserAccount("current");
		owner.setId(0L);
		try {
			// Execute the method (i.e. do the test)
			advice.list(owner);
			fail("Passed authentication for invalid user");
		} catch(final TimesheetSecurityException e ) {
			log.info("Exception thrown as required");
		}
	}
	
	public void testListNotAuthenticated() {
		// Create an invalid valid authentication context
		final SecurityContext sc = SecurityContextHolder.getContext();
		auth.setAuthenticated(false);
		sc.setAuthentication(auth);
		// Set the user to match the "current" user (current but failed
		// authentication)
		final UserAccount owner = new UserAccount("current");
		owner.setId(0L);
		try {
			// Execute the method (i.e. do the test)
			advice.list(owner);
			fail("Passed authentication for invalid user");
		} catch(final TimesheetSecurityException e ) {
			log.info("Exception thrown as required");
		}
	}
}
