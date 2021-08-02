/**
 * 
 */
package xml_reader;
import drivers.DriverGetter;
import drivers.GoogleDriver;
import reporting.strategy.ReportingStrategyFactory;
import reporting.strategy.ResultWriter;
import xml_file.XMLFile;

/**
 * @author SteveBrown
 *
 */
public class ConfigReader implements WebDriverNameGetter {
	private XMLFile xmlFile;
	private DriverGetter dg = null;
	private String webDriverName;
		
	public ConfigReader(String filePath) {
		super();		
		xmlFile = new XMLFile(filePath);
	}

	public DriverGetter getDriverGetter() {
		setDriverName();
		if(webDriverName.equalsIgnoreCase("google")) {
			dg = new GoogleDriver();
		}
		return dg;		
	}

	private void setDriverName() {
		webDriverName = xmlFile.getElement("WebDriver").getTextContent();
	}
	
	public ResultWriter getResultWriter() {
		return ReportingStrategyFactory.getResultWriter(xmlFile.getElement("Strategy").getTextContent());
	}
	
	@Override
	public String getTypeName() {
		setDriverName();
		return webDriverName;
	}
}
