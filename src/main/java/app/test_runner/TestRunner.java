
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
 */
public class TestRunner {
	private AppArguments args;
	private TestPackage testPackage;
	private Logger logger = LogManager.getLogger();
	
	public TestRunner(AppArguments args) {
		this.args = args;
		this.testPackage = args.getTestPackage();
	}
		
	public void runTests() {
		System.out.println("\nRunning tests for: " + args.getTestFileName());
		System.out.println("\nRunning package: " + args.getTestPackage().getPackageName());
		
		SummaryGeneratingListener listner = new SummaryGeneratingListener();
		
		DiscoverySelector[] selectors  = getSelectors();

		LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
		.selectors(selectors)
		.filters(includeClassNamePatterns(".*"))				
		.build();
		
		Launcher launcher = LauncherFactory.create();
			launcher.registerTestExecutionListeners(listner);
			launcher.execute(request);
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
			final String clazzName = packagePath + testClass.getName();
			try {				
				ClassSelector selector = selectClass(Class.forName(clazzName));
				if(!(selector == null)) {
					selectors.add(selector);
				}else {
					logger.error("Selector for [" + clazzName + "] is null");
				}				 				
			} catch (Exception e) {
				logger.error("Could not get class for name [" + clazzName + "]. This test suite will be ignored");
			}			
		}
	}	
}





