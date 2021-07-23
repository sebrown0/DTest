package listners;

import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;

public class MyTestExecutionListener implements TestExecutionListener{	
	
	public MyTestExecutionListener(){
		System.out.println("MyTestExecutionListener");
	}
		
	public void executionFinished​(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult){
		System.out.println("executionFinished​");
		System.out.println(testIdentifier.getDisplayName());
	}

  public void testPlanExecutionFinished(TestPlan testPlan) {
  	
    System.out.println("testPlanExecutionFinished: " + testPlan.toString());
  }
}
