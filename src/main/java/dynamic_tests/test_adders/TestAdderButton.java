/**
 * 
 */
package dynamic_tests.test_adders;

import dynamic_tests.test_elements.ElementTestFactory;
import site_mapper.elements.ElementCreation;
import site_mapper.elements.ElementDetails;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TestAdderButton implements TestAdder {	
	private String text;
	private String fafa;	
	private String toolTipText;
	
	public TestAdderButton(ElementCreation e) {				
		text = e.getText();
		fafa = e.getFafa();
		toolTipText = ((ElementDetails)e).getToolTipText();		
	}
		
	@Override
	public void addTestsWith(ElementTestFactory testFactory) {
		testFactory
			.createButtonFaFaCheck(fafa)
			.createTextCheck(text)
			.createToolTipCheck(toolTipText);		
	}
	
}
