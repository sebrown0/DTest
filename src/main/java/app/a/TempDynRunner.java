/**
 * 
 */
package app.a;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import object_models.pages.homepage.HomePage;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class TempDynRunner {
	private static HomePage hp;	
	
	private static final String XML_SOURCE = 
			"./src/main/resources/site_map/site_map.xml";
	
	@Test
	public void test() {
		System.out.println("goodbye"); 	
		assertTrue(true);
	}
	
//	@TestFactory	 
//	public DynamicContainer runTests() {
//		ConfigReader reader = new ConfigReader();		
//		WebDriver driver = reader.getDriver();
//		
////		LogManager.getLogger(LoginPageResolverPayroll.class).debug("Loging in with default company");
//		UserLoginPage userLogin =
//				new UserLoginPage(
//						driver,
//						reader.getUri(),
//						new PayrollModuleElements(new Company(reader.getCompany())));
//		
//		hp = userLogin.loginValidUser(new User("steve", "1234"));
//		
//		app.xml_content.DynamicTestApp content = 
//				DynamicTestMapper.getDynamicTestContent(XML_SOURCE).get();
//		
//		DynamicTestApp app = 
//				new DynamicTestApp(
//						content, 
//						hp);		
//		return app.getAppTests();
//	}

}
