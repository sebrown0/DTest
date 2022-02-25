/**
 * 
 */
package dynamic_tests.elements;

import site_mapper.elements.ElementCreation;
import site_mapper.elements.ElementDetails;

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
	
	public ElementTestButton(ElementCreation e) {				
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
