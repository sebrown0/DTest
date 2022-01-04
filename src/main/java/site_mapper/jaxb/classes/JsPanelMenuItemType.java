/**
 * 
 */
package site_mapper.jaxb.classes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="JsPanel")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class JsPanelMenuItemType extends MenuItemType {	
	private String panelTitle;	
	private String menuTitle;	
	private String menuParentName;
	
	public JsPanelMenuItemType() {
		System.out.println("new JsPanelMenuItemType"); // TODO - remove or log 	
	}
	
	@XmlElement(name="PanelTitle")
	public void setPanelTitle(String panelTitle) {
		this.panelTitle = panelTitle;
		System.out.println("-->" + panelTitle); // TODO - remove or log 	
	}
	@XmlElement(name="MenuTitle")
	public void setMenuTitle(String menuTitle) {
		this.menuTitle = menuTitle;
		System.out.println("-->" + menuTitle); // TODO - remove or log
	}
	@XmlElement(name="MenuParent")
	public void setMenuParentName(String menuParentName) {
		this.menuParentName = menuParentName;
		System.out.println("-->" + menuParentName); // TODO - remove or log
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
