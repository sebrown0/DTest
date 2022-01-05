package site_mapper.jaxb.classes.pom;

import java.util.List;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.jaxb.classes.Module;

/** 
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Rename
 * @version 1.2
 * 	Add SiteMap.
 * @since 1.0
 * 
 * JAXB class representing the top level element root.
 * This will return a DynamicContainer with all the 
 * sub DynamicContainers found in the relevant XML doc.
 */
@XmlRootElement(name = "App")
public class PomMapperApp {	
	@XmlElementWrapper(name="Modules")
  @XmlElement(name="Module")
  private List<Module> modules;
	
	@XmlElement(name="SiteMap")
	private SiteMap siteMap;
	
	public void createPoms(PackageHierarchy ph, final String XML_SOURCE) {			
		siteMap.setXmlSource(XML_SOURCE);
		for (Module module : modules) {
			module.getModuleContainers(ph, siteMap);
		}			
	}
	
}