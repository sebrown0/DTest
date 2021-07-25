package testrail;

import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.gurock.testrail.APIClient;

import exceptions.IncorrectTestStatusException;
import testrail_api.MyTestRailAPI;
import testrail_api.TestCaseData;
import testrail_api.TestRail;
import testrail_api.TestStatus;
import testrail_api.TestStatusValues;

class MyTestRailAPITest {
	private static APIClient client;
	private static TestRail tr = new TestRail("sbrown@dakarsoftware.com", "12345"); // TODO read from startup 
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		client = tr.getInitialisedClient();
	}
	
	@Test
	@Tag("R19")
	@Tag("C3831")
	void postResult() throws IncorrectTestStatusException {
		MyTestRailAPI api = new MyTestRailAPI(client);
		TestCaseData data = new TestCaseData(
				"R19", "C3831",	new TestStatus(TestStatusValues.PASSED()), "Passed");
		JSONObject json = api.postSingleTest(data);
		assertTrue(json.containsKey("comment"));
	}

}
