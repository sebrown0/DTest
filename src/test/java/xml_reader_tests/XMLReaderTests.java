package xml_reader_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import factories.DriverFactory;
import xml_reader.ConfigReader;

class XMLReaderTests {

	@Test
	void usableWebDriver() {
		ConfigReader reader = new ConfigReader();
		WebDriver driver = reader.getDriverGetter().getDriver();
		assertNotNull(driver);
		driver.quit();
	}

	@Test
	void usableWebDriverFromFactory() {		
		WebDriver driver = DriverFactory.getDriver(new ConfigReader());
		assertNotNull(driver);
		driver.quit();
	}

}
