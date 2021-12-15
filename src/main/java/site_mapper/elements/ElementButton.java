/**
 * 
 */
package site_mapper.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DynamicTest;
import org.w3c.dom.Element;

import site_mapper.ElementAdder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 *  Move loading and getting of control into super implementation.
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
		return this;
	}

	@Override
	public NodeElement createTests() {		
		createButtonFaFaCheck();
		createButtonTextCheck();		
		return this;
	}		
		
	private void createButtonFaFaCheck() {
		super.tests.add(
				DynamicTest.dynamicTest(
					"Is [" + methodName +"] button [FaFa] correct?", 
					() -> {							
						String faFaActual = super.getControlTest().getFaFaText(name);							
						assertEquals(fafa, faFaActual);
					}
				)
			);
	}
	private void createButtonTextCheck() {
		super.tests.add(
				DynamicTest.dynamicTest(
					"Is [" + methodName +"] button [text] correct?", 
					() -> {							
						String textActual = super.getControlTest().getControlText(name);							
						assertEquals(text, textActual);
					}
				)
			);
	}

	@Override
	public String toString() {
		return "NodeElement [type=" + type + ", methodName=" + methodName + ", name=" + name + ", text=" + text + ", fafa="
				+ fafa + ", response=" + response + ", by=" + by + ", locator=" + locator + "]";
	}

}
