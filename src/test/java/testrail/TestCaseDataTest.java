package testrail;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.IncorrectTestStatusException;
import factories.TestResultDataFactory;
import testrail_api.TestCaseData;
import testrail_api.TestStatus;
import testrail_api.TestStatusValues;

class TestCaseDataTest {
  
	@Test
	void correctTestCaseData() throws IncorrectTestStatusException {
		TestCaseData data =  TestResultDataFactory
			.buildTestResultWith()
			.setBelongsToTestRunId("R1")
			.setTestId("T1")
			.setStatus(new TestStatus(TestStatusValues.UNTESTED()))
			.setName("A name")
			.setComment("No comment");
		
		assertTrue(data.getTestStatus().getStatus() == TestStatusValues.UNTESTED());
	}

}
