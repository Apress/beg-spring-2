package com.apress.timesheets.service;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.apress.timesheets.dao.SecurityDao;
import com.apress.timesheets.dao.UserAccountDao;
import com.apress.timesheets.entity.UserAccount;
import com.apress.timesheets.security.AcegiUserDetails;

public class UserAccountServiceTest extends TestCase {
   private UserAccountService service;
   private SecurityDao mockSecurityDao;
   private UserAccountDao mockUserAccountDao;

   @Override
   protected void setUp() throws Exception {
      final UserAccountServiceImpl impl = new UserAccountServiceImpl();
      mockSecurityDao = createMock(SecurityDao.class);
      mockUserAccountDao = createMock(UserAccountDao.class);
      impl.setSecurityDao(mockSecurityDao);
      impl.setUserAccountDao(mockUserAccountDao);
      impl.setSecurityDao(mockSecurityDao);
      impl.setUserAccountDao(mockUserAccountDao);
      this.service = impl;
   }

   public void testFindUser() {
      // Prepare test data
      final String username = "test";
      final UserAccount account = new UserAccount(username);

      // Script the mock object's expectations
      expect(mockUserAccountDao.read(username)).andReturn(account);
      replay(mockUserAccountDao);

      // Run the test. The mock object will fail the test if its
      // script is not followed.
      final UserAccount actualAccount = service.findUser(username);
      verify(mockUserAccountDao);
      assertTrue(actualAccount == account);
   }

   public void testCreateUser() {
      // Prepare test data
      final String username = "test";
      final UserAccount account = new UserAccount(username);
      final AcegiUserDetails acegi = new AcegiUserDetails(account,"password");
      
      // Script the test
      expect(mockSecurityDao.createUser(account)).andReturn(acegi);
      replay(mockSecurityDao);

      // Run the test
      service.createUser(account);
      verify(mockSecurityDao);
   }
      
   public void testDeleteUser() {
      final String username = "test";
      final UserAccount account = new UserAccount(username);
      
      mockSecurityDao.deleteUser(account);
      replay(mockSecurityDao);
      
      service.deleteUser(account);
      verify(mockSecurityDao);
   }
   
   public void testUpdateUser() {
      final String username = "test";
      final UserAccount account = new UserAccount(username);

      mockUserAccountDao.update(account);
      replay(mockUserAccountDao);
      
      service.updateUser(account);
      verify(mockUserAccountDao);
   }
      
   public void testListUsers() {
      final String username = "test";
      final UserAccount account = new UserAccount(username);
      final List<UserAccount> accounts = new ArrayList<UserAccount>();
      accounts.add(account);
      expect(mockUserAccountDao.list()).andReturn(accounts);
      replay(mockUserAccountDao);
      
      final List<UserAccount> actual = service.listUsers();
      verify(mockUserAccountDao);
      assertNotNull(actual);
      assertEquals(1,actual.size());
      assertEquals(account,actual.get(0));
   }      
}
