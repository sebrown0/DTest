/**
 * 
 */
package site_mapper.jaxb.classes;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
@XmlRootElement(name="Type")
public class MenuItemType {
	@XmlAttribute(name="name")
	private String type;
	@XmlElement(name="PanelTitle")
	private String panelTitle;
	@XmlElement(name="MenuTitle")
	private String menuTitle;
	@XmlElement(name="MenuParent")
	private String menuParentName;

	public String getType() {
		return type;
	}
	public String getPanelTitle() {
		return panelTitle;
	}
	public String getMenuTitle() {
		return menuTitle;
	}
	public String getMenuParentName() {
		return menuParentName;
	}

}
