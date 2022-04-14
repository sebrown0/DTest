/**
 * 
 */
package app.a;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import app.xml_content.DynamicTestMapper;
import dynamic_tests.mappers.DynamicTestApp;
import library.dakar_hr.entities.company.Company;
import library.dakar_hr.entities.user.User;
import library.dakar_hr.helpers.login.UserLoginPage;
import library.dakar_hr.object_models.modules.payroll.PayrollModuleElements;
import library.dakar_hr.pages.homepage.HomePage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import xml_reader.config_file.ConfigReader;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@ExtendWith({ 
	ConfigParameterResolver.class,
	LoginPageResolverPayroll.class })
public class DynamicTestsFromSiteMapperTests {
	private static HomePage hp;	
	
	private static final String XML_SOURCE = 
			"./src/main/resources/site_map/site_map.xml";
	
//	@BeforeAll	
//	public static void setup(ConfigReader configReader, UserLoginPage userLogin) throws Exception {
//		hp = userLogin.loginValidUser(UserProvider.userPortal());
//	}	
//
//	@AfterAll
//	public static void tearDown() {
////		hp.close();
//	}

	@TestFactory	 
	public DynamicContainer runTests() {
		ConfigReader reader = new ConfigReader();		
		WebDriver driver = reader.getDriver();
		
//		LogManager.getLogger(LoginPageResolverPayroll.class).debug("Loging in with default company");
		UserLoginPage userLogin =
				new UserLoginPage(
						driver,
						reader.getUri(),
						new PayrollModuleElements(new Company(reader.getCompany())));
		
		hp = userLogin.loginValidUser(new User("steve", "1234"));
		
		app.xml_content.DynamicTestApp content = 
				DynamicTestMapper.getDynamicTestContent(XML_SOURCE).get();
		
		DynamicTestApp app = 
				new DynamicTestApp(
						content, 
						hp);		
		return app.getAppTests();
	}
	
}
