package object_model_tests.navigation.payroll;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;

import logging.TestResultLogger;
import object_models.employee.EmployeeCv;
import object_models.employee.EmployeeGridView;
import object_models.employee_creation.EmployeeCreationWizard;
import object_models.helpers.Closable;
import object_models.modules.common.nav.NavBarElement;
import object_models.modules.common.nav.nav_bar_elements.NavBarDakarIntelligence;
import object_models.modules.common.nav.nav_bar_elements.NavBarEmpGridView;
import object_models.modules.common.nav.nav_bar_elements.NavBarEmployeeCVPayroll;
import object_models.modules.common.nav.nav_bar_elements.NavBarEmployeeCreation;
import object_models.modules.common.nav.nav_bar_elements.NavBarMyCoLastViewed;
import object_models.modules.common.nav.nav_bar_elements.NavBarNewEmployments;
import object_models.modules.common.nav.nav_bar_elements.NavBarNotifications;
import object_models.modules.common.nav.nav_bar_elements.NavBarTerminations;
import object_models.modules.common.nav.nav_bar_elements.NavBarUserAvatar;
import object_models.modules.common.nav.nav_bar_elements.NavBarUserManagment;
import object_models.modules.common.nav.nav_bar_elements.NavBarVisualReports;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePagePayroll;
import object_models.reports.DakarIntelligence;
import object_models.reports.VisualReports;
import object_models.top_right_nav_bar.common.TopRightNavBar;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.UserProvider;
import xml_reader.config_file.ConfigReader;
/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 * Test the elements of the Top-Right Nav-Bar for payroll module.
 * Each item in the nav-bar is clicked and its child element returned.
 * The child is then closed.
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
class TopRightNavBar_Payroll_ElementsTests {	
	private static HomePagePayroll homepagePayroll;
	private static TopRightNavBar navBar;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		homepagePayroll = (HomePagePayroll) userLogin.loginValidUser(UserProvider.userPortal());
		navBar = homepagePayroll.getTopRightNavBar();
	}
	
	@AfterAll
	static void teardown() {
		homepagePayroll.close();
	}
	
	@Test
	@Order(1)
	void clickEmployeeCreation() {
		NavBarElement empCr = navBar.getNavBarElement(NavBarEmployeeCreation.ORIGINAL_NAME).get();
		EmployeeCreationWizard wiz = (EmployeeCreationWizard) empCr.clickElement();
		wiz.close();
		assertEquals(NavBarEmployeeCreation.ORIGINAL_NAME, empCr.getOriginalName());
	}
	
	@Test
	@Order(2)
	void clickEmployeeCV() {
		NavBarElement empCV = navBar.getNavBarElement(NavBarEmployeeCVPayroll.ORIGINAL_NAME).get();
		EmployeeCv empCv = (EmployeeCv) empCV.clickElement();
		empCv.close();
		assertEquals(NavBarEmployeeCVPayroll.ORIGINAL_NAME, empCV.getOriginalName());
	}
	
	@Test
	@Order(3)
	void clickEmployeeGridView() {
		NavBarElement empGrid = navBar.getNavBarElement(NavBarEmpGridView.ORIGINAL_NAME).get();
		EmployeeGridView empGridView = (EmployeeGridView) empGrid.clickElement();
		empGridView.close();
		assertEquals(NavBarEmpGridView.ORIGINAL_NAME, empGrid.getOriginalName());
	}
	
	@Test
	@Order(4)
	void clickVisualReports() {
		NavBarElement rep = navBar.getNavBarElement(NavBarVisualReports.ORIGINAL_NAME).get();
		VisualReports visReports = (VisualReports) rep.clickElement();
		visReports.close();
		assertEquals(NavBarVisualReports.ORIGINAL_NAME, rep.getOriginalName());
	}
	
	@Test
	@Order(5)
	void clickDakarIntelligence() {
		NavBarElement dak = navBar.getNavBarElement(NavBarDakarIntelligence.ORIGINAL_NAME).get();
		DakarIntelligence dakInt = (DakarIntelligence) dak.clickElement();
		dakInt.close();
		assertEquals(NavBarDakarIntelligence.ORIGINAL_NAME, dak.getOriginalName());
	}
	
	@Test
	@Order(6)
	void clickCompanyLastViewed() {
		NavBarElement myCo = navBar.getNavBarElement(NavBarMyCoLastViewed.ORIGINAL_NAME).get();
		Closable myCompanyLastViewed = myCo.clickElement();
		myCompanyLastViewed.close();
		assertEquals(NavBarMyCoLastViewed.ORIGINAL_NAME, myCo.getOriginalName());
	}
	
	@Test
	@Order(7)
	void clickNotifications() {
		NavBarElement not = navBar.getNavBarElement(NavBarNotifications.ORIGINAL_NAME).get();
		Closable notifications = not.clickElement();
		notifications.close();
		assertEquals(NavBarNotifications.ORIGINAL_NAME, not.getOriginalName());
	}

	@Test
	@Order(8)
	void clickNewEmployments() {
		NavBarElement newEmps = navBar.getNavBarElement(NavBarNewEmployments.ORIGINAL_NAME).get();
		Closable newEmpsForm= newEmps.clickElement();
		newEmpsForm.close();
		assertEquals(NavBarNewEmployments.ORIGINAL_NAME, newEmps.getOriginalName());
	}

	@Test
	@Order(9)
	void clickTerminations() {
		NavBarElement term = navBar.getNavBarElement(NavBarTerminations.ORIGINAL_NAME).get();
		Closable termForm = term.clickElement();
		termForm.close();
		assertEquals(NavBarTerminations.ORIGINAL_NAME, term.getOriginalName());
	}
	
	@Test
	@Order(10)
	void clickUserManagement() {
		NavBarElement user = navBar.getNavBarElement(NavBarUserManagment.ORIGINAL_NAME).get();
		Closable userManForm = user.clickElement();
		userManForm.close();
		assertEquals(NavBarUserManagment.ORIGINAL_NAME, user.getOriginalName());
	}
	
	@Test
	@Order(11)
	void clickUserAvatar() {
		NavBarElement avatar = navBar.getNavBarElement(NavBarUserAvatar.ORIGINAL_NAME).get();
		Closable userAvatarForm = avatar.clickElement();
		userAvatarForm.close();
		assertEquals(NavBarUserAvatar.ORIGINAL_NAME, avatar.getOriginalName());
	}
}
