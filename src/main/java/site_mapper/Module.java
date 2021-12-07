/**
 * 
 */
package site_mapper;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class Module implements ChildMapper {
	private String name;
	private Map<String, Panel> panels = new HashMap<>();
	
	public Module(String name) {
		this.name = name;
		System.out.println("->" + name); // TODO - remove or log 	
	}

	@Override
	public void map(Element prnt) {
		mapPanels(prnt);		
	}

	private void mapPanels(Element panel) {
		Mapper
			.mapTags(panel, "Panel")
			.forEach(p -> { 
				new Panel(panels, p.getAttribute("title")).map(panel); 
				}
			);
	}

	public String getName() {
		return name;
	}

}
