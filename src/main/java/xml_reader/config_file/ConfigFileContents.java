package xml_reader.config_file;

import xml_file.XMLFile;

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
}
