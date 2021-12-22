/**
 * 
 */
package site_mapper.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
 * @version 1.3
 * 	Revert to standard class.
 * @since 1.0
 */
public class ElementTestButton extends ElementTest {	
	private String text;
	private String fafa;	

	public ElementTestButton(ElementLoader loader, String name, String text, String fafa) {
		super("button", name, loader);
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
						String faFaActual = super.getLoader().getControlTest().getFaFaText(super.getName());
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
						String textActual = super.getLoader().getControlTest().getControlText(super.getName());
						assertEquals(text, textActual);
					}
				)
			);
	}
	
}
