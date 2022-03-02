/**
 * 
 */
package dynamic_tests.test_adders;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DynamicTest;

import controls.ControlTestData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public final class TestAdderFaFa extends XXTestAdder {
		
	@Override
	public DynamicTest addTest() {
		return DynamicTest.dynamicTest(
				"Is [" + elName +"] FaFa correct?", 
				() -> {						 	
					String faFaActual = ControlTestData.getFaFaText(getControl());
					assertEquals(elDetails.getFafa(), faFaActual);
				});
	}

}
