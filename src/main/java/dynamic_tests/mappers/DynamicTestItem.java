/**
 * 
 */
package dynamic_tests.mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.DynamicTest;

import dynamic_tests.elements.ElementTestButton;
import dynamic_tests.elements.IncludedElements;
import dynamic_tests.elements.TestElement;
import object_models.pages.homepage.CoreData;
import object_models.pages.homepage.HomePage;
import site_mapper.elements.ElementCreation;
import site_mapper.jaxb.menu_items.MenuItem;
import site_mapper.jaxb.pom.Element;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class DynamicTestItem {
	private Map<String, List<DynamicTest>> tests = new HashMap<>();
	private List<Element> elements;
	private MenuItem item;
	private CoreData coreData;
	
	public Map<String, List<DynamicTest>> getTests(
			MenuItem item, IncludedElements includedElements,	HomePage hp) {
	
		this.coreData = hp;
		this.item = item;
		this.elements = item.getElements();
		
		setElementsTests(includedElements, hp);
		return tests;
	}
	
	private void setElementsTests(IncludedElements includedElements, HomePage hp){		
		if(elements != null) {			
			elements.forEach(e -> {							
				if(includedElements.isIncluded(e.getElementType())) {					
					addElementsTests(e, hp);					
				}								
			});	
		}		
	}

	/**
	 * @param e: The Element from XML.
	 * @param hp: Homepage.
	 * 
	 * Get the type of test and add it to the map of tests.
	 */
	private void addElementsTests(ElementCreation e, HomePage hp) {
		String elementType = e.getElementType();
		Optional<TestElement> test = null;
		 	
		switch (elementType) {
			case "button" -> { 
				test = 
						Optional.of(new ElementTestButton(hp, coreData, item, e)); 
			}
			default -> { 
				throw new IllegalArgumentException("Unexpected value: " + elementType); 
			}
		}		
		test.ifPresent(t -> {	tests.put(getKey(t), t.createTests()); });
	}	
		
	private String getKey(TestElement e) {
		return e.getType() + "." + e.getName();
	}
}