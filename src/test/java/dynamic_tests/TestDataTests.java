/**
 * 
 */
package dynamic_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import controls.with_text.TextOut;
import dynamic_tests.elements.ControlGroup;
import logging.TestResultLogger;
import object_models.left_menu.common.LeftMenu;
import object_models.modules.payroll.left_menu.employees.SalaryDetails;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.UserProvider;
import site_mapper.jaxb.pom.Element;
import site_mapper.jaxb.pom.test_data.Data;
import site_mapper.jaxb.pom.test_data.TestData;
import site_mapper.jaxb.pom.test_data.TestDataIn;
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
	private static SalaryDetails salDetails;	
	private static Element el = new Element();
	private static TestDataIn dataIn = new TestDataIn();
	
	@BeforeAll
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		HomePage homepagePayroll = userLogin.loginValidUser(UserProvider.userPortal());		
		LeftMenu menu = homepagePayroll.getLeftMenu();
		salDetails = 
			(SalaryDetails)	menu
				.clickParent("Employees")
				.clickAndLoad(SalaryDetails.class)
				.get();
		
		TestDataItem testDataItem = new TestDataItem();
		testDataItem
			.setId("name")
			.setInsertWith("EmployeeLookupByName")
			.setValue("a name");
		
		Data data = new Data();
		data.setTestDataList(Arrays.asList(testDataItem));

		dataIn
			.setData(data);
		
		el
			.setName("FormId")
			.setType("TextOut")
			.setTestData(new TestData().setTestDataIn(dataIn));
	}
	
	@Test
	void getFormId() {
		ControlGroup grp = 
				(ControlGroup) salDetails
						.getPanelControl()
						.getControl("EmpLookup")
						.get();
		
		TextOut formId = (TextOut) grp.getControlByTitle("FormID").get();
		assertTrue(formId != null);
	}
	
//	@Test
//	void test() {
//		ControlGroup grp = 
//				(ControlGroup) salDetails
//						.getPanelControl()
//						.getControl("EmpLookup")
//						.get();
//		
////		InsertText formId = (TextOut) grp.getControlByTitle("FormID").get();
////		formId.insertText("");
//		TestAdderWithData textOut = new TestAdderTextOut(el);
//		TestDataVerifier dataVerifier = new TestDataVerifier(textOut);
//		dataVerifier.verifyDataFor();
//	}

}
