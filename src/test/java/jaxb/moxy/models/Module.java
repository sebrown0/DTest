package jaxb.moxy.models;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MODULE")
public class Module {
	@XmlAttribute
	private String name;

  @XmlElementWrapper(name="Menus")
  @XmlElement(name="Menu")
  private List<Menu> menus;
  
  public String getName() {
      return name;
  }

	public List<Menu> getMenus() {
		return menus;
	}
  
}
