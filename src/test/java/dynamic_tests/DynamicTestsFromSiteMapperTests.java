/**
 * 
 */
package dynamic_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;

import app.xml_content.DynamicTestMapper;
import dynamic_tests.mappers.DynamicTestApp;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.UserProvider;
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
		
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) throws Exception {
		// Login to the homepage
		hp = userLogin.loginValidUser(UserProvider.userPortal());
	}
	private static final String XML_SOURCE = 
			"./src/main/resources/site_map/site_map.xml";
//	private static final String XML_SOURCE = 
//	"./src/test/resources/site_map/site_map.xml";

	@AfterAll
	public static void tearDown() {
//		hp.close();
	}
	
//	@Test
//	void getincludeElementsForTestFrom_DynamicTestMapper() {				
//		Optional<app.xml_content.DynamicTestApp> content = 
//				DynamicTestMapper.getDynamicTestContent(XML_SOURCE);
//		
//		List<String> elements = 
//				content.get().getIncludeElementsForTest();
//		
//		assertTrue(elements.size()>0);
//		assertEquals("button",elements.get(0));
//	}

	@TestFactory
	DynamicContainer runTests() {
		DynamicTestApp app = 
				new DynamicTestApp(
						DynamicTestMapper.getDynamicTestContent(XML_SOURCE).get(), 
						hp);		
		return app.getAppTests();
	}
//	@Test
//	void get_dynamicContainer() {
//		DynamicContainer tests =
//				DynamicTestApp.getTests(
//						DynamicTestMapper.getDynamicTestContent(XML_SOURCE).get(), 
//						null);
//		
//		assertTrue(tests != null);
//	}
}
