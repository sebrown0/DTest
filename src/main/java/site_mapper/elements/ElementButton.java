/**
 * 
 */
package site_mapper.elements;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DynamicTest;
import org.w3c.dom.Element;

import site_mapper.ElementAdder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ElementButton extends NodeElement{
	private String methodName;
	private String text;
	private String fafa;
	private String response;
		
	public ElementButton(Element element, ElementAdder elementAdder) {
		super(element, elementAdder);		
	}
	
	public NodeElement mapAttributes() {		
		methodName = element.getAttribute("method");		
		text = element.getAttribute("text");
		fafa = element.getAttribute("fafa");
		response = element.getAttribute("response");		
		
		System.out.println("  " + this.toString()); // TODO - remove or log
		return this;
	}

	@Override
	public NodeElement createTests() {
		super.tests.add(DynamicTest.dynamicTest("display name 1", () -> assertTrue(true)));
		super.tests.add(DynamicTest.dynamicTest("display name 2", () -> assertTrue(true)));
		return this;
	}		
	
	@Override
	public String toString() {
		return "NodeElement [type=" + type + ", methodName=" + methodName + ", name=" + name + ", text=" + text + ", fafa="
				+ fafa + ", response=" + response + ", by=" + by + ", locator=" + locator + "]";
	}


}
