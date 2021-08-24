package test_result;

import org.apache.logging.log4j.Level;

import factories.TestStatusFactory;
import testrail_api.TestCaseData;

/**
 * @author SteveBrown
 * A test result type.
 * Sets the data specific to a passed test.
 */
public class ResultPassed implements TestResultSetter{

	@Override
	public void setResult(TestCaseData data) {
		data.setStatus(TestStatusFactory.passed());
		data.setComment("Test passed");
		data.setLogLevel(Level.forName("PASSED", 0));
	}

}
