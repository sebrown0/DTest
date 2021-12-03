package xml_reader.config_file;

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
public class ConfigFileContents {
	private ConfigFileContents() {	}
	
	public static String getDriverName(XMLFile xmlFile) {
		 return xmlFile.getElement("WebDriver").getTextContent();
	}
	
	public static String getResultStrategyName(XMLFile xmlFile) {
		 return xmlFile.getElement("Strategy").getTextContent();
	}
	
	public static String getLogDir(XMLFile xmlFile) {
		 return xmlFile.getElement("LogDir").getTextContent();
	}
	
	public static String getModule(XMLFile xmlFile) {
		 return xmlFile.getElement("Module").getTextContent();
	}
	
	public static String getCompany(XMLFile xmlFile) {
		 return xmlFile.getElement("Company").getTextContent();
	}
}
