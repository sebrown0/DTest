/**
 * 
 */
package dynamic_tests.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import controls.ControlTestData;
import dynamic_tests.mappers.TestNode;
import object_models.pages.homepage.CoreData;
import object_models.pages.homepage.HomePage;
import site_mapper.elements.ElementCreation;
import site_mapper.elements.ElementDetails;
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
	private String toolTipText;	
	
	public ElementTestButton(
		TestNode testNode, HomePage hp, CoreData coreData, MenuItem item, ElementCreation e) {
		super(testNode, hp, item, "button", e.getElementName());
	
		text = e.getText();
		fafa = e.getFafa();
		toolTipText = ((ElementDetails)e).getToolTipText();
	}
	/*
	 * HAVE TO GERT THIS BEFORE WE GET HERE 
	 * FROM THE IP GROUP!!!!!!!!!!!!!!!!!!!
	 */
	@Override //TestElement
	public List<DynamicTest> createTests() {
//		Not using dynamic test methods at present.
//		addTestMethod("button", coreData);
		createButtonFaFaCheck();
		createButtonTextCheck();
		createButtonToolTipCheck();
		return super.getTests();		
	}
		
	private void createButtonFaFaCheck() {				
		super.getTests().add(
				DynamicTest.dynamicTest(
					"Is [" + super.getName() +"] button [FaFa] correct?", 
					() -> {							
//						System.out.println("->" + super.getName()); // TODO - remove or log 	
						String faFaActual = ControlTestData.getFaFaText(super.getControl());
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
						String textActual = ControlTestData.getControlText(super.getControl());
						assertEquals(text, textActual);
					}
				)
			);
	}
	private void createButtonToolTipCheck() {
		if(toolTipText != null && toolTipText.length()>0) {
			super.getTests().add(
					DynamicTest.dynamicTest(
						"Is [" + super.getName() +"] button [tool tip] correct?", 
						() -> {							
							String textActual = ControlTestData.getControlToolTip(super.getControl());
							assertEquals(toolTipText, textActual);
						}
					)
				);	
		}
		
	}	
}
