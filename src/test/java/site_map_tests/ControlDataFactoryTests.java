/**
 * 
 */
package site_map_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import exceptions.InvalidArgumentException;
import site_mapper.creators.ControlDataFactory;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Test that the string produced by the ControlDataStringFactory
 * is correct.
 * 
 * The values passed to the factory are not necessarily representative
 * of 'real' values.
 * 
 */
class ControlDataFactoryTests {
	@Test
	void incorrectControlName() {		
		Exception ex = assertThrows(
			InvalidArgumentException.class, 
			() -> ControlDataFactory.getControlData("null", "null", "null", "css"));
		assertEquals("[null] is not a valid control type name.", ex.getMessage());
	}
	@Test
	void incorrectByValue() {		
		Exception ex = assertThrows(
			InvalidArgumentException.class, 
			() -> ControlDataFactory.getControlData("button", "null", "null", "null"));
		assertEquals("[null] is not a valid By type name.", ex.getMessage());
	}
	@Test
	void check_Button_with_CSS() throws InvalidArgumentException {
		String s = ControlDataFactory.getControlData("search", "button", "button[name='QBF1']", "css");
		assertEquals("new ControlData(search, new ControlGetterButton(coreData, By.cssSelector(\"button[name='QBF1']\")))", s);
	}
	@Test
	void check_Button_with_XPATH() throws InvalidArgumentException {
		String s = ControlDataFactory.getControlData("search", "button", "button[name='QBF1']", "xpath");
		assertEquals("new ControlData(search, new ControlGetterButton(coreData, By.xpath(\"button[name='QBF1']\")))", s);
	}
	@Test
	void check_Button_with_ID() throws InvalidArgumentException {
		String s = ControlDataFactory.getControlData("search", "button", "button[name='QBF1']", "id"); 	
		assertEquals("new ControlData(search, new ControlGetterButton(coreData, By.id(\"button[name='QBF1']\")))", s);
	}

}
