/**
 * 
 */
package site_mapper.elements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.DynamicTest;
import org.w3c.dom.Element;

import controls.ControlTest;
import site_mapper.ElementAdder;
import site_mapper.ElementTest;
import site_mapper.ElementType;
import site_mapper.MapKey;
import site_mapper.NodeClass;
import site_mapper.SiteMapElement;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial
 * @version 1.1
 *  Loading and get control.
 * @since 1.0
 */
public abstract class NodeElement implements MapKey, ElementType, ElementTest {
	protected Element element;
	protected Collection<DynamicTest> tests = new ArrayList<DynamicTest>();
	protected String type;	
	protected String name;	
	protected String by;
	protected String locator;	
	
	private ElementAdder elementAdder;	
	private Optional<SiteMapElement> container;
		
	public NodeElement(Element element, ElementAdder elementAdder) {
		this.element = element;
		this.elementAdder = elementAdder;
		
		mapCommonAttributes();
	}

	public abstract NodeElement mapAttributes();
	public abstract NodeElement createTests();
	
	private void mapCommonAttributes() {
		type = element.getAttribute("type");
		name = element.getAttribute("name");
		by = element.getAttribute("by");
		locator = element.getAttribute("locator");
	}

	public void addToNode() {
		elementAdder.addElement(this);
	}
	
	/*
	 * SHOULD BE LOADING CONTAINER ONCE PER NODE
	 */
	protected ControlTest getControlTest() {
		loadContainerIfNull();		
		if(container.isPresent()) {
			SiteMapElement e = container.get();
			if(e instanceof ControlTest) {
				return (ControlTest) e;
			}
		}else {
			// TODO - THROW ERROR??
		}
		return null;
	}
	
	private void loadContainerIfNull() {
		if(container == null) {
			loadContainer();
		}
	}	
	public void loadContainer() {
		container = getNodeClass().getNodeAsSiteMapElement();
	}
	
	@Override //MapKey
	public String getKey() {
		return name;
	}

	@Override //ElementTest
	public Collection<DynamicTest> getTests() {
		return tests;
	}

	@Override //TestType
	public String getType() {
		return type;
	}		
	
	protected NodeClass getNodeClass() {
		return (NodeClass) elementAdder;
	}

}
