package com.apress.timesheets.dao.hibernate;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.apress.timesheets.dao.UserAccountDao;
import com.apress.timesheets.entity.UserAccount;
import com.apress.timesheets.entity.UserRole;

public class HibernateUserAccountDaoImpl extends HibernateDaoSupport implements
         UserAccountDao {
   
   public static final String GET_BY_NAME = "getUserAccountByName";   
   public static final String LIST_BY_NAME = "listUserAccountByName";

   public void create(final UserAccount account) {
      getHibernateTemplate().persist(account);
   }

   public UserAccount read(final Long id) {
      return
         (UserAccount)getHibernateTemplate().get(UserAccount.class,id);
   }

   public void update(final UserAccount account) {
      getHibernateTemplate().update(account);
   }

   public void delete(final UserAccount account) {
      getHibernateTemplate().delete(account);
   }

   public void delete(final Long id) {
      getHibernateTemplate().delete(read(id));
   }

   @SuppressWarnings("unchecked")
   public List<UserAccount> list() {
      return (List<UserAccount>)
         getHibernateTemplate().findByNamedQuery(LIST_BY_NAME);
   }

   public UserAccount read(final String accountName) {
      final Session session = getSession();
      try {
         final Query query = session.getNamedQuery(GET_BY_NAME);
         query.setParameter("name", accountName);
         return (UserAccount)query.uniqueResult();
      } finally {
         releaseSession(session);
      }
   }
   
   @SuppressWarnings("unchecked")
   public UserRole readUserRole(final String roleName) {
      final List<UserRole> list = 
         getHibernateTemplate().
            findByNamedQueryAndNamedParam(
                     "findUserRole", "roleName", roleName);
      
      if( list.size() > 0 ) {
         return list.get(0);
      } else {
         return null;
      }
   }
}
