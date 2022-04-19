package xml_reader_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import library.dto.Employee;
import library.enums.EmployeeTitle;
import library.providers.employee.EmployeeProvider;
import providers.XMLFileProvider;
import providers.employee.EmployeeFromXml_Old;
import xml_reader.config_file.ConfigReader;

/** 
 * @author SteveBrown
 * @version 1.0
 *  Initial 	
 * @since 1.0
 * 
 * Read & validate data from various XML files.
 */
class XMLReaderTests {
	private static ConfigReader reader;
	
	@BeforeAll
	static void setup() {
		reader = new ConfigReader(XMLFileProvider.TEST_CONFIG_FILE_PATH);
	}
	
	@Test
	void usableWebDriver() {	
		WebDriver driver = reader.getDriver();
		assertNotNull(driver);
		driver.quit();
	}

	@Test
	void usableWebDriverFromFactory() {		
		WebDriver driver = reader.getDriver();
		assertNotNull(driver);
		driver.quit();
	}
	
	@Test
	void getEmployee_withRequiredFields() {
		EmployeeProvider empXml = new EmployeeFromXml_Old();
		Employee emp = empXml.getEmployeeWithRequiredFields("VALID_WITH_REQUIRED_ONLY");
		assertEquals("HS_2345", emp.getEmpCode());
		assertEquals("123456", emp.getNiNumber());
	}
	
	@Test
	void getEmployee_withAllFields() {
		EmployeeProvider empXml = new EmployeeFromXml_Old();
		Employee emp = empXml.getEmployeeWithAllFields("VALID_WITH_ALL_FIELDS");
		assertEquals(EmployeeTitle.MRS, emp.getEmployeeTitle());
	}
	
//	@Test
//	void reportStrategy() throws IncorrectTestStatusException {		
//		ResultWriter writer = reader.getResultWriter();
//		TestCaseData data =  TestResultDataFactory
//				.buildTestResultWith()
//				.setBelongsToTestRunId("R1")
//				.setTestId("T1");
//		
//		TestResult result = new TestResult(new ResultPassed(), null);
//		
//		
//		writer.writeResult(result);
//		assertEquals(TestStatusValues.PASSED(), result.getResult().getTestStatus().getStatus());
//	}

}
