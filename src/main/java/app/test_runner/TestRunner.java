
package app.test_runner;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.platform.engine.DiscoverySelector;
import org.junit.platform.engine.discovery.ClassSelector;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import app.AppArguments;
import xml_reader.test_file.TestClass;
import xml_reader.test_file.TestPackage;

/**
 * @author Steve Brown
 *
 * Discover and run tests.
 */
public class TestRunner {
	private TestPackage testPackage;
	private Logger logger = LogManager.getLogger();
	private String fileName;
	private String packageName;
	
	public TestRunner(AppArguments args) {
		this.testPackage = args.getTestPackage();
		this.fileName = args.getTestFileName();
		this.packageName = args.getTestPackage().getPackageName();
	}
		
	public void runTests() {		
		SummaryGeneratingListener listner = new SummaryGeneratingListener();
		
		writeStartMsgForTestClass();
		
		DiscoverySelector[] selectors  = getSelectors();

		LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
			.selectors(selectors)		
			.filters(includeClassNamePatterns(".*"))				
			.build();
		
		Launcher launcher = LauncherFactory.create();
			launcher.registerTestExecutionListeners(listner);
			launcher.execute(request);
	}
		
	private void writeStartMsgForTestClass() {
		System.out.println("\nRunning tests for: " + fileName);
		System.out.println("\nRunning package: " + packageName);
//		logger.info("\nRunning tests for: " + fileName);
//		logger.info("\nRunning package: " + packageName);
	}
	
	private DiscoverySelector[] getSelectors() {
		List<DiscoverySelector> selectors = new ArrayList<>();		
		List<TestClass> classes = testPackage.getTestClasses();		
		
		createSelectorsFromTestClasses(selectors, classes);
		return selectors.toArray(new DiscoverySelector[0]);
	}

	private void createSelectorsFromTestClasses(List<DiscoverySelector> selectors, List<TestClass> classes) {
		final String packagePath = "object_model_tests."  + testPackage.getPackageName() + ".";		
		for (TestClass testClass : classes) {
			final String clazzUnderTest = testClass.getName();
			final String clazzPath = packagePath + clazzUnderTest;
			logger.info("Starting process for [" + clazzUnderTest + "]");
			try {				
				ClassSelector selector = selectClass(Class.forName(clazzPath));
				if(!(selector == null)) {					
					selectors.add(selector);
				}else {
					logger.error("Selector for [" + clazzPath + "] is null");
				}				 				
			} catch (Exception e) {
				logger.error("Could not get class for name [" + clazzPath + "]. This test suite will be ignored");
			}			
		}
	}	
}





