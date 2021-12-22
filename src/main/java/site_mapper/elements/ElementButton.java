/**
 * 
 */
package site_mapper.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 *  Move loading and getting of control into super implementation.
 * @version 1.2
 * 	Convert to JAXB class.
 * @since 1.0
 */
public class ElementButton implements TestElement{	
	private String name;
	private String text;
	private String fafa;
	private List<DynamicTest> tests = new ArrayList<>();
	private ElementLoader loader;	

	public ElementButton(String name, String text, String fafa) {
		this.name = name;
		this.text = text;
		this.fafa = fafa;
	}

	@Override //TestElement
	public ElementButton createTests(ElementLoader loader) {
		this.loader = loader;
		createButtonFaFaCheck();
		createButtonTextCheck();
		return this;		
	}
	@Override //TestElement
	public List<DynamicTest> getTests() {
		return tests;
	}
	@Override //TestElement
	public String getName() {
		return name;
	}
	@Override //TestElement
	public String getType() {
		return "button";
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

	
}

//@XmlRootElement(name="ElementButton")
//public class ElementButton implements TestElement{
//	@XmlAttribute
//	private String name;	
//	@XmlAttribute
//	private String by;
//	@XmlAttribute
//	private String locator;
//	@XmlAttribute
//	private String text;
//	@XmlAttribute
//	private String fafa;
//	@XmlAttribute
//	private String response;	
//
//	private Collection<DynamicTest> tests = new ArrayList<>();
//	private ElementLoader loader;	
//
//	@Override //TestElement
//	public ElementButton createTests(ElementLoader loader) {
//		this.loader = loader;
//		createButtonFaFaCheck();
//		createButtonTextCheck();
//		return this;		
//	}
//	@Override //TestElement
//	public Collection<DynamicTest> getTests() {
//		return tests;
//	}
//
//	private void createButtonFaFaCheck() {
//		tests.add(
//				DynamicTest.dynamicTest(
//					"Is [" + name +"] button [FaFa] correct?", 
//					() -> {							
//						String faFaActual = loader.getControlTest().getFaFaText(name);
//						assertEquals(fafa, faFaActual);
//					}
//				)
//			);
//	}
//	private void createButtonTextCheck() {
//		tests.add(
//				DynamicTest.dynamicTest(
//					"Is [" + name +"] button [text] correct?", 
//					() -> {							
//						String textActual = loader.getControlTest().getControlText(name);
//						assertEquals(text, textActual);
//					}
//				)
//			);
//	}
//
//	@Override
//	public String toString() {
//		return "NodeElement [name=" + name + ", text=" + text + ", fafa="
//				+ fafa + ", response=" + response + ", by=" + by + ", locator=" + locator + "]";
//	}
//
//}
