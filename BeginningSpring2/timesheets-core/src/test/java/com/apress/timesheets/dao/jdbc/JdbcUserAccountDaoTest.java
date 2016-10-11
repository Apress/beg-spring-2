package com.apress.timesheets.dao.jdbc;

import com.apress.timesheets.dao.UserAccountDaoTestHelper;

public class JdbcUserAccountDaoTest extends UserAccountDaoTestHelper {
   public void setUp() throws Exception {
      final JdbcConfigHelper helper = new JdbcConfigHelper();
      helper.setUp();
      setHelper(helper);
      
      final JdbcUserAccountDaoImpl dao = new JdbcUserAccountDaoImpl();
      dao.setJdbcTemplate(helper.getTemplate());
      setDao(dao);
   }
}
