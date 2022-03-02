/**
 * 
 */
package dynamic_tests.assertations;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;

import controls.data.ControlTestData;
import controls.interfaces.Control;
import controls.interfaces.ControlTest;
import dynamic_tests.test_adders.TestAdderWithData;
import site_mapper.jaxb.pom.test_data.TestDataOut;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */

/*
 * TODO
 * ----
 * Split this up!!!
 * 
 */
public class AssertTextEquals {
	private ControlTest controlTest;
	private Optional<Control> cntrl;
	private String textActual;
	
	public AssertTextEquals(ControlTest controlTest, Optional<Control> cntrl) {
		this.controlTest = controlTest;
		this.cntrl = cntrl;
	}

	private void setTextActual() {
		textActual = ControlTestData.getControlText(cntrl);
	}
	
	public void assertTextEquals(String textExpected) {
		setTextActual();
		runAssert(textExpected);
	}
	
	public void runAssert(String expected) {
		assertEquals(expected, textActual);
	}
	
	public void assertTextEquals(TestAdderWithData testData) {
		setTextActual();
		
		TestDataOut dataOut = testData.getTestDataOut();
		if(dataOut != null) {
			//this will have to be checked for Text or List!!!!!!!!!!!
//			assertEquals(dataOut.getTestData().getValue(), textActual);
			runAssert(dataOut.getTestData().getValue());
		}else {
			LogManager.getLogger(AssertTextEquals.class)
				.info(
					String.format(
							"No test data to check expected result for [%s - %s]", 
							controlTest.getClass().getSimpleName(), cntrl.get().getClass().getSimpleName()));
		}				
	}


}
