/**
 * 
 */
package dynamic_tests.test_adders;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.DynamicTest;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TestAdderListNotImplemented extends XXTestAdder {
	
	@Override
	public DynamicTest addTest() {
		return 			
			DynamicTest.dynamicTest(
				"List not supported for [" + elName +"]", 
				() -> {			
					LogManager.getLogger(getClass()).error("ElementTestFactory.createTextListCheck not implemented.");
				});
	}
		

	
}
