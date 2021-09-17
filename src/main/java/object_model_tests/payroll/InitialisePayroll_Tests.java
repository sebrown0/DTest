package object_model_tests.payroll;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import logging.TestResultLogger;
import object_models.forms.ContainerAction;
import object_models.left_menu.common.LeftMenuActions;
import object_models.left_menu.employees.Banks;
import object_models.left_menu.payroll.InitialisePayroll;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * @author Steve Brown
 *
 */
@ExtendWith({ 
	ConfigParameterResolver.class, 
	LoginPageResolverPayroll.class, 
	TestResultLogger.class })
public final class InitialisePayroll_Tests {
	private static WebDriver driver;
	private static LeftMenuActions leftMenu;
	
	@BeforeAll
	static void setUpBeforeClass(ConfigReader configReader, UserLoginPage userLogin) throws Exception {
		HomePage hp = userLogin.loginValidUser(UserProvider.userPortal());
		driver = hp.getWebDriver();
		leftMenu = hp.getLeftMenu();
	}

	@Test
	@Order(1)
	void test() {
		Optional<ContainerAction> contPay = leftMenu
				.clickParent(InitialisePayroll.MENU_PARENT_NAME)
				.clickAndLoad(InitialisePayroll.MENU_TITLE);
		
		contPay.get();
//		if(contPay.isPresent()) {
//			InitialisePayroll initPay = (InitialisePayroll) contPay.get();
//			System.out.println("->" + initPay.getVal()); 		
//		}else {
//			System.out.println("-> XXXXXXXXXXXXXXXXXXXXXX");
//		}
//		initPay.ifPresent(init -> init.closeElement());
	}


	@AfterAll
	static void tearDownAfterClass() throws Exception {
//		driver.quit();
	}

}
