/**
 * 
 */
package site_mapper.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;
import org.w3c.dom.Element;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.ElementAdder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 *  Move loading and getting of control into super implementation.
 * @since 1.0
 */
@XmlRootElement(name="ElementButton")
public class ElementButton {
	@XmlAttribute
	private String name;	
	@XmlAttribute
	private String by;
	@XmlAttribute
	private String locator;
	@XmlAttribute
	private String text;
	@XmlAttribute
	private String fafa;
	@XmlAttribute
	private String response;	

	private Collection<DynamicTest> tests = new ArrayList<>();
//	private Collection<DynamicContainer> containers;
	private ElementLoader loader;
	
//	public ElementButton createTests(ElementLoader loader, Collection<DynamicTest> tests) {
//		this.loader = loader;
//		this.tests = tests;
//		createButtonFaFaCheck();
//		createButtonTextCheck();		
//		return this;
//	}		

	public ElementButton createTests(ElementLoader loader) {
		this.loader = loader;
		createButtonFaFaCheck();
		createButtonTextCheck();
		return this;		
	}
	
	public Collection<DynamicTest> getTests() {
		return tests;
	}

	private void createButtonFaFaCheck() {
		tests.add(
				DynamicTest.dynamicTest(
					"Is [" + name +"] button [FaFa] correct?", 
					() -> {							
						String faFaActual = loader.getControlTest().getFaFaText(name);
						assertEquals(fafa, faFaActual);
					}
				)
			);
	}
	private void createButtonTextCheck() {
		tests.add(
				DynamicTest.dynamicTest(
					"Is [" + name +"] button [text] correct?", 
					() -> {							
						String textActual = loader.getControlTest().getControlText(name);
						assertEquals(text, textActual);
					}
				)
			);
	}

	@Override
	public String toString() {
		return "NodeElement [name=" + name + ", text=" + text + ", fafa="
				+ fafa + ", response=" + response + ", by=" + by + ", locator=" + locator + "]";
	}


}
