package jaxb.moxy.models;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Menu")
public class Menu {
	@XmlAttribute
	private String name;
	
	@XmlElementWrapper(name="Nodes")
  @XmlElement(name="Node")
  private List<Node> nodes;
	@XmlElement(namespace = "./src/test/resources/defs")
	private Page page;

	public List<Node> getNodes() {
		return nodes;
	}

	public Page getPage() {
		return page;
	}
	
}
