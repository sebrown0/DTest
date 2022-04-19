/**
 * 
 */
package dynamic_tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;

import app.xml_content.DynamicTestMapper;
import dynamic_tests.mappers.DynamicTestApp;
import library.helpers.login.UserLoginPage;
import library.pages.homepage.HomePage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.ZZZ_UserProvider;
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
class DynamicTestsFromSiteMapperTests {
	private static HomePage hp;	
	
	private static final String XML_SOURCE = 
			"./src/main/resources/site_map/site_map.xml";
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) throws Exception {
		hp = userLogin.loginValidUser(ZZZ_UserProvider.userPortal());
	}	

	@AfterAll
	public static void tearDown() {
		hp.close();
	}

	@TestFactory	 
	DynamicContainer runTests() {
		app.xml_content.DynamicTestApp content = 
				DynamicTestMapper.getDynamicTestContent(XML_SOURCE).get();
		
		DynamicTestApp app = 
				new DynamicTestApp(
						content, 
						hp);		
		return app.getAppTests();
	}
	
}
