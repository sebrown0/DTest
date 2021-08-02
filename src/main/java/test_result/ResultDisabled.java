package test_result;

import factories.TestStatusFactory;
import testrail_api.TestCaseData;

public class ResultDisabled implements TestResultSetter{

	@Override
	public void setResult(TestCaseData data) {
		data.setStatus(TestStatusFactory.blocked());
		data.setComment("Test disabled");
	}
}