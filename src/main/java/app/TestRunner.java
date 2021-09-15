/**
 * 
 */
package app;

/**
 * @author Steve Brown
 *
 */
public class TestRunner {
	private AppArguments args;

	public TestRunner(AppArguments args) {
		this.args = args;
	}
	
	public void runTests() {
		System.out.println("\nRunning tests for: " + args.getTestFileName());
		System.out.println("\nRunning package: " + args.getTestPackage().getPackageName());
	}
}

//public static class Runner{
//	SummaryGeneratingListener listner = new SummaryGeneratingListener();
//	
//	public void runAll() {
//		LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
////				.selectors(selectPackage("object_model_tests.navigation"))
//				.selectors(selectPackage("object_model_tests.login"))
//				.filters(includeClassNamePatterns(".*"))					
//				.build();
//		Launcher launcher = LauncherFactory.create();
////		TestPlan testPlan = launcher.discover(request);
//		launcher.registerTestExecutionListeners(listner);
//		launcher.execute(request);	
//	}
//}