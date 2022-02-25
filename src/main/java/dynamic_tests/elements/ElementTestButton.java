/**
 * 
 */
package dynamic_tests.elements;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DynamicTest;

import controls.Control;
import controls.ControlTestData;
import dynamic_tests.mappers.TestNode;
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
public class ElementTestButton implements TestAdder {	
	private String text;
	private String fafa;	
	private String toolTipText;

	public ElementTestButton(
			TestNode testNode, HomePage hp, 
			MenuItem item, ElementCreation e) {
					
			text = e.getText();
			fafa = e.getFafa();
			toolTipText = ((ElementDetails)e).getToolTipText();
		}
		
	@Override
	public void addTestTo(List<DynamicTest> testList, String elName, Optional<Control> cntrl) {
		createButtonFaFaCheck(testList, elName, cntrl);
		createButtonTextCheck(testList, elName, cntrl);
		createButtonToolTipCheck(testList, elName, cntrl);
	}
	private void createButtonFaFaCheck(List<DynamicTest> testList, String elName, Optional<Control> cntrl) {				
		testList.add(
				DynamicTest.dynamicTest(
					"Is [" + elName +"] button [FaFa] correct?", 
					() -> {							
//						System.out.println("->" + super.getName()); // TODO - remove or log 	
						String faFaActual = ControlTestData.getFaFaText(cntrl);
						assertEquals(fafa, faFaActual);
					}
				)
			);
	}
	private void createButtonTextCheck(List<DynamicTest> testList, String elName, Optional<Control> cntrl) {
		testList.add(
				DynamicTest.dynamicTest(
					"Is [" + elName +"] button [text] correct?", 
					() -> {							
						String textActual = ControlTestData.getControlText(cntrl);
						assertEquals(text, textActual);
					}
				)
			);
	}
	private void createButtonToolTipCheck(List<DynamicTest> testList, String elName, Optional<Control> cntrl) {
		if(toolTipText != null && toolTipText.length()>0) {
			testList.add(
					DynamicTest.dynamicTest(
						"Is [" + elName +"] button [tool tip] correct?", 
						() -> {							
							String textActual = ControlTestData.getControlToolTip(cntrl);
							assertEquals(toolTipText, textActual);
						}
					)
				);	
		}		
	}	
	
}
