package jaxb.moxy.models;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

@XmlRootElement(name = "Element")
@XmlType(propOrder = { "type name text fafa response by locator" })
public class NodeElement {
	@XmlAttribute
	private String type;
	@XmlAttribute
	private String name;
	@XmlAttribute
	private String text;
	@XmlAttribute
	private String fafa;
	@XmlAttribute
	private String response;
	@XmlAttribute
	private String by;
	@XmlAttribute
	private String locator;
	
	public String getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public String getText() {
		return text;
	}
	public String getFafa() {
		return fafa;
	}
	public String getResponse() {
		return response;
	}
	public String getBy() {
		return by;
	}
	public String getLocator() {
		return locator;
	}
	
	@Override
	public String toString() {
		return "NodeElement [type=" + type + ", name=" + name + ", text=" + text + ", fafa=" + fafa + ", response="
				+ response + ", by=" + by + ", locator=" + locator + "]";
	}
		
}
