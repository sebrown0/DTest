package a;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Parent", namespace="tns")
public class Parent {
	public Child getChild() {
		return child;
	}

	public String getType() {
		return type;
	}

	@XmlElement(name="Child", namespace="prnt")
	Child child;
	
	@XmlAttribute
	String type;
}
