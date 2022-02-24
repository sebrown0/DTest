/**
 * 
 */
package dynamic_tests.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import controls.ControlTestData;
import dynamic_tests.mappers.TestNode;
import object_models.pages.homepage.HomePage;
import site_mapper.elements.ElementCreation;
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
	
	//TODO Where do we get expected from???
	public ElementTestTextOut(
			TestNode testNode, HomePage hp, MenuItem item, 
			 ElementCreation el) {
		super(testNode, hp, item, "TextOut", el.getElementName());
		
//		this.textExpected = textExpected;
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
//						String textActual = super.myControlTest().getControlText(getName());
						String textActual = ControlTestData.getControlText(super.getControl());
						assertEquals(textExpected, textActual);
					}
				)
			);
	}


}
