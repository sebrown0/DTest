package aaaa;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class UiTest {
	private DynamicContainer app;
	private List<DynamicContainer> modules;
	
	@TestFactory
	DynamicContainer testUI() {
		
		List<DynamicTest> documentTests = Arrays.asList(
				dynamicTest("Doc T1", ()->{ assertTrue(true); }),
				dynamicTest("Doc T2", ()->{ assertTrue(true); }));
		
		DynamicContainer documents = DynamicContainer.dynamicContainer("Documents", documentTests);
		List<DynamicContainer> payrollItems = Arrays.asList(documents);
		
		DynamicContainer payroll = DynamicContainer.dynamicContainer("Payroll", payrollItems);
		DynamicContainer personnel = DynamicContainer.dynamicContainer("Personnel", Arrays.asList(DynamicTest.dynamicTest("Personnel test 1", ()->{ assertTrue(true); })));
		
		
		app = DynamicContainer.dynamicContainer("App", Arrays.asList(payroll, personnel));
		return app;
//		return DynamicContainer.dynamicContainer("Root", Arrays.asList(DynamicTest.dynamicTest("Root test", ()->{ assertTrue(true); })));
	}
	
	@TestFactory
	DynamicContainer testApp() {
		
		List<DynamicTest> documentTests = Arrays.asList(
				dynamicTest("Doc T1", ()->{ assertTrue(true); }),
				dynamicTest("Doc T2", ()->{ assertTrue(true); }));
		
		DynamicContainer documents = DynamicContainer.dynamicContainer("Documents", documentTests);
		List<DynamicContainer> payrollItems = Arrays.asList(documents);
		
		DynamicContainer payroll = DynamicContainer.dynamicContainer("Payroll", payrollItems);
		DynamicContainer personnel = DynamicContainer.dynamicContainer("Personnel", Arrays.asList(DynamicTest.dynamicTest("Personnel test 1", ()->{ assertTrue(true); })));
		
		
		app = DynamicContainer.dynamicContainer("App", Arrays.asList(payroll, personnel));
		return app;
//		return DynamicContainer.dynamicContainer("Root", Arrays.asList(DynamicTest.dynamicTest("Root test", ()->{ assertTrue(true); })));
	}

}
