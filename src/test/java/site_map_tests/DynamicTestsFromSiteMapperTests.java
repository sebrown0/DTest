/**
 * 
 */
package site_map_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;

import app.xml_content.DynamicTestMapper;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
class DynamicTestsFromSiteMapperTests {
	private static final String XML_SOURCE = 
			"./src/test/resources/site_map/site_map.xml";
	//  C:\Users\SteveBrown\eclipse-workspace\2021\DTest\src\test\resources
	@Test
	void getincludeElementsForTestFrom_DynamicTestMapper() {				
		Optional<app.xml_content.DynamicTestApp> content = 
				DynamicTestMapper.getDynamicTestContent(XML_SOURCE);
		LogManager.getLogger().info("dsdsdsdsds");
		List<String> elements = 
				content.get().getIncludeElementsForTest();
		
		assertTrue(elements.size()>0);
		assertEquals("button",elements.get(0));
	}

}
