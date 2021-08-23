package testrail;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import testrail_api.TestStatus;
import testrail_api.TestStatusValues;

class TestStatusTest {

	@Test
	void correctTestStatus() {
		assertTrue(TestStatusValues.isValidStatus(1) == true);
	}

	@Test
	void incorrectTestStatus() {
		assertTrue(TestStatusValues.isValidStatus(0) == false);
	}
	
	@Test
	void createCorrectTestStatus() {
		TestStatus status = new TestStatus(TestStatusValues.BLOCKED());
		assertTrue(status.getStatus() == TestStatusValues.BLOCKED());
	}
	
	@Test
	void createIncorrectTestStatus() {
		TestStatus status = new TestStatus(-1);
		assertTrue(status.getStatus() == -1);
	}
}
