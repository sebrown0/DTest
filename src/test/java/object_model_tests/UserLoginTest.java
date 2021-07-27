package object_model_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import object_models.pages.HomePage;
import object_models.pages.LoadablePage;
import object_models.pages.UserLoginPage;

class UserLoginTest extends LoggedTestRailTests {
	
	@Test
	@Tag("R20")
	@Tag("T3833")
	void createUserLoginModel() {
		LoadablePage userLogin = new UserLoginPage(driver);		
		assertTrue(userLogin.isPageTitleCorrect());
	}

	@Test
	@Tag("R20")
	@Tag("T3834")
	void validUserLogin() {		
		UserLoginPage userLogin = new UserLoginPage(driver);		
		HomePage hp = userLogin.loginValidUser("steve", "1234");		
		assertTrue(hp.isPageTitleCorrect());
	}

	@Test
	@Tag("R20")
	@Tag("T3835")
	void invalidUserLogin() {		
		UserLoginPage userLogin = new UserLoginPage(driver);		
		HomePage hp = userLogin.loginValidUser("steve", "12");		
		assertTrue(!hp.isPageTitleCorrect());
	}
}
