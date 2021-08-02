package testrail;

import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.gurock.testrail.APIClient;

import exceptions.IncorrectTestStatusException;
import factories.TestResultDataFactory;
import listeners.TestResultLogger;
import testrail_api.MyTestRailAPI;
import testrail_api.TestCaseData;
import testrail_api.TestRailClient;
import testrail_api.TestStatus;
import testrail_api.TestStatusValues;

@ExtendWith(TestResultLogger.class)
class TestRailAPITest {
	private static APIClient client;
	private static MyTestRailAPI api = new MyTestRailAPI(client);
		
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		client = TestRailClient.getInitialisedClient();
		api = new MyTestRailAPI(client);		
	}
	
	@Test
	@Tag("R18")
	@Tag("T3829")
	void getClient_pass() {
		assertEquals("sbrown@dakarsoftware.com", client.getUser());
	}

	@Test
	@Tag("R18")
	@Tag("T3830")
	void getClient_fail() {		
		fail("This is a failling test.");
	}

	@Test
	@Tag("R18")
	@Tag("T3832")
	void postResult() throws IncorrectTestStatusException {
		TestCaseData data =  TestResultDataFactory
				.buildTestResultWith()
				.setBelongsToTestRunId("R19")
				.setTestId("T3831")
				.setStatus(new TestStatus(TestStatusValues.PASSED()))
				.setName("Post Result")
				.setComment("Test Passed");
		JSONObject json = api.postSingleTest(data);
		assertTrue(json.containsKey("comment"));
	}
}
