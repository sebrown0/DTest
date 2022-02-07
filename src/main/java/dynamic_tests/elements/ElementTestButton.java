/**
 * 
 */
package dynamic_tests.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import controls.ControlTest;
import object_models.pages.homepage.CoreData;
import site_mapper.jaxb.menu_items.MenuItem;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ElementTestButton extends ElementTest {	
	private String text;
	private String fafa;	
//	private CoreData coreData;
	
	public ElementTestButton(
			CoreData coreData, MenuItem item, ControlTest controlTest, String name, String text, String fafa) {
		super(item, "button", name, controlTest);
	
//		this.coreData = coreData;
		this.text = text;
		this.fafa = fafa;
	}
	
	@Override //TestElement
	public List<DynamicTest> createTests() {
//		addTestMethod("button", coreData);
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
