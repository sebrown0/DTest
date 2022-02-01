/**
 * 
 */
package dynamic_tests.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import controls.ControlTest;
import site_mapper.jaxb.menu_items.MenuItem;

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
public class ElementTestTextOut extends ElementTest {
	private String textExpected;
	
	public ElementTestTextOut(MenuItem item, ControlTest controlTest, String name, String textExpected) {
		super(item, "text_out", name, controlTest);
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
						String textActual = super.getControlTest().getControlText(super.getName());
						assertEquals(textExpected, textActual);
					}
				)
			);
	}


}
