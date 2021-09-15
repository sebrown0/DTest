package utils_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import app.AppArguments;
import app.ArgumentChecker;
import exceptions.InvalidArgumentException;

/**
 * @author Steve Brown
 *
 * Check that the args passed are correct.
 */
class ArgumentCheckerTests {
	
	@Test
	void invalidNumberOfArgs()  {
		ArgumentChecker checker = new ArgumentChecker();
		boolean exceptionCaught = false;
		
		try {
			checker.withArgs(new String[]{"Payroll"}).getCheckedArgs();
		} catch (InvalidArgumentException e) {
			assertTrue(true);
			exceptionCaught = true;
		} 

		if(!exceptionCaught) {
			fail();
		}
	}
	
	@Test
	void validNumberOfArgs()  {
		ArgumentChecker checker = new ArgumentChecker();
		boolean exceptionCaught = false;
		
		try {
			checker.withArgs(new String[]{"Payroll", "unit_test.xml"}).getCheckedArgs();
		} catch (InvalidArgumentException e) {
			fail();
			exceptionCaught = true;
		} 

		if(!exceptionCaught) {
			assertTrue(true);
		}		
	}

	@Test
	void validTestFile() throws InvalidArgumentException  {
		ArgumentChecker checker = new ArgumentChecker();
		Optional<AppArguments> args = checker.withArgs(new String[]{"Payroll", "unit_test.xml"}).getCheckedArgs();		
		args.ifPresentOrElse(a -> assertEquals("Payroll", a.getModuleName()), 
				new Runnable() {			
					@Override
					public void run() {	fail(); }
				}
		);
	}
	
	@Test
	void incompatibleFile_and_Module() throws InvalidArgumentException  {
		ArgumentChecker checker = new ArgumentChecker();
		Optional<AppArguments> args = checker.withArgs(new String[]{"Absence", "unit_test.xml"}).getCheckedArgs();		
		if(args.isPresent()) {
			/* 
			 * If the module specified is not equal to the 'applicable' 
			 * module in the XML then args should be empty.
			 */			
			fail();
		}
	}
}
