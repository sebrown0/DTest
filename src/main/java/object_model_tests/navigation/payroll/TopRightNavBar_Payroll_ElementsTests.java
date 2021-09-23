package object_model_tests.navigation.payroll;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import logging.TestResultLogger;
import object_models.drop_down_forms.MyCompanyLastViewed;
import object_models.employee.EmployeeCv;
import object_models.employee.EmployeeGridView;
import object_models.employee_creation.EmployeeCreationWizard;
import object_models.left_menu.bulk_updates.EmployeeCreation;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import object_models.reports.DakarIntelligence;
import object_models.reports.VisualReports;
import object_models.top_right_nav_bar.all_elements.NavBarDakarIntelligence;
import object_models.top_right_nav_bar.all_elements.NavBarEmpGridView;
import object_models.top_right_nav_bar.all_elements.NavBarEmployeeCVPayroll;
import object_models.top_right_nav_bar.all_elements.NavBarEmployeeCreation;
import object_models.top_right_nav_bar.all_elements.NavBarMyCoLastViewed;
import object_models.top_right_nav_bar.all_elements.NavBarNewEmployments;
import object_models.top_right_nav_bar.all_elements.NavBarNotifications;
import object_models.top_right_nav_bar.all_elements.NavBarTerminations;
import object_models.top_right_nav_bar.all_elements.NavBarUserAvatar;
import object_models.top_right_nav_bar.all_elements.NavBarUserManagment;
import object_models.top_right_nav_bar.all_elements.NavBarVisualReports;
import object_models.top_right_nav_bar.common.NavBarElement;
import object_models.top_right_nav_bar.common.TopRightNavBar;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;
/**
 * @author Steve Brown
 *
 * Test the elements of the Top-Right Nav-Bar for payroll module.
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class TopRightNavBar_Payroll_ElementsTests {	
	private static HomePage homepagePayroll;
	private static TopRightNavBar navBar;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		homepagePayroll = userLogin.loginValidUser(UserProvider.userPortal());
		navBar = homepagePayroll.getTopRightNavBar();
	}
	
	@AfterAll
	static void teardown() {
//		homepagePayroll.close();
	}
	
	@Test
	@Order(1)
	void clickEmployeeCreation() {
		NavBarElement empCr = navBar.getNavBarElement(NavBarEmployeeCreation.ORIGINAL_NAME).get();
		EmployeeCreationWizard wiz = (EmployeeCreationWizard) empCr.clickElement();
		wiz.closeElement();
		assertEquals(NavBarEmployeeCreation.ORIGINAL_NAME, empCr.getOriginalName());
	}
	
	@Test
	@Order(2)
	void clickEmployeeCV() {
		NavBarElement empCV = navBar.getNavBarElement(NavBarEmployeeCVPayroll.ORIGINAL_NAME).get();
		EmployeeCv empCv = (EmployeeCv) empCV.clickElement();
		empCv.closeElement();
		assertEquals(NavBarEmployeeCVPayroll.ORIGINAL_NAME, empCV.getOriginalName());
	}
	
	@Test
	@Order(3)
	void clickEmployeeGridView() {
		NavBarElement empGrid = navBar.getNavBarElement(NavBarEmpGridView.ORIGINAL_NAME).get();
		EmployeeGridView empGridView = (EmployeeGridView) empGrid.clickElement();
		empGridView.closeElement();
		assertEquals(NavBarEmpGridView.ORIGINAL_NAME, empGrid.getOriginalName());
	}
	
	@Test
	@Order(4)
	void clickVisualReports() {
		NavBarElement rep = navBar.getNavBarElement(NavBarVisualReports.ORIGINAL_NAME).get();
		VisualReports visReports = (VisualReports) rep.clickElement();
		visReports.closeElement();
		assertEquals(NavBarVisualReports.ORIGINAL_NAME, rep.getOriginalName());
	}
	
	@Test
	@Order(5)
	void clickDakarIntelligence() {
		NavBarElement dak = navBar.getNavBarElement(NavBarDakarIntelligence.ORIGINAL_NAME).get();
		DakarIntelligence dakInt = (DakarIntelligence) dak.clickElement();
		dakInt.closeElement();
		assertEquals(NavBarDakarIntelligence.ORIGINAL_NAME, dak.getOriginalName());
	}
	
	@Test
	@Order(6)
	void clickCompanyLastViewed() {
		NavBarElement myCo = navBar.getNavBarElement(NavBarMyCoLastViewed.ORIGINAL_NAME).get();
		MyCompanyLastViewed myCompanyLastViewed =  (MyCompanyLastViewed) myCo.clickElement();
		myCompanyLastViewed.click();
		assertEquals(NavBarMyCoLastViewed.ORIGINAL_NAME, myCo.getOriginalName());
	}
	
//	@Test
//	@Order(7)
//	void clickNotifications() {
//		NavBarElement not = homepagePayroll.getTopRightNavBar().getNavBarElement(NavBarNotifications.ORIGINAL_NAME).get();
//		not.clickElement();
//		assertEquals(NavBarNotifications.ORIGINAL_NAME, not.getOriginalName());
//	}
//
//	@Test
//	@Order(8)
//	void clickNewEmployments() {
//		NavBarElement newEmps = homepagePayroll.getTopRightNavBar().getNavBarElement(NavBarNewEmployments.ORIGINAL_NAME).get();
//		newEmps.clickElement();
//		assertEquals(NavBarNewEmployments.ORIGINAL_NAME, newEmps.getOriginalName());
//	}
//
//	@Test
//	@Order(9)
//	void clickTerminations() {
//		NavBarElement term = homepagePayroll.getTopRightNavBar().getNavBarElement(NavBarTerminations.ORIGINAL_NAME).get();
//		term.clickElement();
//		assertEquals(NavBarTerminations.ORIGINAL_NAME, term.getOriginalName());
//	}
//	
//	@Test
//	@Order(10)
//	void clickUserManagement() {
//		NavBarElement user = homepagePayroll.getTopRightNavBar().getNavBarElement(NavBarUserManagment.ORIGINAL_NAME).get();
//		user.clickElement();
//		assertEquals(NavBarUserManagment.ORIGINAL_NAME, user.getOriginalName());
//	}
//	
//	@Test
//	@Order(11)
//	void clickUserAvatar() {
//		NavBarElement avatar = homepagePayroll.getTopRightNavBar().getNavBarElement(NavBarUserAvatar.ORIGINAL_NAME).get();
//		avatar.clickElement();
//		assertEquals(NavBarUserAvatar.ORIGINAL_NAME, avatar.getOriginalName());
//	}
}
