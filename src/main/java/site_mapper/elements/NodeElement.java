/**
 * 
 */
package site_mapper.elements;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.DynamicTest;
import org.w3c.dom.Element;

import site_mapper.ElementAdder;
import site_mapper.ElementTest;
import site_mapper.ElementType;
import site_mapper.MapKey;
import site_mapper.NodeClass;

/**
 * @author SteveBrown
 * @version 1.0
 *  Initial
 * @since 1.0
 */
public abstract class NodeElement implements MapKey, ElementType, ElementTest {
	protected Element element;
	protected String type;	
	protected String name;	
	protected String by;
	protected String locator;
	protected Collection<DynamicTest> tests = new ArrayList<DynamicTest>();
	
	private ElementAdder elementAdder;	
		
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
