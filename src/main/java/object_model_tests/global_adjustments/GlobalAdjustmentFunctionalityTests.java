package object_model_tests.global_adjustments;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import controls.Button;
import controls.ComboSelectFromList;
import controls.ComboWriteAndSelect;
import enums.control_names.CommonControlNames;
import enums.control_names.EmployeeControlNames;
import enums.control_names.GlobalAdjustmentControlNames;
import enums.control_names.PayrollControlNames;
import logging.TestResultLogger;
import object_models.helpers.text_utils.RemoveX;
import object_models.left_menu.payroll.GlobalAdjustments;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 * Test the functionality & objects models 
 * of the Global Adjustments page.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class GlobalAdjustmentFunctionalityTests {
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
	void selectCompany_companyIsGood_noAlertShouldBePresent() {	
		ComboWriteAndSelect cmbComp = (ComboWriteAndSelect) globalAdjustments.getControl(CommonControlNames.COMPANY).get();
		cmbComp.click();		
		cmbComp.writeText("Mars Northe"); // Write part of the company name.
		cmbComp.getAlert().ifPresent(a -> {	fail("Alert should not be present"); });
	}
	
	@Test @Order(3)
	void selectCompany_companyIsBad_alertShouldBePresent() {		
		ComboWriteAndSelect cmbComp = (ComboWriteAndSelect) globalAdjustments.getControl(CommonControlNames.COMPANY).get();
		cmbComp.click();		
		cmbComp.writeText("xxxxMars Northe"); // Write invalid company name.
		String alert = cmbComp.getAlert().get();
		cmbComp.click(); // close
		assertEquals("No results found", alert);		
	}
	
	@Test @Order(4)
	void selectPayPeriods() {		
		ComboWriteAndSelect cmbComp = (ComboWriteAndSelect) globalAdjustments.getControl(PayrollControlNames.PAY_PERIODS).get();
		cmbComp.click();
		String period = cmbComp.getText(new RemoveX());
		cmbComp.click(); // close
		assertEquals(33, period.length());		
	}		

	@Test @Order(5)
	void selectDepartment_implicitPassIfNoErrors() {
		ComboWriteAndSelect cmbComp = (ComboWriteAndSelect) globalAdjustments.getControl(CommonControlNames.DEPARTMENT).get();
		cmbComp.click();
		cmbComp.click(); // close
	}		

	
	@Test @Order(6)
	void selectDepartment_adjustmentType() {
		ComboWriteAndSelect cmb = (ComboWriteAndSelect) globalAdjustments.getControl(GlobalAdjustmentControlNames.VIEW_ADJUSTMENT_TYPE).get();
		cmb.clearAll();;
		cmb.writeText("Allowances");
		assertEquals("Allowances", cmb.getDefaultText());
	}		

	@Test @Order(7)
	void selectPaygroup_implicitPassIfNoErrors() {		
		ComboWriteAndSelect cmbComp = (ComboWriteAndSelect) globalAdjustments.getControl(PayrollControlNames.PAY_GROUP).get(); 	
		cmbComp.clearAll();		
		cmbComp.writeText("Fourw");
		assertEquals("Fourweekly", cmbComp.getDefaultText());
	}		
	
	@Test @Order(8)
	void selectFullOrPartTime_fullTime() {
		ComboSelectFromList cmbComp = (ComboSelectFromList) globalAdjustments.getControl(EmployeeControlNames.FULL_OR_PART_TIME).get();
		cmbComp.click();		
		cmbComp.selectFullText("Full Timer");		
	}		

	@Test @Order(9)
	void selectEmployee() {		
		ComboWriteAndSelect cmbComp = (ComboWriteAndSelect) globalAdjustments.getControl(EmployeeControlNames.EMPLOYEES).get();
		cmbComp.click();		
		cmbComp.selectFullText("3 E");
		cmbComp.click();		
		cmbComp.writeText("Bor");
	}		
	
	@Test @Order(10)
	void acceptCriteria_changeToPartTimer_implicitPassIfNoErrors() {
		ComboSelectFromList cmbComp = (ComboSelectFromList) globalAdjustments.getControl(EmployeeControlNames.FULL_OR_PART_TIME).get();
		cmbComp.click();		
		cmbComp.selectFullText("Part Timer");		
		Button btn = (Button) globalAdjustments.getControl(GlobalAdjustmentControlNames.ACCEPT_CRITERIA).get();
		btn.click();
	}
	
}
