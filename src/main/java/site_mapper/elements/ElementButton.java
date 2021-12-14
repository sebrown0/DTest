/**
 * 
 */
package site_mapper.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.DynamicTest;
import org.w3c.dom.Element;

import controls.ControlTest;
import site_mapper.ElementAdder;
import site_mapper.NodeClass;
import site_mapper.SiteMapElement;

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
//		System.out.println("  " + this.toString()); // TODO - remove or log
		return this;
	}

	@Override
	public NodeElement createTests() {
		NodeClass nodeClass = super.getNodeClass();
		Optional<SiteMapElement> container = nodeClass.getNodeAsSiteMapElement();
		container.ifPresent(c -> {
			if(c instanceof ControlTest) {
				ControlTest controlTest = (ControlTest) c;
				
				super.tests.add(DynamicTest.dynamicTest("Is [" + methodName +"] button [FaFa] correct?", () -> {
					String faFaActual = controlTest.getFaFaText(name);							
					assertEquals(fafa, faFaActual); 
				}));
				
				super.tests.add(DynamicTest.dynamicTest("Is [" + methodName +"] button [text] correct?", () -> { 
					String textActual = controlTest.getControlText(name);							
					assertEquals(text, textActual); 
				}));
				
			}else {
				//TODO throw error?
				//Test ignored?
			}
						
		});
		return this;
	}		
	
	@Override
	public String toString() {
		return "NodeElement [type=" + type + ", methodName=" + methodName + ", name=" + name + ", text=" + text + ", fafa="
				+ fafa + ", response=" + response + ", by=" + by + ", locator=" + locator + "]";
	}

}
