package object_model_tests.navigation;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import exceptions.NullDriverException;
import object_models.modules.PayrollModuleLoader;
import object_models.navigation.NavBarElement;
import object_models.navigation.top_right_nav_bar.elements.NavBarElementGetter;
import object_models.navigation.top_right_nav_bar.elements.NavBarEmployeeCreation;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import providers.XMLFileProvider;
import resources.test_data.UserProvider;
import xml_reader.ConfigReader;

class TopRightNavBarElementsTests {	
	private static HomePage hp;
	private static WebDriver driver;
	private static UserLoginPage userLogin;
	private static ConfigReader configReader;	
	
	@BeforeAll	
	static void setup() throws NullDriverException {	
		configReader = new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH);
		// Get a web driver as specified in the config.xml		
		driver = configReader.getDriver();
		// Get a login page, with the required module loaded.
		userLogin = new UserLoginPage(driver, new PayrollModuleLoader(driver));
		// Get a home page after successful login
		hp = userLogin.loginValidUser(UserProvider.userPortal());		
	}
	
	@AfterAll
	static void teardown() {
		driver.quit();
	}
	

	@Test
	void clickEmployeeCreation_usingElement() {
		NavBarElementGetter navBarElemGetter = hp.getTopRightNavBar();
		Optional<NavBarElement> empCreation = navBarElemGetter.getNavBarElement(NavBarEmployeeCreation.ORIGINAL_NAME);
		empCreation.ifPresentOrElse(ec -> ec.clickElement(), fail("Failed to click employee creation."));
	}
	
//	@Test
//	void getEmpCreationElement_Using_NavBarPayrollElements_PayrollStrategy() {
//		NavBarElementStrategy elementStrategy = new NavBarPayrollElements(null);
//		Map<String, NavBarElement> map = elementStrategy.getElements();
//		NavBarElement empCreation = map.get(NavBarEmployeeCreation.ORIGINAL_NAME);
//		assertEquals("Employee Creation", empCreation.getOriginalName());
//	}

//	@Test
//	void loadEmpCreationWizard_Using_PayrollStrategy() throws ElementDoesNotExistException {
//		NavBarEmployeeCreation empCreation = (NavBarEmployeeCreation) hp.getTopRightNavBar().getNavBarElements().getElement(NavBarEmployeeCreation.ORIGINAL_NAME);
//		XJsPanel empCreattionWizard = empCreation.loadEmployeeCreation();
//		assertEquals(empCreattionWizard.getExpectedTitle(), empCreattionWizard.getActualTitle());
//		empCreattionWizard.close();		
//	}	
	
//	@Test
//	void getEmpCV_Using_TopRightNavBarElements_With_PayrollStrategy() throws InterruptedException {
//		NavBarEmployeeCVPayroll navEmpCV = (NavBarEmployeeCVPayroll) hp.getTopRightNavBar().getNavBarElements().getElement(NavBarEmployeeCVPayroll.ORIGINAL_NAME);
//		
//		EmployeeCV panelEmpCv = (EmployeeCV) navEmpCV.loadChild();
//		assertEquals(panelEmpCv.getTitle().getExpectedTitle(), panelEmpCv.getTitle().getActualTitle(driver));
//
//		panelEmpCv.switchToIFrame();
////		EmployeeCV empCV = (EmployeeCV) panelEmpCv;
////		assertTrue(empCV.getCompanyLabel().getLabelText().equals("Company"));
//		
////		String companyLabelText = driver.findElement(By.className("col-md-2")).getText();
////		assertTrue(companyLabelText.contains("Company"));		
//		panelEmpCv.close();
//	}
	
//	
//	@Test
//	void getEmpGrid_Using_TopRightNavBarElements_With_PayrollStrategy() {
//		TopRightNavBarElements trnbElements = new TopRightNavBarElements(null, new NavBarPayrollElements(null));
//		NavBarElement empGrid = trnbElements.getElement(NavBarEmpGridView.ORIGINAL_NAME);
//		assertEquals("Employee Grid View", empGrid.getOriginalName());
//	}
//
//	@Test
//	void getVisualReports_Using_TopRightNavBarElements_With_PayrollStrategy() {
//		TopRightNavBarElements trnbElements = new TopRightNavBarElements(null, new NavBarPayrollElements(null));
//		NavBarElement empGrid = trnbElements.getElement(NavBarVisualReports.ORIGINAL_NAME);
//		assertEquals("Visual Reports", empGrid.getOriginalName());
//	}
//
//	@Test
//	void getDakarIntelligence_Using_TopRightNavBarElements_With_PayrollStrategy() {
//		TopRightNavBarElements trnbElements = new TopRightNavBarElements(null, new NavBarPayrollElements(null));
//		NavBarElement empGrid = trnbElements.getElement(NavBarDakarIntelligence.ORIGINAL_NAME);
//		assertEquals("Dakar Intelligence", empGrid.getOriginalName());
//	}
//
//	@Test
//	void getMyCoLastViewed_Using_TopRightNavBarElements_With_PayrollStrategy() {
//		TopRightNavBarElements trnbElements = new TopRightNavBarElements(null, new NavBarPayrollElements(null));
//		NavBarElement empGrid = trnbElements.getElement(NavBarMyCoLastViewed.ORIGINAL_NAME);
//		assertEquals("My Company / Last Viewed", empGrid.getOriginalName());
//	}
//	
//	@Test
//	void getEmpCreationElement_Using_Payroll_TopRightNavBar() throws NullDriverException {
//		ModuleLoader moduleLoader = new PayrollModuleLoader(driver);
//		TopRightNavBar navBar = new TopRightNavBar(driver, moduleLoader.getElementStrategy());
//		NavBarElement empCreation = navBar.getNavBarElements().getElement(NavBarEmployeeCreation.ORIGINAL_NAME);
//		assertEquals("Employee Creation", empCreation.getOriginalName());
//	}

	
/*
 * Keep this as an example of how we could use the clicker to load elements.
 */
//	@Test
//	void clickEmployeeCreation_usingNavClicker() {
//		TopRightNavBar topRightNavBar = hp.getTopRightNavBar();
//		XX_PayrollNavBarClicker clicker = (XX_PayrollNavBarClicker) topRightNavBar.getNavBarClicker();
//		try {
//			clicker.clickEmployeeCreation();
//		} catch (ElementDoesNotExistException e) {
//			fail("Failed to click employee creation.");
//		}
//	}
}
