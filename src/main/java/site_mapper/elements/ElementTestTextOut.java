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
public class ElementTestTextOut extends ElementTest {
	private String textExpected;
	
	public ElementTestTextOut(String name, ElementLoader loader, String textExpected) {
		super("text_out", name, loader);
		this.textExpected = textExpected;
	}
;	

	@Override //TestElement 
	public List<DynamicTest> createTests() {
		createTextCheck();
		return super.getTests();		
	}

	private void createTextCheck() {
		super.getTests().add(
				DynamicTest.dynamicTest(
					"Is [" + super.getName() +"] [text] correct?", 
					() -> {							
						String textActual = super.getLoader().getControlTest().getControlText(super.getName());
						assertEquals(textExpected, textActual);
					}
				)
			);
	}


}
