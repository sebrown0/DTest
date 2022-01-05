/**
 * 
 */
package site_map_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import exceptions.InvalidArgumentException;
import site_mapper.creators.ControlDataStringFactory;
import site_mapper.creators.ControlDataValues;

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
			() -> ControlDataStringFactory.getControlData(new ControlDataValues("null", "null", "null", "css")));
		assertEquals("[null] is not a valid control type name.", ex.getMessage());
	}
	@Test
	void incorrectByValue() {		
		Exception ex = assertThrows(
			InvalidArgumentException.class, 
			() -> ControlDataStringFactory.getControlData(new ControlDataValues("button", "null", "null", "null")));
		assertEquals("[null] is not a valid By type name.", ex.getMessage());
	}
	@Test
	void check_Button_with_CSS() throws InvalidArgumentException {
		String s = ControlDataStringFactory.getControlData(new ControlDataValues("search", "button", "button[name='QBF1']", "css")).get();
		assertEquals("new ControlData(\"search\", new ControlGetterButton(coreData, By.cssSelector(\"button[name='QBF1']\")))", s);
	}
	@Test
	void check_Button_with_XPATH() throws InvalidArgumentException {
		String s = ControlDataStringFactory.getControlData(new ControlDataValues("search", "button", "button[name='QBF1']", "xpath")).get();
		assertEquals("new ControlData(\"search\", new ControlGetterButton(coreData, By.xpath(\"button[name='QBF1']\")))", s);
	}
	@Test
	void check_Button_with_ID() throws InvalidArgumentException {
		String s = ControlDataStringFactory.getControlData(new ControlDataValues("search", "button", "button[name='QBF1']", "id")).get(); 	
		assertEquals("new ControlData(\"search\", new ControlGetterButton(coreData, By.id(\"button[name='QBF1']\")))", s);
	}
	@Test
	void buildControlsFunction() throws InvalidArgumentException {
		String expected = 
				"private void buildMyControls() {\n" +
				"\tvar myControls = \r\n" +
				"\t\tList.of(" +
				"\n\t\t\tnew ControlData(\"search\", new ControlGetterButton(coreData, By.id(\"button[name='QBF1']\")))," +
				"\n\t\t\tnew ControlData(\"save\", new ControlGetterButton(coreData, By.cssSelector(\"button[name='QBF2']\")))" +
				"\n\t\t);\n\tsuper.buildPanelControls(myControls);\n}";
		
		ControlDataStringFactory fact = 
				new ControlDataStringFactory(
						Arrays.asList(
								new ControlDataValues("search", "button", "button[name='QBF1']", "id"),
								new ControlDataValues("save", "button", "button[name='QBF2']", "css")));
		
		assertEquals(expected, fact.getFunctionBuildMyControls());
	}
	@Test
	void build_empty_ControlsFunction() throws InvalidArgumentException {
		String expected = 
				"private void buildMyControls() {}";
		
		ControlDataStringFactory fact = 
				new ControlDataStringFactory(null); 	
		assertEquals(expected, fact.getFunctionBuildMyControls());
	}
}
