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
	private Map<String, ChildMapper> modules = new HashMap<>();
	
	public void mapPayroll() {
		mapModules();
	}
	
	private void mapModules() {
		Mapper.mapTags(getModules(), "name").forEach(m -> {
			Element el = (Element) m;	  		
  		Module mod = new Module(el.getAttribute("name"));
  		modules.putIfAbsent(mod.getName(), mod);
  		mod.map(el);
		});
//		System.out.println("->"); // TODO - remove or log 	
	}
	
	private NodeList getModules() {		 
		return getRoot().getElementsByTagName("Module");	
	}	
	private Element getRoot() {
		return xmlFile.getElement("SiteMap");
	}

}
