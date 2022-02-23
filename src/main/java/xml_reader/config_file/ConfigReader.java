/**
 * 
 */
package xml_reader.config_file;
import org.openqa.selenium.WebDriver;

import factories.DriverFactory;
import reporting.strategy.ReportingStrategyFactory;
import reporting.strategy.ResultWriter;
import xml_file.XMLFile;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Add company and module.
 * @since 1.0
 *
 */
public class ConfigReader implements WebDriverGetter, ResultWritterGetter, AppParameters {
	private XMLFile xmlFile;
	private static final String CONFIG_FILE_PATH = "./config/config.xml";
	
	public ConfigReader() {
		xmlFile = new XMLFile(CONFIG_FILE_PATH);
	}	
	
	// Use if we want to specify the location of the config file.
	public ConfigReader(String filePath) {
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

	@Override
	public String getModule() {
		return ConfigFileContents.getModule(xmlFile);
	}

	@Override
	public String getCompany() {
		return ConfigFileContents.getCompany(xmlFile);
	}

	@Override
	public String getUri() {
		String res = ConfigFileContents.getUri(xmlFile); 
		return res;
	}
}
