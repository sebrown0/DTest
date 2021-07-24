package testrail;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.IncorrectTestStatusException;
import testrail_api.TestCaseData;
import testrail_api.TestStatus;
import testrail_api.TestStatusValues;

class TestCaseDataTest {
  
	@Test
	void correctTestCaseData() throws IncorrectTestStatusException {
		TestCaseData data = new TestCaseData(
				"R1", 
				"C1", 
				new TestStatus(TestStatusValues.UNTESTED()), 
				"No comment");
		assertTrue(data.getTestStatus().getStatus() == TestStatusValues.UNTESTED());
	}

}
