/**
 * 
 */
package dynamic_tests.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import controls.ControlTest;
import controls.ControlTestIMPL;
import object_models.pages.homepage.CoreData;
import object_models.pages.homepage.HomePage;
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
//	private CoreData coreData;
	
	public ElementTestButton(
			HomePage hp, CoreData coreData, MenuItem item, ControlTest controlTest, String name, String text, String fafa) {
		super(hp, item, "button", name, controlTest);
	
//		this.coreData = coreData;
		this.ct = new ControlTestIMPL(name);
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
		
//		JsPanel panel = (JsPanel) controlTest;
//		ContextManager cm = panel.getContextManager();
//		cm.findContext(panel);
		
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
//						String textActual = super.getControlTest().getControlText(super.getName());
						String textActual = super.getControlTest().getControlText(ct);
						assertEquals(text, textActual);
					}
				)
			);
	}
		
}
