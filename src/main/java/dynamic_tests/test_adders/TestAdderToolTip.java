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
public final class TestAdderToolTip extends XXTestAdder {
	private String expected;
		
	public TestAdderToolTip(String expected) {
		this.expected = expected;
	}

	@Override
	public DynamicTest addTest() {
		return DynamicTest.dynamicTest(
			"Is [" + elName +"] tool tip correct?", 
			() -> {							
				String textActual = ControlTestData.getControlToolTip(getControl());
				assertEquals(expected, textActual);
			});
	}
}
