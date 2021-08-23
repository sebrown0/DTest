package xml_reader_tests;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import providers.XMLFileProvider;
import xml_reader.ConfigReader;

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
