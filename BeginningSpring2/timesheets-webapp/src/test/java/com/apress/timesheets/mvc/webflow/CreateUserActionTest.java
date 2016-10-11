package com.apress.timesheets.mvc.webflow;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.reportMatcher;
import static org.easymock.EasyMock.verify;
import junit.framework.TestCase;

import org.easymock.IArgumentMatcher;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.support.EventFactorySupport;
import org.springframework.webflow.test.MockParameterMap;
import org.springframework.webflow.test.MockRequestContext;

import com.apress.timesheets.entity.UserAccount;
import com.apress.timesheets.service.UserAccountService;

public class CreateUserActionTest extends TestCase {
   private final EventFactorySupport support = new EventFactorySupport(); 
   private CreateUserAction action;

   @Override
   protected void setUp() throws Exception {
      action = new CreateUserAction();
      action.setFormObjectName("command");
      action.setFormObjectClass(CreateUserForm.class);
   }
   
   public void testEdit() throws Exception {
      // Setup test data
      final MockRequestContext context = new MockRequestContext();

      // Carry out the action
      final Event event = action.edit(context);
      
      // Verify success
      assertNotNull(event);
      final String successEventId = support.success(action).getId();
      assertEquals(successEventId,event.getId());
   }

   public void testSave() throws Exception {
      // Setup test data
      final MockParameterMap params = new MockParameterMap();
      params.put("username", "example");
      final MockRequestContext context = new MockRequestContext(params);
      action.bind(context);
      final UserAccount account = new UserAccount("example");
      
      // Script the test
      final UserAccountService service = createMock(UserAccountService.class);
      action.setUserAccountService(service);
      service.createUser(matchesNamedAccount(account));
      replay(service);
      
      // Carry out the action
      final Event event = action.save(context);

      // Verify success
      verify(service);
      final String successEventId = support.success(action).getId();
      assertEquals(successEventId,event.getId());
   }

   private static <T extends UserAccount> T matchesNamedAccount(final T account) {
      reportMatcher(new NamedUserAccountMatcher(account));
      return account;
   }

   private static class NamedUserAccountMatcher implements IArgumentMatcher {
      private String expected;
      
      public NamedUserAccountMatcher(final UserAccount expected) {
         this.expected = expected.getAccountName();
      }

      public boolean matches(final Object object) {
         if(!(object instanceof UserAccount)) {
            return false;
         }
         
         final UserAccount account = (UserAccount)object;
         final String actual = account.getAccountName();
         return (expected == actual) || ((expected != null) && expected.equals(actual));
      };

      public void appendTo(final StringBuffer buffer) {
         buffer.append("eqNamedUserAccount(");
         buffer.append(expected.getClass().getName());
         buffer.append(" with account name \"");
         buffer.append(expected);
         buffer.append("\")");
      }
   }
}
