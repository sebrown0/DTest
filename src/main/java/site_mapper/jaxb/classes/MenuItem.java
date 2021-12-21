/**
 * 
 */
package site_mapper.jaxb.classes;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import object_models.pages.homepage.HomePage;
import site_mapper.NodeClass;
import site_mapper.elements.ElementButton;
import site_mapper.elements.ElementLoader;

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
	@XmlElementWrapper(name="Elements")
	@XmlElement(name="ElementButton")
	private List<ElementButton> buttons;
	
	private List<DynamicTest> menuItemTests = new ArrayList<>();
	private String menuPackageName;
	private String moduleName;
	
	public DynamicContainer getDynamicContainer(HomePage hp, String menuPackageName, String moduleName) {
		this.menuPackageName = menuPackageName;
		this.moduleName = moduleName;
		getNodeTests(hp);
		return DynamicContainer.dynamicContainer(name, menuItemTests);
	}
	
	private void getNodeTests(HomePage hp){
		
//		hp.loadModule(moduleName);

		//SHOULD BE A MAP OF ELEMENTS!!!!
		buttons.forEach(b -> {
			menuItemTests.addAll(b.createTests(new ElementLoader(this, hp)).getTests());
		});	
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
