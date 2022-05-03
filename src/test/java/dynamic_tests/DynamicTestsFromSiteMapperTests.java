/**
 * 
 */
package dynamic_tests;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;

import app.xml_content.DynamicTestMapper;
import library.helpers.login.UserLoginPage;
import library.pages.homepage.HomePage;
import library.xml_file.FileLocator;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.UserProvider;
import root.mappers.DynamicTestApp;
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
	private static final Logger LOGGER = LogManager.getLogger(DynamicTestsFromSiteMapperTests.class);
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) throws Exception {
		hp = userLogin.loginValidUser(UserProvider.userPortal());
	}	

	@AfterAll
	public static void tearDown() {
		hp.close();		
	}

	@TestFactory	 
	DynamicContainer runTests() {
		final Optional<String> XML_SOURCE = new FileLocator().getPathToFile();
		
		if(XML_SOURCE.isPresent()){
			LOGGER.info(String.format("Get content for [%s]", XML_SOURCE.get()));
			
			app.xml_content.DynamicTestApp content = 
					DynamicTestMapper.getDynamicTestContent(XML_SOURCE.get()).get();
			
			LOGGER.info("Got content. Getting tests");
			
			return new DynamicTestApp(content, hp).getAppTests();
		}else {
			LOGGER.error("Could not find XML source");
		}
		return null;		
	}
	
}
