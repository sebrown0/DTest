package jaxb.moxy.models;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Node")
//@XmlType(propOrder = { "type", "obj", "title" })
public class Node {
	@XmlAttribute
	private String type;
	@XmlAttribute
	private String obj;
	@XmlAttribute
	private String title;
	
	@XmlElement(name = "Element")
	private List<NodeElement> elements;
	
	public List<NodeElement> getElements() {
		return elements;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getObj() {
		return obj;
	}
	public void setObj(String obj) {
		this.obj = obj;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}
