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
public class Button {
	private Map<String, Button> buttons;
	private Element element;
	private String name;
	private String text;
	private String by;
	private String locator;
	
	public Button(Map<String, Button> buttons, Element element) {
		this.buttons = buttons;
		this.element = element;
		mapButton();
	}
	
	private void mapButton() {
		name = element.getAttribute("name");
		text = element.getAttribute("text");
		by = element.getAttribute("by");
		locator = element.getAttribute("locator");
		System.out.println("   Button ->" + this); // TODO - remove or log 	
		
		buttons.putIfAbsent(name, this);
		
	}

	@Override
	public String toString() {
		return "Button [name=" + name + ", text=" + text + ", by=" + by + ", locator=" + locator + "]";
	}
	
}
