/**
 * 
 */
package dynamic_tests.test_elements;

import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import controls.data.ControlTestData;
import controls.interfaces.ControlTest;
import dynamic_tests.assertations.AssertTextEquals;
import dynamic_tests.common.XmlInfo;
import dynamic_tests.elements.ControlFinder;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class CreateFaFaCheck extends TestCreator {
	private String faFaExpected;
	private AssertTextEquals assertEquals;
	
	public CreateFaFaCheck(XmlInfo testInfo,
		ControlFinder cntrlFinder, List<DynamicTest> testList, 
		ControlTest controlTest, String faFa) {
		
		super(testInfo, cntrlFinder, testList, controlTest);

		this.faFaExpected = faFa;
	}
	
	@Override
	public void createTest(String elName) {		
		testList.add(
			DynamicTest.dynamicTest(
				"Is [" + elName +"] FaFa correct?", 
				() ->	executeTest()));			
	}
		
	private void executeTest() {
		getControlAndParent();
		String faFaActual = ControlTestData.getFaFaText(cntrl);		
		assertEquals = new AssertTextEquals(testInfo.getTestReportStrategy(), controlTest, cntrl);
		assertEquals.assertTextEquals(faFaExpected, faFaActual);	
	}
}
