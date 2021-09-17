/**
 * 
 */
package test_runner_tests;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

import java.io.PrintWriter;

import org.junit.jupiter.api.Test;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
//import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
//import static org.junit.platform.launcher.EngineFilter.*;
//import static org.junit.platform.launcher.TagFilter.*;

/**
 * @author Steve Brown
 *
 */
class TestRunnerTests {

	public class Runner{
		SummaryGeneratingListener listner = new SummaryGeneratingListener();
		
		public void runAll() {
			LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
					.selectors(selectPackage("object_model_tests.navigation"))
					.filters(includeClassNamePatterns(".*"))					
					.build();
			Launcher launcher = LauncherFactory.create();
//			TestPlan testPlan = launcher.discover(request);
			launcher.registerTestExecutionListeners(listner);
			launcher.execute(request);	
		}
	}
	
	@Test
	void test() {
		Runner runner = new Runner();
		runner.runAll();
		
		TestExecutionSummary summary = runner.listner.getSummary();
		summary.printTo(new PrintWriter(System.out));
	}

}


