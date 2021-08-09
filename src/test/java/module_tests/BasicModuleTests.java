/**
 * 
 */
package module_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import providers.ModuleNames;

/**
 * @author Steve Brown
 *
 */
class BasicModuleTests {

	@Test
	void validModuleName() {
		assertTrue(ModuleNames.isValidName("Payroll"));
		assertTrue(ModuleNames.isValidName("Personnel"));
		assertTrue(ModuleNames.isValidName("T&A"));
		assertTrue(ModuleNames.isValidName("Absence"));
		assertTrue(ModuleNames.isValidName("Appraisal"));
	}

}
