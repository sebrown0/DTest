/**
 * 
 */
package dynamic_tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.DynamicTest;

import controls.ControlTest;
import object_models.pages.homepage.HomePage;
import site_mapper.elements.Element;
import site_mapper.elements.ElementCreation;
import site_mapper.elements.ElementLoader;
import site_mapper.elements.ElementTestButton;
import site_mapper.elements.IncludedElements;
import site_mapper.elements.TestElement;
import site_mapper.jaxb.menu_items.MenuItem;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class DynamicTestItem {
	private String menuPackageName;
	private String moduleName;	
	private Map<String, List<DynamicTest>> tests = new HashMap<>();
	private ControlTest controlTest;
	private List<Element> elements;
	private MenuItem item;
	
	public Map<String, List<DynamicTest>> getTests(
			MenuItem item, List<Element> elements, IncludedElements includedElements, 
				HomePage hp, String moduleName, String menuPackageName) {
	
		this.item = item;
		this.elements = elements;
		this.menuPackageName = menuPackageName;
		this.moduleName = moduleName;
		
		setElementsTests(includedElements, hp);
		return tests;
	}
	
	private void setElementsTests(IncludedElements includedElements, HomePage hp){		
		if(elements != null) {
			controlTest = ElementLoader.getControlTest(item, hp);		
			elements.forEach(e -> {							
				if(includedElements.isIncluded(e.getElementType())) {					
					addElementsTests(e, hp);					
				}								
			});	
		}		
	}

	private void addElementsTests(ElementCreation e, HomePage hp) {
		String elementType = e.getElementType();
		Optional<TestElement> test = null;
		 	
		switch (elementType) {
			case "button" -> { 
				test = Optional.of(
						new ElementTestButton(
								controlTest, e.getElementName(), e.getText(), e.getFafa())); 
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
