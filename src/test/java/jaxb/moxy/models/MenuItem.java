/**
 * 
 */
package jaxb.moxy.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElements;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.elements.ElementButton;
import site_mapper.elements.NodeElement;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
@XmlRootElement(name="MenuItem")
public class MenuItem {
	@XmlAttribute(name="name")
	private String name;
	
//	@XmlElement(name="ElementButton")
	
	@XmlElements({
		@XmlElement(name="ElementButton")
	})
	private List<NodeElement> elements;
	
	public void runMenuItemTests() {
		if(elements != null) {
			elements.forEach(e -> {
				ElementButton btn = (ElementButton) e;
				System.out.println("   Running element test: " + btn.toString()); // TODO - remove or log 	
			});	
		}	else {
			System.out.println("CVVVVVVVVVVVVV"); // TODO - remove or log 	
		}
	}
	
	private DynamicNode getTests() {		
		return DynamicContainer.dynamicContainer(name, getNodeTests());
	}

	private Collection<DynamicTest> getNodeTests(){
		Collection<DynamicTest> tests = new ArrayList<>();		
		elements.forEach(e -> { 			
			tests.addAll(e.createTests().getTests());			
		});
		return tests;
	}

	public String getName() {
		return name;
	}

	public List<NodeElement> getElements() {
		return elements;
	}
	
	/*
	 * 
	 */
}
