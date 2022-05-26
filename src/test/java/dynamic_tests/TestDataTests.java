/**
 * 
 */
package dynamic_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import element_library.common.controls.data.InsertItem;
import element_library.common.controls.interfaces.DisplayedText;
import library.helpers.login.UserLoginPage;
import library.left_menu.LeftMenu;
import library.object_models.modules.payroll.left_menu.employees.SalaryDetails;
import library.pages.homepage.HomePage;
import logging.TestResultLogger;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.ZZZ_UserProvider;
import root.elements.ControlGroup;
import site_mapper.jaxb.pom.test_data.TestData;
import site_mapper.jaxb.pom.test_data.TestDataItem;
import xml_reader.config_file.ConfigReader;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class TestDataTests {	
	private static TestDataItem testDataItem;
	
	private static SalaryDetails salDetails;	
//	private static Element el = new Element();
//	private static TestData dataIn = new TestData();
	
	@BeforeAll
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		HomePage homepagePayroll = userLogin.loginValidUser(ZZZ_UserProvider.userPortal());		
		LeftMenu menu = homepagePayroll.getLeftMenu();
		 	
		salDetails = 
			(SalaryDetails)	menu
				.clickParent("Employees")
				.clickAndLoad(SalaryDetails.class)
				.get();
		
		testDataItem = new TestDataItem();
		testDataItem
			.setId("name")
//			.setInsertWith("EmployeeLookupByName")
			.setInsertWith("")
			.setValue("borg");	

	}
	
	@Test
	void getFormId_asInsertItem() {
//		ControlGroup grp = 
//				(ControlGroup) salDetails
//						.getPanelControl()
//						.getControl("EmpLookup")
//						.get();
//		
//		InsertItem insertItemIntoFormId = 
//				(InsertItem) grp.getControlByTitle("FormID").get();
//		
//		insertItemIntoFormId.insert(testDataItem);
//		assertTrue(insertItemIntoFormId != null);
	}
	
	@Test
	void getGrade_asInsertItem() {
		ControlGroup grp = 
				(ControlGroup) salDetails
						.getPanelControl()
						.getControl("SalaryDetails")
						.get();
		
		InsertItem insertItemIntoFormId = 
				(InsertItem) grp.getControlByTitle("Grade").get();
		
//		insertItemIntoFormId.insert(testDataItem, null);
		
		DisplayedText cntrl = (DisplayedText) insertItemIntoFormId;
		assertEquals("borg", cntrl.getText());
	}
	
	@Test
	void test_testDataItem() {		
		assertEquals("borg", testDataItem.getValue());
	}	

	@Test
	void test_testData() {				
		TestData data = new TestData();	
		data.setTestDataIn(Arrays.asList(testDataItem));
		
		assertEquals("borg", data.getTestDataIn().get(0).getValue());
	}
	
//	@Test
//	void test_dataInserter() {
//		DataInserter dataInserter = new DataInserterItem(testDataItem);
//		dataInserter.insertData(salDetails);
//		
//	}
	
}
