/**
 * 
 */
package site_mapper.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;

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
@XmlRootElement(name="ElementTextOut")
public class ElementTextOut implements TestElement{
	@XmlAttribute(name="name")
	private String name;	
	@XmlAttribute(name="by")
	private String by;
	@XmlAttribute(name="locator")
	private String locator;
	@XmlAttribute(name="textExpected")
	private String textExpected;
	
	private List<DynamicTest> tests = new ArrayList<>();
	private ElementLoader loader;	

	@Override //TestElement 
	public ElementTextOut createTests(ElementLoader loader) {
		this.loader = loader;
		createTextCheck();
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
		return "text_out";
	}

	private void createTextCheck() {
		tests.add(
				DynamicTest.dynamicTest(
					"Is [" + name +"] [text] correct?", 
					() -> {							
						String textActual = loader.getControlTest().getControlText(name);
						assertEquals(textExpected, textActual);
					}
				)
			);
	}


}
