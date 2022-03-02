/**
 * 
 */
package dynamic_tests.test_adders;

import dynamic_tests.test_elements.ElementTestFactory;
import site_mapper.elements.ElementDetails;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TestAdderButton implements TestAdder {		
	private String toolTipText;
	private ElementDetails elDetails;
	
	public TestAdderButton(ElementDetails e) {
		this.elDetails = e;		
		toolTipText = e.getToolTipText();		
	}
		
	@Override
	public void addTestsWith(ElementTestFactory testFactory) {
		testFactory
			.createTests(
					new TestAdderTextCheck(elDetails),
					new TestAdderFaFa(), 
					new TestAdderToolTip(toolTipText));		
	}
	
}
