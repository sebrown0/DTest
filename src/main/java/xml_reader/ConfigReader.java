/**
 * 
 */
package xml_reader;
import drivers.DriverGetter;
import drivers.GoogleDriver;
import xml_file.XMLFile;

/**
 * @author SteveBrown
 *
 */
public class ConfigReader implements WebDriverNameGetter {
	private XMLFile xmlFile = new XMLFile("./src/main/java/xml_files/config.xml");
	private DriverGetter dg = null;
	private String webDriverName;
	
	public DriverGetter getDriverGetter() {
		setDriverName();
		if(webDriverName.equalsIgnoreCase("google")) {
			dg = new GoogleDriver();
		}
		return dg;		
	}

	private void setDriverName() {
		webDriverName = xmlFile.getElement("web_driver").getTextContent();
	}
	
	@Override
	public String getTypeName() {
		setDriverName();
		return webDriverName;
	}
}
