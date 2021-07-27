package listeners;

import static org.junit.platform.engine.discovery.ClassNameFilter.includeClassNamePatterns;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;
import static org.junit.platform.engine.discovery.DiscoverySelectors.selectPackage;

import page_tests.LandingPageTests;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;

public class TestListener {
	private LauncherDiscoveryRequest request;		
	private Launcher launcher;
//	private SummaryGeneratingListener listener = new SummaryGeneratingListener();
	private MyTestExecutionListener listener = new MyTestExecutionListener();
	
	public TestListener() {
		request = LauncherDiscoveryRequestBuilder.request()
			.selectors(
					selectPackage("demo.src.test.java.page_tests"),
					selectClass(LandingPageTests.class))
			.filters(includeClassNamePatterns(".*"))
			.build();
		launcher = LauncherFactory.create();
		setTestPlan();
		registerListner();
	}
		
	private void setTestPlan() {
		this.launcher.discover(this.request);
	}
	
	private void registerListner() {		
		this.launcher.registerTestExecutionListeners(listener);	
		this.launcher.execute(request);
	}
	
//	public TestExecutionSummary getSummary() {
////		return listener.getSummary();
//	}
}
