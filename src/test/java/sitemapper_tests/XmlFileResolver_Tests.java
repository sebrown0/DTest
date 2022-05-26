package sitemapper_tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import parameter_resolvers.ConfigParameterResolver;
import xml_reader.XmlSourceResolver;
import xml_reader.config_file.ConfigReader;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * Test that the XmlSourceResolver works correctly.
 */
@ExtendWith(ConfigParameterResolver.class)
class XmlFileResolver_Tests {
	private static ConfigReader configReader;
	
	@BeforeAll	
	public static void setup(ConfigReader _configReader) throws Exception {
		configReader = _configReader;
	}	
	
	@Test
	void configReader_notNull() {
		assertNotNull(configReader);
	}
	
	@Test
	void xmlSourceResolver_notNull() {
		XmlSourceResolver resolver = 
				new XmlSourceResolver(configReader.getSiteMapXmlLocation());
		assertNotNull(resolver);
	}
	
	@Test
	void getPathToFile_isEmpty_becauseNoPath() {
		XmlSourceResolver resolver = new XmlSourceResolver("");
		
		Optional<String> pathToXmlFile = resolver.getPathToFile();
		assertTrue(pathToXmlFile.isEmpty());
	}
	
	@Test
	void getPathToFile_isEmpty_becausePathInvalid() {
		XmlSourceResolver resolver = 
				new XmlSourceResolver("configReader.getSiteMapXmlLocation()");
		Optional<String> pathToXmlFile = resolver.getPathToFile();
		assertTrue(pathToXmlFile.isEmpty());
	}
	
	@Test
	void getPathToFile_isOk() {
		XmlSourceResolver resolver = 
				new XmlSourceResolver(configReader.getSiteMapXmlLocation());
		Optional<String> pathToXmlFile = resolver.getPathToFile();
		assertTrue(pathToXmlFile.get().length() > 0);
	}
}
