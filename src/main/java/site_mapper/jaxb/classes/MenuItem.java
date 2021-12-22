/**
 * 
 */
package site_mapper.jaxb.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import object_models.pages.homepage.HomePage;
import site_mapper.NodeClass;
import site_mapper.elements.Element;
import site_mapper.elements.ElementButton;
import site_mapper.elements.ElementLoader;
import site_mapper.elements.TestElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Add moduleName.
 * @since 1.0
 */
@XmlRootElement(name="MenuItem")
public class MenuItem implements NodeClass {
	@XmlAttribute(name="name")
	private String name;
	@XmlAttribute(name="package")
	private String packageName;
	@XmlAttribute(name="class")
	private String className;
//	@XmlElementWrapper(name="Elements")
//	@XmlElement(name="ElementButton")
//	private List<TestElement> buttons;
//	@XmlElementWrapper(name="Elements")
//	@XmlElement(name="ElementTextOut")
//	private List<TestElement> textOuts;
	
	@XmlElementWrapper(name="Elements")
	@XmlElement(name="Element")
	private List<Element> elements;	
	
	private List<DynamicTest> menuItemTests = new ArrayList<>();	
	private String menuPackageName;
	private String moduleName;
	
	private Map<String, List<DynamicTest>> tests = new HashMap<>();
	
	public Map<String, List<DynamicTest>> getTests(HomePage hp, String moduleName, String menuPackageName) {
		this.menuPackageName = menuPackageName;
		this.moduleName = moduleName;
		getNodeTests(hp);
		return tests;
//		return DynamicContainer.dynamicContainer(name, menuItemTests);
//		return DynamicContainer.dynamicContainer(name, tests.entrySet().stream());
	}
//	public DynamicContainer getDynamicContainer(HomePage hp, String menuPackageName, String moduleName) {
//		this.menuPackageName = menuPackageName;
//		this.moduleName = moduleName;
//		getNodeTests(hp);
//		return DynamicContainer.dynamicContainer(name, menuItemTests);
////		return DynamicContainer.dynamicContainer(name, tests.entrySet().stream());
//	}
	
	/*
	 * Which elements to test?
	 */
	private void getNodeTests(HomePage hp){
		
		if(elements != null) {
			elements.forEach(e -> {
				addElementTest(e, hp);
				
				System.out.println("->" + e.toString()); // TODO - remove or log 	
//				menuItemTests.addAll(e.createTests(new ElementLoader(this, hp)).getTests());
			});	
		}
		
	}

	private void addElementTest(Element e, HomePage hp) {
		String elementType = e.getType();
		Optional<TestElement> test = null;
		switch (elementType) {
			case "button" -> { test = Optional.of(new ElementButton(e.getName(), e.getText(), e.getFafa())); }
			default -> { throw new IllegalArgumentException("Unexpected value: " + elementType); }
		}		
		test.ifPresent(t -> { tests.put(getKey(t), t.createTests(new ElementLoader(this, hp)).getTests()); });
	}	
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
