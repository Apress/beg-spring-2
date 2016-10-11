package com.apress.timesheets.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.jdbc.core.simple.ParameterizedRowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import com.apress.timesheets.dao.UserAccountDao;
import com.apress.timesheets.entity.UserAccount;
import com.apress.timesheets.entity.UserRole;

/**
 * Note that this implementation is database-specific.
 * 
 * @author Dave Minter
 */
public class JdbcUserAccountDaoImpl extends JdbcDaoSupport implements UserAccountDao {

   private static final Logger log = Logger.getAnonymousLogger();
   
   public static final String INSERT_ACCOUNT = "insert into UserAccount(accountName) values (?)";
   public static final String SELECT_LAST_ACCOUNT_ID = "select max(id) from UserAccount";
   public static final String INSERT_ROLE = "insert into UserRole(roleName) values (?)";
   public static final String SELECT_LAST_ROLE_ID = "select max(id) from UserRole";   
   public static final String INSERT_ACCOUNT_ROLE = "insert into account_role(user,role) values(?,?)";
   public static final String DELETE_ACCOUNT_ROLE = "delete from account_role where user = ? and role = ?";
   public static final String DELETE_BY_ID = "delete from UserAccount where id = ?";
   public static final String SELECT_ACCOUNT_BY_ID = "select id, accountName from UserAccount where id = :id";
   public static final String SELECT_ACCOUNT_ID_BY_NAME = "select id from UserAccount where accountName = :name";
   public static final String SELECT_ACCOUNT_EMAIL_BY_NAME = "select email from UserAccount where accountName = :name";   
   public static final String SELECT_ACCOUNTS = "select id, accountName from UserAccount order by accountName";
   public static final String SELECT_ROLES_BY_ACCOUNT = "select id, roleName from UserRole left join account_role on UserRole.id = account_role.role where user = ?";
   public static final String UPDATE_ACCOUNT = "update UserAccount set accountName = :name where id = :id";
   public static final String SELECT_USER_ROLE_ID_BY_NAME = "select id from UserRole where roleName = ?";

   final ParameterizedRowMapper<UserAccount> userMapper = 
         new ParameterizedRowMapper<UserAccount>() {
      public UserAccount mapRow(ResultSet rs, int rowNum) 
         throws SQLException
      {
         UserAccount account = new UserAccount();
         account.setId(rs.getLong("id"));
         account.setAccountName(rs.getString("accountName"));
         return account;
      }
   };

   final ParameterizedRowMapper<UserRole> roleMapper = 
      new ParameterizedRowMapper<UserRole>() {
      public UserRole mapRow(ResultSet rs, int rowNum) 
         throws SQLException
      {
         UserRole role = new UserRole();
         role.setId(rs.getLong("id"));
         role.setRoleName(rs.getString("roleName"));
         return role;
      }
   };

   private SimpleJdbcTemplate template;
   
   private SimpleJdbcTemplate getTemplate() {
      if( template == null ) {
         template = new SimpleJdbcTemplate(getJdbcTemplate());         
      }
      return this.template;
   }
   
   public void create(final UserAccount account) {
      // Make the account entity persistent
      getTemplate().update(INSERT_ACCOUNT, account.getAccountName());
      final Long accountId = getTemplate().queryForLong(SELECT_LAST_ACCOUNT_ID);
      account.setId(accountId);
      persistTransientRoles(account);
   }
   
   private void persistTransientRoles(final UserAccount account) {
      // Make any transient role entities persistent
      for( final UserRole role : account.getRoles()) {
         if( role.getId() == null ) {
            getTemplate().update(INSERT_ROLE, role.getRoleName());
            final Long roleId = getTemplate().queryForLong(SELECT_LAST_ROLE_ID);
            role.setId(roleId);
            getTemplate().update(INSERT_ACCOUNT_ROLE, account.getId(),roleId);
         }
      }      
   }

   public void delete(final UserAccount account) {
      delete(account.getId());
   }

   public void delete(final Long id) {
      getTemplate().update(DELETE_BY_ID, id);
   }
   
   public void traditionalDelete(final Long id) {
      Connection conn = null;
      PreparedStatement stat = null; 
      try {
         conn = getDataSource().getConnection();
         stat = conn.prepareStatement(DELETE_BY_ID);
         stat.setLong(1, id);
         stat.execute();
      } catch( final SQLException e ) {
      } finally {
         try {
            if( stat != null) stat.close();
         } catch( SQLException e ) {
            log.log(Level.SEVERE,"Problem closing statement",e);
         }
         try {
            if( conn != null) conn.close();
         } catch( SQLException e ) {
            log.log(Level.SEVERE,"Problem closing connection",e);
         }
      }
   }

   public List<UserAccount> list() {
      final List<UserAccount> list =
         getTemplate().query(SELECT_ACCOUNTS, userMapper);
      for(final UserAccount account : list) {
         populateRoles(account);
      }
      return list;
   }

   public UserAccount read(final Long id) {
      final UserAccount account =
         getTemplate().
            queryForObject(SELECT_ACCOUNT_BY_ID,userMapper,id);
      populateRoles(account);
      return account;
   }

   public UserAccount read(final String accountName) {
      final Long accountId = getTemplate().queryForLong(SELECT_ACCOUNT_ID_BY_NAME, accountName);
      final UserAccount account = new UserAccount(accountName);
      account.setId(accountId);
      populateRoles(account);
      return account;
   }
   
   public UserRole readUserRole(final String roleName) {
      final Long userRoleId = getTemplate().queryForLong(SELECT_USER_ROLE_ID_BY_NAME, roleName);
      final UserRole userRole = new UserRole(roleName);
      userRole.setId(userRoleId);
      return userRole;
   }

   public void update(final UserAccount account) {
      // Update the account entity
      getTemplate().update(UPDATE_ACCOUNT, account.getAccountName(), account.getId());
      
      // Update the role relationships
      persistTransientRoles(account);
      removeDeletedRoles(account);
   }
   
   private void removeDeletedRoles(final UserAccount account) {
      final List<UserRole> roleList = 
         getTemplate().query(SELECT_ROLES_BY_ACCOUNT,roleMapper,account.getId());

      final Set<Long> actual = roleIdSet(roleList);
      final Set<Long> expected = roleIdSet(account.getRoles());
      
      for( final Long roleId : actual ) {
         if( !expected.contains(roleId) ) {
            getTemplate().update(DELETE_ACCOUNT_ROLE, account.getId(),roleId);
         }
      }      
   }
   
   private Set<Long> roleIdSet(final Collection<UserRole> roles) {
      final Set<Long> set = new HashSet<Long>();
      for( final UserRole role : roles ) {
         set.add(role.getId());
      }
      return set;
   }
   
   private void populateRoles(final UserAccount account) { 
      final List<UserRole> roles = 
         getTemplate().query(
                  SELECT_ROLES_BY_ACCOUNT,roleMapper,account.getId());
      account.getRoles().addAll(roles);
   }
}
