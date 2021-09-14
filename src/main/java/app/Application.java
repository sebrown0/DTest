/**
 * 
 */
package app;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

import java.io.PrintWriter;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

/**
 * @author SteveBrown
 *
 */
public class Application {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Runner runner = new Runner();
		runner.runAll();
		
		TestExecutionSummary summary = runner.listner.getSummary();
		summary.printTo(new PrintWriter(System.out));
	}

	public static class Runner{
		SummaryGeneratingListener listner = new SummaryGeneratingListener();
		
		public void runAll() {
			LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
//					.selectors(selectPackage("object_model_tests.navigation"))
					.selectors(selectPackage("object_model_tests.login"))
					.filters(includeClassNamePatterns(".*"))					
					.build();
			Launcher launcher = LauncherFactory.create();
			TestPlan testPlan = launcher.discover(request);
			launcher.registerTestExecutionListeners(listner);
			launcher.execute(request);	
		}
	}
}
