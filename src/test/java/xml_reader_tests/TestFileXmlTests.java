package xml_reader_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import app.test_reader.IncludedTestsReader;
import app.test_reader.TestClass;
import app.test_reader.TestPackage;
import app.test_reader.TestClass.TestPriority;
import providers.XMLFileProvider;

/**
 * @author Steve Brown
 *
 * Test IncludedTestsReader which gets the elements of unit_test.xml.
 *  
 */
class TestFileXmlTests {
	private static IncludedTestsReader reader;
	
	@BeforeAll
	static void setUp() {
		reader = new IncludedTestsReader(XMLFileProvider.INCLUDE_TESTS_FILE_PATH + "/" + "unit_test.xml");	
	}
	
	@Test
	void testName_In_Package() {
		TestPackage p = reader.getPackage();
		assertTrue(p.getPackageName().equalsIgnoreCase("unit_test"));
	}

	@Test
	void testApplicable_In_Package() {
		TestPackage p = reader.getPackage();
		assertTrue(p.getApplicable().equalsIgnoreCase("payroll"));
	}
	
	@Test
	void testClasses_In_Package() {
		List<TestClass> testClasses = reader.getPackage().getTestClasses();
		TestClass class1 = testClasses.get(0); 
		assertTrue(class1.getName().equalsIgnoreCase("class_1"));
		assertTrue(class1.getPriority() == TestPriority.HIGH);
	}
}
