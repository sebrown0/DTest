/**
 * 
 */
package config_tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;

import parameter_resolvers.ConfigParameterResolver;
import xml_reader.config_file.ConfigReader;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * Test that the ConfigReader works correctly.
 */
@ExtendWith(ConfigParameterResolver.class)
class ConfigFile_Tests {
	private static ConfigReader configReader;
	
	@BeforeAll	
	public static void setup(ConfigReader _configReader) throws Exception {
		configReader = _configReader;
	}	
	
	@Test
	void configReader_notNull() {
		assertNotNull(configReader);
	}
	
	//Disable so that a driver context is not created each time.
	@Disabled @Test
	void driver_notNull() {
		WebDriver driver = configReader.getDriver(); 
		assertNotNull(driver);
		driver.close();
	}
	
	@Test
	void module_exists() {
		assertTrue(configReader.getModule().length() > 0);
	}

	@Test
	void sitemapXml_exists() {
		assertTrue(configReader.getSiteMapXmlLocation().length() > 0);
	}
	
	@Test
	void getCompany() {
		assertTrue(configReader.getCompany().length() > 0);		
	}

	@Test
	void getUri() {
		assertTrue(configReader.getUri().length() > 0);
	}
	
	@Test
	void getResultWriter() {	
		assertNotNull(configReader.getResultWriter("aTestSuite"));
	}
	
}
