package com.apress.timesheets.dao.hibernate;

import com.apress.timesheets.dao.UserAccountDaoTestHelper;

public class HibernateUserAccountDaoTest extends UserAccountDaoTestHelper {
   @Override
   protected void setUp() throws Exception {
      final HibernateConfigHelper helper = new HibernateConfigHelper();
      helper.setUp();
      setHelper(helper);
      
      final HibernateUserAccountDaoImpl dao = new HibernateUserAccountDaoImpl();
      dao.setHibernateTemplate(helper.getTemplate());
      
      setDao(dao);
   }
}
