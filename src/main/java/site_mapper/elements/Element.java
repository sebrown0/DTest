/**
 * 
 */
package site_mapper.elements;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="Element")
public class Element {
	@XmlAttribute(name="type")
	private String type;
	@XmlAttribute(name="name")
	private String name;
	@XmlAttribute(name="text")
	private String text;
	@XmlAttribute(name="by")
	private String by;
	@XmlAttribute(name="locator")
	private String locator;	
	@XmlAttribute(name="fafa")
	private String fafa;
	@XmlAttribute(name="hasFunction")
	private String hasFunction;
	
	public String getType() {
		return type;
	}
	public String getName() {
		return name;
	}
	public String getBy() {
		return by;
	}
	public String getLocator() {
		return locator;
	}
	public String getText() {
		return text;
	}
	public String getFafa() {
		return fafa;
	}
	public String getHasFunction() {
		return hasFunction;
	}
	
	@Override
	public String toString() {
		return "Element [type=" + type + ", name=" + name + ", by=" + by + ", locator=" + locator + ", text=" + text
				+ ", fafa=" + fafa + ", response=" + hasFunction + "]";
	}	
	
}
