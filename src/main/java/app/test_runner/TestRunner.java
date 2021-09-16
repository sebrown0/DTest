
package app.test_runner;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.asynchttpclient.util.HttpConstants.Methods;
import org.junit.jupiter.api.Test;
import org.junit.platform.engine.DiscoverySelector;

import app.AppArguments;
import logging.LogFactory;
import object_model_tests.TestClass;
import providers.XMLFileProvider;
import xml_reader.config_file.ConfigReader;
import xml_reader.test_file.TestPackage;

/**
 * @author Steve Brown
 *
 */
public class TestRunner {
	private AppArguments args;
	private TestPackage testPackage;
	
	LogFactory l;
	public TestRunner(AppArguments args) {
		this.args = args;
		this.testPackage = args.getTestPackage();
	}
	
	public void runTests() {
		System.out.println("\nRunning tests for: " + args.getTestFileName());
		System.out.println("\nRunning package: " + args.getTestPackage().getPackageName());
		
//		SummaryGeneratingListener listner = new SummaryGeneratingListener();
//		
//		LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
//			.selectors(getSelectors())
//			.filters(includeClassNamePatterns(".*"))				
//			.build();
//		
//		Launcher launcher = LauncherFactory.create();
//				launcher.registerTestExecutionListeners(listner);
//				launcher.execute(request);
		getSelectors();
	}

	private DiscoverySelector[] getSelectors() {
		DiscoverySelector[] selectors = new DiscoverySelector[testPackage.getTestClasses().size()];
		final String packagePath = "object_model_tests."  + testPackage.getPackageName() + "."; 
		
		testPackage.getTestClasses().forEach(c -> {
			int i = 0;
			final String clazzName = packagePath + c.getName();
			try {
				Class<?> clazz = Class.forName(clazzName);
//				Class<?> clazz = Class.forName("app.test_runner.XXX");
				TestClass testClazz = (TestClass)clazz.getDeclaredConstructor(ConfigReader.class).newInstance(new ConfigReader(XMLFileProvider.PROD_CONFIG_FILE_PATH));
				Method[] methods = testClazz.getMethods();
				loopMethods(methods, testClazz);
				testClazz.tearDown();
				selectors[i] = selectClass(Class.forName(clazzName));
//			} catch (IllegalArgumentException | SecurityException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			} catch (IllegalArgumentException | SecurityException | InvocationTargetException | NoSuchMethodException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}				
		});
		return selectors;
	}

	private void loopMethods(Method[] methods, TestClass testClazz) {
		for (Method m : methods) {
			if(m.getAnnotation(Test.class) != null) {
				System.out.println("->" + m.getName());
				try {
					m.invoke(testClazz, null);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}