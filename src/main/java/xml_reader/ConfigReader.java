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
		return DriverFactory.getDriver(XMLFileContents.getDriverName(xmlFile));
	}

	@Override
	public ResultWriter getResultWriter() {
		return ReportingStrategyFactory.getResultWriter(XMLFileContents.getResultStrategyName(xmlFile));
	}
}
