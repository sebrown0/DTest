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
 *  Initial
 * @since 1.0
 */
public class Panel implements ChildMapper {
	private String title;
	private Map<String, Button> buttons = new HashMap<>();
	
	public Panel(Map<String, Panel> panels, String title) {
		this.title = title;
		panels.putIfAbsent(title, this);
		System.out.println(" Panel: " + title); // TODO - remove or log 	
		
	}

	@Override
	public void map(Element panel) {
		mapButtons(panel);
	}
	
	private void mapButtons(Element panel) {
		Mapper
			.mapTags(panel, "Button")
				.forEach(b -> {
					new Button(buttons, b);
				}
			);
	}

	public String getTitle() {
		return title;
	}
	
}
