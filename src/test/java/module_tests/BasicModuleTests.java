/**
 * 
 */
package module_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import library.dakar_hr.providers.ModuleNames;

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
