package a;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Child", namespace="chld")
public class Child {
	@XmlElement(name="Child", namespace="chld")
	Child child;
	
	@XmlAttribute
	String type;

	public String getType() {
		return type;
	}
	
}
