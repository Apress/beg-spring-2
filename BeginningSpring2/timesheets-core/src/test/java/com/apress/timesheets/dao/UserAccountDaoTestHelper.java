package com.apress.timesheets.dao;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.apress.timesheets.entity.UserAccount;
import com.apress.timesheets.entity.UserRole;

public abstract class UserAccountDaoTestHelper 
   extends TestCase 
{
   
   private UserAccountDao dao;   
   private ConfigHelper helper;

   public void testSaveUserRoles() {
      final UserAccount account = new UserAccount("testuser3");
      final UserRole admin = new UserRole("admin");

      account.getRoles().add(admin);
      dao.create(account);

      helper.clear();

      assertNotNull(account.getId());
      assertNotNull(admin.getId());

      final UserAccount retrievedAccount = dao.read(account.getId());
      assertNotNull(retrievedAccount);
      assertNotNull(retrievedAccount.getRoles());
      assertEquals(1, retrievedAccount.getRoles().size());

      final UserRole retrievedRole = 
         retrievedAccount.getRoles().iterator().next();
      assertEquals(admin.getId(), retrievedRole.getId());
   }
   
   // READ, UPDATE, DELETE
   public void testReadById() {
      final UserAccount account = new UserAccount("testuser4");
      dao.create(account);
      helper.clear();

      assertNotNull(account.getId());
      
      final UserAccount retrieved = dao.read(account.getId());
      assertNotNull(retrieved);
      assertEquals(account.getId(), retrieved.getId());
      assertEquals(account.getAccountName(), retrieved.getAccountName());
   }
   
   public void testReadByName() {
      final UserAccount account = new UserAccount("testuser5");
      dao.create(account);
      helper.clear();

      assertNotNull(account.getId());
      
      final UserAccount retrieved = dao.read(account.getAccountName());
      assertNotNull(retrieved);
      assertEquals(account.getId(), retrieved.getId());
      assertEquals(account.getAccountName(), retrieved.getAccountName());
   }
   
   public void testList() {
      final List<Long> ids = new ArrayList<Long>();
      for(int i = 0; i < 10; i++) {
         final UserAccount account = new UserAccount("testuser6:"+i);
         dao.create(account);
         helper.clear();
         assertNotNull(account.getId());
         ids.add(account.getId());
      }
      
      final List<UserAccount> accounts = dao.list();
      assertNotNull(accounts);
      int index = 0;
      for( final UserAccount retrieved : accounts ) {
         final Long expected = ids.get(index++);
         System.out.println("E: "+expected);
         System.out.println("A: "+retrieved.getId() + retrieved.getAccountName());
         assertNotNull(retrieved);
         assertEquals(expected, retrieved.getId());
      }
   }
   
   public void testUpdate() {
      final UserAccount account = new UserAccount("testuser7");
      dao.create(account);
      helper.clear();
      assertNotNull(account.getId());
      
      final UserAccount update = new UserAccount();
      update.setId(account.getId());
      update.setAccountName("Updated");
      dao.update(update);
      helper.clear();
      
      final UserAccount retrieved = dao.read(account.getId());
      assertNotNull(retrieved);
      assertEquals(account.getId(),retrieved.getId());
      assertEquals(update.getAccountName(),retrieved.getAccountName());
   }
   
   public void testDeleteByAccount() {
      final UserAccount account = new UserAccount("testuser8");
      dao.create(account);
      helper.clear();
      assertNotNull(account.getId());
      
      dao.delete(account);
      helper.clear();
      
      final List<UserAccount> retrieved = dao.list();
      assertNotNull(retrieved);
      assertEquals(0,retrieved.size());
   }
   
   public void testDeleteById() {
      final UserAccount account = new UserAccount("testuser9");
      dao.create(account);
      helper.clear();
      assertNotNull(account.getId());
      
      dao.delete(account.getId());
      helper.clear();
      
      final List<UserAccount> retrieved = dao.list();
      assertNotNull(retrieved);
      assertEquals(0,retrieved.size());
   }

   public UserAccountDao getDao() {
      return dao;
   }

   public void setDao(UserAccountDao dao) {
      this.dao = dao;
   }

   public ConfigHelper getHelper() {
      return helper;
   }

   public void setHelper(ConfigHelper helper) {
      this.helper = helper;
   }
}
