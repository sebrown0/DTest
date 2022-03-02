/**
 * 
 */
package dynamic_tests.test_adders;

import org.junit.jupiter.api.DynamicTest;

import dynamic_tests.assertations.AssertTextEquals;
import site_mapper.elements.ElementDetails;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public final class TestAdderTextNotImplemented extends XXTestAdder {
	private ElementDetails elDetails;
	
	public TestAdderTextNotImplemented(ElementDetails elDetails) {
		this.elDetails = elDetails;
	}
	
	@Override
	public DynamicTest addTest() {		
		return 			
			DynamicTest.dynamicTest(
				"Is [" + elName +"] text correct?", 
				() -> {							
					new 
						AssertTextEquals(controlTest, getControl())
							.assertTextEquals(elDetails.getText());
				});
	}
	
	
}
