package object_model_tests.global_adjustments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import controls.ComboSelect;
import enums.control_names.CommonControlNames;
import enums.control_names.EmployeeControlNames;
import enums.control_names.PayrollControlNames;
import logging.TestResultLogger;
import object_models.helpers.text_utils.RemoveX;
import object_models.left_menu.payroll.GlobalAdjustments;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class GlobalAdjustmentTests {
	private static HomePage homepagePayroll;	
	private static GlobalAdjustments globalAdjustments; 
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		// Login to the home page.
		homepagePayroll = userLogin.loginValidUser(UserProvider.userPortal());
		// Load the page.
		globalAdjustments = (GlobalAdjustments) homepagePayroll.getLeftMenu().clickAndLoad(GlobalAdjustments.class).get();
	}	

	@Test @Order(1)
	void loadGlobalAdjustments() {		
		assertTrue(globalAdjustments != null);
	}		
	
	@Test @Order(2)
	void selectCompany_implicitPassIfNoErrors() {
		ComboSelect cmbComp = (ComboSelect) globalAdjustments.getControl(CommonControlNames.COMPANY).get();
		cmbComp.click();		
	}
	
	@Test @Order(3)
	void selectPayPeriods() {
		ComboSelect cmbComp = (ComboSelect) globalAdjustments.getControl(PayrollControlNames.PAY_PERIODS).get();
		cmbComp.click();
		String period = cmbComp.getText(new RemoveX());
		assertEquals(33, period.length());		
	}		

	@Test @Order(4)
	void selectDepartment_implicitPassIfNoErrors() {
		ComboSelect cmbComp = (ComboSelect) globalAdjustments.getControl(CommonControlNames.DEPARTMENT).get();
		cmbComp.click();		
	}		

	@Test @Order(5)
	void selectPaygroup_implicitPassIfNoErrors() {
		ComboSelect cmbComp = (ComboSelect) globalAdjustments.getControl(PayrollControlNames.PAY_GROUP).get();
		cmbComp.click();		
	}		
	
	@Test @Order(6)
	void selectFullOrPartTime_fullTime() {
		ComboSelect cmbComp = (ComboSelect) globalAdjustments.getControl(EmployeeControlNames.FULL_OR_PART_TIME).get();
		cmbComp.click();		
		cmbComp.writeText("Full Timer");		
	}		
	
}
