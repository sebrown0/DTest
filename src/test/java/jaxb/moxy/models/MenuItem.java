/**
 * 
 */
package jaxb.moxy.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import object_models.pages.homepage.HomePage;
import site_mapper.NodeClass;
import site_mapper.SiteMapElement;
import site_mapper.elements.ElementButton;
import site_mapper.elements.ElementLoader;

/**
 * @author SteveBrown
 * @version 1.0
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
	
	public DynamicContainer getDynamicContainer(HomePage hp, String menuPackageName) {
		this.menuPackageName = menuPackageName;
		getNodeTests(hp);
		return DynamicContainer.dynamicContainer(name, menuItemTests);
	}
	
	private void getNodeTests(HomePage hp){

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
	public Optional<SiteMapElement> getNodeAsSiteMapElement() {
		// TODO Auto-generated method stub
		System.out.println("[MenuItem.getNodeAsSiteMapElement] *********** NOT IMPLEMENTED ***********"); // TODO - remove or log 	
		return null;
	}
	@Override //NodeClass
	public String getParentPackage() {
		return menuPackageName;
	}
	public String getName() {
		return name;
	}

}
