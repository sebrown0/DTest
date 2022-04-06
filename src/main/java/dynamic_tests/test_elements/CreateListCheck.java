/**
 * 
 */
package dynamic_tests.test_elements;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.DynamicTest;

import controls.interfaces.ControlTest;
import dynamic_tests.common.XmlInfo;
import dynamic_tests.elements.ControlFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class CreateListCheck extends TestCreator {
	
	public CreateListCheck(XmlInfo testInfo,
		ControlFinder cntrlFinder, List<DynamicTest> testList, 
		ControlTest controlTest) {
		
		super(testInfo, cntrlFinder, testList, controlTest);

	}
	
	@Override
	public void createTest(String elName) {		
		testList.add(
			DynamicTest.dynamicTest(
				"Is [" + elName +"] text correct?", 
				() -> {			
					fail("ElementTestFactory.createTextListCheck not implemented.");
					LogManager
						.getLogger(CreateListCheck.class)
						.error("ElementTestFactory.createTextListCheck not implemented.");
				}));
	}
		
}
