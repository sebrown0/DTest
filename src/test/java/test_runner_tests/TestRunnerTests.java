/**
 * 
 */
package test_runner_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;

import app.AppArguments;
import app.ArgumentChecker;
import app.StartUp;
import app.test_reader.IncludedTestsReader;
import app.test_reader.TestClass;
import app.test_reader.TestPackage;
import app.test_runner.TestsRunner;
import exceptions.InvalidArgumentException;
import providers.XMLFileProvider;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 */
class TestRunnerTests {
	private static String[] validArgs = new String[] {"payroll","navigation"};
	private static String[] invalidArgs = new String[] {"roll","navigation"};
		
	@Test
	void welcomeMsg() {
		StartUp startUp = new StartUp(validArgs);
		startUp.welcome();
	}
	
	@Test
	void checkArg_validModule() throws InvalidArgumentException {
		ArgumentChecker checker = new ArgumentChecker();
		AppArguments appArgs = checker.withArgs(validArgs).getCheckedArgs().get();		
		assertEquals("Payroll", appArgs.getModuleName());		
	}
	
	@Test
	void checkArg_invalidModule() {
		ArgumentChecker checker = new ArgumentChecker();		
		try {
			checker.withArgs(invalidArgs).getCheckedArgs().get();
			fail("aaaa");
		} catch (InvalidArgumentException e) {
			assertTrue(true);
		}						
	}
	
	@Test
	void includedTest_packageAllInPackageName() {
		String testFilePath = XMLFileProvider.INCLUDE_TESTS_FILE_PATH + "\\test_all_in_nav_payroll.xml";		
		IncludedTestsReader reader = new IncludedTestsReader(testFilePath);
		
		TestPackage pck = reader.getPackage();
		List<TestClass> testClasses = pck.getTestClasses(); 
		assertTrue(testClasses.size() > 0);
		assertEquals("navigation.payroll", pck.getPackageName());
	}

	@Test
	void specificTests_InPackageName() {
		String testFilePath = XMLFileProvider.INCLUDE_TESTS_FILE_PATH + "\\test_specific_in_nav_payroll.xml";		
		IncludedTestsReader reader = new IncludedTestsReader(testFilePath);
		
		TestPackage pck = reader.getPackage();
		List<TestClass> testClasses = pck.getTestClasses(); 
		assertTrue(testClasses.size() > 0);
		assertEquals("navigation.payroll", pck.getPackageName());
	}
	
	@Test
	void allTests_InTestDir() {
		String testFilePath = XMLFileProvider.INCLUDE_TESTS_FILE_PATH + "\\test_all.xml";		
		IncludedTestsReader reader = new IncludedTestsReader(testFilePath);
		
		TestPackage pck = reader.getPackage();
		List<TestClass> testClasses = pck.getTestClasses(); 
		assertTrue(testClasses.size() > 0);
		assertEquals("All", pck.getPackageName());
	}

	/*
	 * WITH THE FOLLOWING TESTS IT'S DIFFICULT TO 
	 * TEST PROGRAMTICALLY FOR PASS OR FAIL, THEREFORE:
	 * 
	 * If it runs with no errors can assume pass.
	 * Check app log and test logs for further proof.
	 * 
	 * TODO - Could automatically check logs for proof of tests runs.
	 */
	@Test
	void run_specificTestsInPackageName() {
		String testFile = "\\test_specific_in_nav_payroll.xml";
		String testFilePath = XMLFileProvider.INCLUDE_TESTS_FILE_PATH + testFile;		
		IncludedTestsReader reader = new IncludedTestsReader(testFilePath);
		
		TestPackage testPackage = reader.getPackage();
		AppArguments appArgs = new AppArguments("Payroll", testFile, testPackage);
		
		TestsRunner testRunner = new TestsRunner(appArgs);
		testRunner.runTests();
	}
	
	@Test
	void allTests_InPackageName() {		
		String testFile = "\\test_all_in_nav_payroll.xml";
		String testFilePath = XMLFileProvider.INCLUDE_TESTS_FILE_PATH + testFile;		
		IncludedTestsReader reader = new IncludedTestsReader(testFilePath);
		
		TestPackage testPackage = reader.getPackage();
		AppArguments appArgs = new AppArguments("Payroll", testFile, testPackage);
		
		TestsRunner testRunner = new TestsRunner(appArgs);
		testRunner.runTests();
	}

	@Test
	void allTests() {		
		String testFile = "\\test_all.xml";
		String testFilePath = XMLFileProvider.INCLUDE_TESTS_FILE_PATH + testFile;		
		IncludedTestsReader reader = new IncludedTestsReader(testFilePath);
		
		TestPackage testPackage = reader.getPackage();
		AppArguments appArgs = new AppArguments("Payroll", testFile, testPackage);
		
		TestsRunner testRunner = new TestsRunner(appArgs);
		testRunner.runTests();
	}
	
}


