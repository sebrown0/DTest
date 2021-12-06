/**
 * 
 */
package site_mapper;

import java.util.Map;

import org.w3c.dom.Element;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial
 * @since 1.0
 */
public class Panel implements ChildMapper {
	private String title;
	
	public Panel(Map<String, Panel> panels, String title) {
		this.title = title;
		panels.putIfAbsent(title, this);
		System.out.println(" Panel: " + title); // TODO - remove or log 	
	}

	@Override
	public void map(Element prnt) {
		mapButtons(prnt);
	}
	
	private void mapButtons(Element panel) {
		Mapper
			.mapTags(panel, "Button")
				.forEach(p -> {
//					Button btn = new Panel(panels, p.getAttribute("title"));
				}
			);
	}

	public String getTitle() {
		return title;
	}
	
}
