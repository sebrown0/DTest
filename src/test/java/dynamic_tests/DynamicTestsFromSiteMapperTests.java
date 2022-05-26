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
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.UserProvider;
import root.mappers.DynamicTestApp;
import xml_reader.XmlSourceResolver;
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
	private static ConfigReader configReader;
	
	private static final Logger LOGGER = 
			LogManager.getLogger(DynamicTestsFromSiteMapperTests.class);
	
	@BeforeAll	
	public static void setup(ConfigReader _configReader, UserLoginPage userLogin) throws Exception {
		hp = userLogin.loginValidUser(UserProvider.userPortal());
		configReader = _configReader;
	}	

	@AfterAll
	public static void tearDown() {
		hp.close();		
	}

	@TestFactory	 
	DynamicContainer runTests() {		
		
		XmlSourceResolver resolver = new XmlSourceResolver(configReader.getSiteMapXmlLocation());
		final Optional<String> XML_SOURCE = resolver.getPathToFile();
		
		if(XML_SOURCE.isPresent()){
			String src = XML_SOURCE.get();
			LOGGER.info(String.format("Getting content for [%s]", src));
			
			app.xml_content.DynamicTestApp content = 
					DynamicTestMapper.getDynamicTestContent(src).get();
			
			LOGGER.info("Got content. Getting tests");
			
			return new DynamicTestApp(content, hp).getAppTests();
		}else {
			LOGGER.error("Could not find XML source");
		}
		return null;		
	}
	
}
