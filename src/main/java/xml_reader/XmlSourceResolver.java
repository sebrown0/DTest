/**
 * 
 */
package xml_reader;

import java.io.File;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * Check that the file given in config.xml exists
 * and is valid.
 */
public class XmlSourceResolver {
	private String siteMapXmlLocation;
	
	public XmlSourceResolver(String siteMapXmlLocation) {
		this.siteMapXmlLocation = siteMapXmlLocation;
	}

	public Optional<String> getPathToFile() {
		Optional<String> res = Optional.empty();
		if(siteMapXmlLocation == null || siteMapXmlLocation.length() <= 0) {
			reportError();
		}else if(fileExists()) {
			res = Optional.of(siteMapXmlLocation);
		}
		return res;
	}
	
	private void reportError() {
		String errMsg = String.format("Path [%s] to SiteMap XML is invalid", siteMapXmlLocation);
		LogManager.getLogger().error(errMsg);
	}

	private boolean fileExists() {
		return new File(siteMapXmlLocation).isFile();
	}
}
