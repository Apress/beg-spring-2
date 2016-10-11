package com.apress.timesheets.dao.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.HSQLDialect;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.apress.timesheets.dao.ConfigHelper;
import com.apress.timesheets.entity.Period;
import com.apress.timesheets.entity.Rate;
import com.apress.timesheets.entity.RateType;
import com.apress.timesheets.entity.Timesheet;
import com.apress.timesheets.entity.UserAccount;
import com.apress.timesheets.entity.UserRole;
import com.apress.timesheets.security.AcegiUserDetails;

public class HibernateConfigHelper implements ConfigHelper {

   private HibernateTemplate template;

   public void setUp() throws Exception {
      final AnnotationConfiguration configuration = new AnnotationConfiguration();
      configuration.setProperty(Environment.DRIVER,
               "org.hsqldb.jdbcDriver");
      configuration.setProperty(Environment.URL,
               "jdbc:hsqldb:mem:HibernateDaoTest");
      configuration.setProperty(Environment.USER, "sa");
      configuration.setProperty(Environment.DIALECT, HSQLDialect.class
               .getName());
      configuration.setProperty(Environment.SHOW_SQL, "true");
      configuration.setProperty(Environment.HBM2DDL_AUTO, "create-drop");
      configuration.addAnnotatedClass(UserRole.class);
      configuration.addAnnotatedClass(UserAccount.class);
      configuration.addAnnotatedClass(Period.class);
      configuration.addAnnotatedClass(RateType.class);
      configuration.addAnnotatedClass(Rate.class);
      configuration.addAnnotatedClass(Timesheet.class);
      configuration.addAnnotatedClass(AcegiUserDetails.class);
      final SessionFactory sessionFactory = configuration
               .buildSessionFactory();

      final String[] schema = configuration
               .generateSchemaCreationScript(new HSQLDialect());
      for (final String table : schema) {
         System.out.println("SCHEMA: " + table);
      }

      this.template = new HibernateTemplate(sessionFactory);

      final Session session = SessionFactoryUtils.getSession(
               sessionFactory, true);
      TransactionSynchronizationManager.bindResource(sessionFactory,
               new SessionHolder(session));
   }

   public void tearDown() throws Exception {
      final SessionFactory factory = template.getSessionFactory();
      final SessionHolder sessionHolder = (SessionHolder) TransactionSynchronizationManager
               .unbindResource(factory);
      SessionFactoryUtils.closeSession(sessionHolder.getSession());
   }

   public void clear() {
      template.flush();
      template.clear();
   }

   public HibernateTemplate getTemplate() {
      return template;
   }

   public void setTemplate(HibernateTemplate template) {
      this.template = template;
   }
}
