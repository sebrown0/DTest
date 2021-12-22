/**
 * 
 */
package site_mapper.jaxb.classes;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.DynamicTest;

import controls.ControlTest;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import object_models.pages.homepage.HomePage;
import site_mapper.elements.Element;
import site_mapper.elements.ElementClass;
import site_mapper.elements.ElementLoader;
import site_mapper.elements.ElementTestButton;
import site_mapper.elements.IncludedElements;
import site_mapper.elements.TestElement;
import site_mapper.method_getter.MethodGetter;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Add moduleName.
 * @version 1.2
 * 	Put all the tests into a Map.
 * @since 1.0
 */
@XmlRootElement(name="MenuItem")
public class MenuItem implements ElementClass {
	@XmlAttribute(name="name")
	private String name;
	@XmlAttribute(name="package")
	private String packageName;
	@XmlAttribute(name="class")
	private String className;	
	@XmlElementWrapper(name="Elements")
	@XmlElement(name="Element")
	private List<Element> elements;	
	
	private String menuPackageName;
	private String moduleName;	
	private Map<String, List<DynamicTest>> tests = new HashMap<>();
	private ControlTest controlTest;
	
	public Map<String, List<DynamicTest>> getTests(
			IncludedElements includedElements, HomePage hp, String moduleName, String menuPackageName) {
		
		this.menuPackageName = menuPackageName;
		this.moduleName = moduleName;
		
		setElementsTests(includedElements, hp);
		return tests;
	}
	
	private void setElementsTests(IncludedElements includedElements, HomePage hp){		
		if(elements != null) {
			controlTest = ElementLoader.getControlTest(this, hp);
						
			elements.forEach(e -> {
				if(includedElements.isIncluded(e.getType())) {
					
					addElementsTests(e, hp);
					
				}								
			});	
		}		
	}

	private void addElementsTests(Element e, HomePage hp) {
		String elementType = e.getType();
		Optional<TestElement> test = null;
		 	
		switch (elementType) {
			case "button" -> { 
				test = Optional.of(
						new ElementTestButton(
								controlTest, e.getName(), e.getText(), e.getFafa())); 
			}
			default -> { 
				throw new IllegalArgumentException("Unexpected value: " + elementType); 
			}
		}		
		test.ifPresent(t -> {	tests.put(getKey(t), t.createTests()); });
	}	
	
//	private void getTestMethods(MethodGetter methodGetter) {
//		methodGetter.getAllTestMethods().forEach(m -> System.out.println("->" + m));
//	}
	
	private String getKey(TestElement e) {
		return e.getType() + "." + e.getName();
	}
	
	@Override //NodeClass
	public String getClassName() {
		return className;
	}
	@Override //NodeClass
	public String getPackage() {
		return packageName;
	}
	@Override //NodeClass
	public String getParentPackage() {
		return menuPackageName;
	}
	@Override //NodeClass
	public String getModuleName() {
		return moduleName;
	}
	
	public String getName() {
		return name;
	}

}
