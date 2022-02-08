/**
 * 
 */
package dynamic_tests.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import controls.ControlTestIMPL;
import object_models.pages.homepage.CoreData;
import object_models.pages.homepage.HomePage;
import site_mapper.elements.ElementCreation;
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
	private ControlTestIMPL ct;

	public ElementTestButton(HomePage hp, CoreData coreData, MenuItem item, ElementCreation e) {
		super(hp, item, "button", e.getElementName());
	
		this.ct = new ControlTestIMPL(e.getElementName());
		this.text = e.getText();
		this.fafa = e.getFafa();
	}
	
	@Override //TestElement
	public List<DynamicTest> createTests() {
//		Not using dynamic test methods at present.
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
						String faFaActual = super.getControlTest().getFaFaText(ct);
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
						String textActual = super.getControlTest().getControlText(ct);
						assertEquals(text, textActual);
					}
				)
			);
	}
		
}
