/**
 * 
 */
package xml_reader;
import org.openqa.selenium.WebDriver;

import factories.DriverFactory;
import reporting.strategy.ReportingStrategyFactory;
import reporting.strategy.ResultWriter;
import xml_file.XMLFile;

/**
 * @author SteveBrown
 *
 */
public class ConfigReader implements WebDriverGetter, ResultWritterGetter {
	private XMLFile xmlFile;
		
	public ConfigReader(String filePath) {
		super();		
		xmlFile = new XMLFile(filePath);
	}	

	@Override
	public WebDriver getDriver() {
		return DriverFactory.getDriver(ConfigFileContents.getDriverName(xmlFile));
	}

	@Override
	public ResultWriter getResultWriter(String testSuiteName) {		
		return ReportingStrategyFactory.getResultWriter(
				ConfigFileContents.getResultStrategyName(xmlFile), 
				ConfigFileContents.getLogDir(xmlFile), 
				testSuiteName);
	}
}
