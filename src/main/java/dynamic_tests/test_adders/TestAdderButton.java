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
	private String elName;
	private String text;
	private String fafa;	
	private String toolTipText;
	
	public TestAdderButton(ElementCreation e) {
		elName = e.getElementName();
		text = e.getText();
		fafa = e.getFafa();
		toolTipText = ((ElementDetails)e).getToolTipText();		
	}
		
	@Override
	public void addTestsWith(ElementTestFactory testFactory) {
		if(fafa != null) testFactory.createButtonFaFaCheck(elName, fafa); 
		if(text != null) testFactory.createTextCheck(elName, text);
		if(toolTipText != null) testFactory.createToolTipCheck(elName, toolTipText);					
	}
	
}
