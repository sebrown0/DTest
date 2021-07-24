package testrail;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import exceptions.IncorrectTestStatusException;
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
		TestStatus status = null;
		try {
			status = new TestStatus(TestStatusValues.BLOCKED());
		} catch (IncorrectTestStatusException e) {
			// TODO Auto-generated catch block
		}
		assertTrue(status.getStatus() == TestStatusValues.BLOCKED());
	}
	
	@Test
	void createIncorrectTestStatus() {
		TestStatus status = null;
		try {
			status = new TestStatus(-1);
		} catch (IncorrectTestStatusException e) {
			// TODO Auto-generated catch block
		}
		assertNull(status);
	}
}
