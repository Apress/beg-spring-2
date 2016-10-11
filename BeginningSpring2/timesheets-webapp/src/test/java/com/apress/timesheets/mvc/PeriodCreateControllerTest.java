package com.apress.timesheets.mvc;

import static org.easymock.EasyMock.createMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import junit.framework.TestCase;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.apress.timesheets.entity.Period;
import com.apress.timesheets.entity.RateType;
import com.apress.timesheets.entity.Timesheet;
import com.apress.timesheets.service.TimesheetService;

public class PeriodCreateControllerTest extends TestCase {

      private PeriodCreateController controller = new PeriodCreateController();
      
      @Override
      protected void setUp() throws Exception {
         controller.setSuccessView("view");
      }

      // Tip: When stubbing out your unit test, I recommend explicitly 
      // failing all of them by default. This way if you forget to 
      // write a test, you'll see it as a failure when you run the
      // suite and be reminded to implement it! Don't remove the
      // failure line until you have finished writing it.
//      public void testReferenceData() {
//         fail();
//      }
      
      public void testReferenceData() throws Exception {
         // Prepare test data
         final PeriodCreateForm form = new PeriodCreateForm();
         form.setTimesheetId(10L);
         final Timesheet timesheet = new Timesheet();
         form.setTimesheetId(10L);
         final List<RateType> rateTypes = new ArrayList<RateType>();
         final RateType rateType = new RateType("daily");
         rateTypes.add(rateType);
         final BindException errors = new BindException(form,"command");
         final HttpServletRequest request = createMock(HttpServletRequest.class);
         
         // Script expectations
         final TimesheetService service = createMock(TimesheetService.class);
         controller.setTimesheetService(service);
         expect(service.findTimesheet(10L)).andReturn(timesheet);
         expect(service.getRateTypeList()).andReturn(rateTypes);
         replay(service);

         // Test the behavior
         final Map data = controller.referenceData(request, form, errors);
         verify(service);
         assertNotNull(data);
         final Timesheet actualTimesheet = (Timesheet)data.get("timesheet");
         assertNotNull(actualTimesheet);
         assertEquals(timesheet,actualTimesheet);
         final List actualRateTypes = (List)data.get("rateTypeList");
         assertNotNull(actualRateTypes);
         assertEquals(rateTypes,actualRateTypes);
         assertNotNull(form.getRateTypeId());
         assertEquals("daily",form.getRateTypeId());
      }

      public void testCreatePeriod() throws Exception {
         // Prepare test data
         final Calendar now = Calendar.getInstance();
         final BigDecimal rate = new BigDecimal(10);
         final PeriodCreateForm form = new PeriodCreateForm();
         form.setTimesheetId(10L);
         form.setStartTime(now);
         form.setEndTime(now);
         form.setNote("note");
         form.setRate(rate);
         form.setRateTypeId("daily");
         final BindException errors = new BindException(form,"command");
         final Timesheet timesheet = new Timesheet();
         
         // Script expectations
         final TimesheetService service = createMock(TimesheetService.class);
         controller.setTimesheetService(service);         
         final Period period = new Period();
         expect(service.findTimesheet(10L)).andReturn(timesheet);
         expect(service.createPeriod(timesheet,now,now,"note",rate,"daily")).andReturn(period);         
         replay(service);

         // Carry out the operation
         final ModelAndView mav = controller.onSubmit(form, errors);

         // Test the behavior
         verify(service);
         final String view = mav.getViewName();
         assertEquals("view",view);
         final Map model = mav.getModel();
         final Timesheet actualTimesheet = (Timesheet)model.get("timesheet");
         assertNotNull(actualTimesheet);
         assertEquals(timesheet,actualTimesheet);
      }
}
