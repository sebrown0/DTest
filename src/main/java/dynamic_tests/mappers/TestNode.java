/**
 * 
 */
package dynamic_tests.mappers;

import java.util.ArrayList;
import java.util.List;

import site_mapper.jaxb.pom.Element;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * TODO
 * ----
 * SPLIT INTO CHILD NODE AND PARENT NODE.
 */
public class TestNode {
	private TestNode parent;
	private String name;
	private List<Element> elements;
	private List<String> prntNames;
	
	// Root with no elements.
	public TestNode(String name) {		
		this.name = name;
		addParentName();
	}
	// Root with elements.
	public TestNode(String name, List<Element> elements) {		
		this.name = name;
		this.elements = elements;
		addParentName();
	}
	// Child with no elements.
	public TestNode(String name, TestNode parent) {
		this.parent = parent;
		this.name = name;
		
		addParentName();
	}
	// Child with elements.
	public TestNode(String name, TestNode parent, List<Element> elements) {
		this.parent = parent;
		this.name = name;
		this.elements = elements;
		
		addParentName();
	}
	
	private void addParentName() {
		prntNames = new ArrayList<>();
		if(parent != null) {
			List<String> existing = parent.getPrntNames();
			if(existing != null) {			
				addParentNames(existing);
			}
		}
		prntNames.add(name);
	}

	public void addParentNames(List<String> existingPrntNames) {
		if(existingPrntNames != null) {
			existingPrntNames.forEach(n -> prntNames.add(n));
		}
	}
	public String getName() {
		return name;
	}
	public List<Element> getElements() {
		return elements;
	}
	public TestNode getParent() {
		return parent;
	}
		
	@Override
	public String toString() {
		String res = "";
		res = String.format("%sName=%s, ", getParentName(), name);
		return res;
	}
	
	private String getParentName() {
		return (parent != null) ? "Parent=" + parent.getName() + ", " : "";
	}
	public List<String> getPrntNames() {		
		return prntNames;
	}
}