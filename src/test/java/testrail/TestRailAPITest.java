package testrail;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.gurock.testrail.APIClient;
import listners.TestResultLogger;
import testrail_api.TestRail;

@ExtendWith(TestResultLogger.class)
class TestRailAPITest {

	@Test
	@Tag("R18")
	@Tag("C2316")
	void getClient_pass() {
		TestRail api = new TestRail("sbrown@dakarsoftware.com", "12345");
		APIClient client = api.getInitialisedClient();
		assertEquals("sbrown@dakarsoftware.com", client.getUser());
	}

	@Test
	@Tag("R18")
	@Tag("C2317")
	void getClient_fail() {
		TestRail api = new TestRail("sbrown@dakarsoftware.com", "12345");
		APIClient client = api.getInitialisedClient();
		assertEquals("swhite@dakarsoftware.com", client.getUser());
	}

}
