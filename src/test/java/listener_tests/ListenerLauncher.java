package listener_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.launcher.listeners.TestExecutionSummary.Failure;

import listeners.TestListener;


class ListenerLauncher {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void test() {
		TestListener listner = new TestListener();
		TestExecutionSummary summary;
		List<Failure> failed;
		
//		summary = listner.getSummary();
//		
//		assertTrue(summary.getTestsFoundCount() > 0);
//		assertTrue(summary.getTestsFailedCount() == 1);
//		assertTrue(summary.getTestsSucceededCount() == 1);
//		failed = summary.getFailures();
//		for (Failure failure : failed) {
//			System.out.printf(failure.getTestIdentifier().getDisplayName());
//		}
	}

}
