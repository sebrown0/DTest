/**
 * 
 */
package site_mapper.jaxb.classes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="Type")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class MenuItemType {
	
	private String type;
//	@XmlElement(name="PanelTitle")
//	private String panelTitle;
//	@XmlElement(name="MenuTitle")
//	private String menuTitle;
//	@XmlElement(name="MenuParent")
//	private String menuParentName;

	public String getType() {
		return type;
	}
	
//	@XmlElementWrapper(name="Type")
	
	private JsPanelMenuItemType js;

	@XmlElements({
		@XmlElement(name="JsPanel", type=JsPanelMenuItemType.class)
	})
	public void setJs(JsPanelMenuItemType js) {
		this.js = js;
		System.out.println("->>>>>>>>>>>>>>>>>>>JS"); // TODO - remove or log
	}
	public JsPanelMenuItemType getJs() {
		return js;
	}
	@XmlAttribute(name="name")
	public void setType(String type) {
		this.type = type;
		System.out.println("->>>>>>>>>>>>>>>>>>>" + type ); // TODO - remove or log 	
	}
	
//	public String getPanelTitle() {
//		return panelTitle;
//	}
//	public String getMenuTitle() {
//		return menuTitle;
//	}
//	public String getMenuParentName() {
//		return menuParentName;
//	}

}
