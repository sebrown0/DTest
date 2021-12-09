/**
 * 
 */
package site_mapper;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import providers.XMLFileProvider;
import xml_file.XMLFile;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class SiteMapper {
	private XMLFile xmlFile = new XMLFile(XMLFileProvider.SITE_MAP_FILE_PATH);
	private Map<String, Module> modules = new HashMap<>();
		
	public void mapModules() {
		Mapper.mapTags(getModules(), "name").forEach(m -> {
			Element el = (Element) m;	  		
  		Module mod = new Module(el.getAttribute("name"));
  		mod.mapNodes(getNodes(m));
  		modules.putIfAbsent(mod.getName(), mod);
		});
	}
	
	private NodeList getModules() {		 
		return getRoot().getElementsByTagName("Module");	
	}	
	private Element getRoot() {
		return xmlFile.getElement("SiteMap");
	}
	private NodeList getNodes(Element module) {
		return module.getElementsByTagName("Node");
	}

	public Map<String, Module> getModuleMap(){
		return modules;
	}
}
