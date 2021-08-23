package test_result;

import org.apache.logging.log4j.Level;

import factories.TestStatusFactory;
import testrail_api.TestCaseData;

public class ResultFailed implements TestResultSetter{
	private Throwable cause;
	
	public ResultFailed(Throwable cause) {
		this.cause = cause;		
	}

	@Override
	public void setResult(TestCaseData data) {
		data.setStatus(TestStatusFactory.failed());
		data.setComment("Test failed for reason [" + cause.getMessage() + "]");
		data.setLogLevel(Level.forName("FAILED", 900));		
	}	
}
