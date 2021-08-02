package test_result;

import factories.TestStatusFactory;
import testrail_api.TestCaseData;

public class ResultFailed implements TestResultSetter{

	@Override
	public void setResult(TestCaseData data) {
		data.setStatus(TestStatusFactory.failed());
		data.setComment("Test failed");
	}
}
