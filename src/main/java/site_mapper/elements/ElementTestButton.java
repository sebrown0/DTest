/**
 * 
 */
package site_mapper.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import controls.ControlTest;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 *  Move loading and getting of control into super implementation.
 * @version 1.2
 * 	Convert to JAXB class.
 * @version 1.3
 * 	Revert to standard class.
 * @version 1.4
 * 	Pass the container that has the control test.
 * @since 1.0
 */
public class ElementTestButton extends ElementTest {	
	private String text;
	private String fafa;	
	
	public ElementTestButton(ControlTest controlTest, String name, String text, String fafa) {
		super("button", name, controlTest);
		this.text = text;
		this.fafa = fafa;
	}
	
	@Override //TestElement
	public List<DynamicTest> createTests() {		
		createButtonFaFaCheck();
		createButtonTextCheck();
		return super.getTests();		
	}
	
	private void createButtonFaFaCheck() {
		super.getTests().add(
				DynamicTest.dynamicTest(
					"Is [" + super.getName() +"] button [FaFa] correct?", 
					() -> {							
						String faFaActual = super.getControlTest().getFaFaText(super.getName());
						assertEquals(fafa, faFaActual);
					}
				)
			);
	}
	private void createButtonTextCheck() {
		super.getTests().add(
				DynamicTest.dynamicTest(
					"Is [" + super.getName() +"] button [text] correct?", 
					() -> {							
						String textActual = super.getControlTest().getControlText(super.getName());
						assertEquals(text, textActual);
					}
				)
			);
	}
	
}