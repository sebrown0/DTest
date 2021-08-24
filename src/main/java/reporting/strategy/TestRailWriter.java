/**
 * 
 */
package reporting.strategy;

import exceptions.InvalidReportWriter;
import test_result.TestResultGetter;
import testrail_api.MyTestRailAPI;
import testrail_api.TestRailClient;

/**
 * @author Steve Brown
 *
 */
public class TestRailWriter implements ResultWriter {
	private MyTestRailAPI api;
	
	public TestRailWriter() {		
		api = new MyTestRailAPI(TestRailClient.getInitialisedClient());		
	}

	@Override
	public void writeResult(TestResultGetter resultGetter) {
		api.postSingleTest(resultGetter.getResult());		
	}

	@Override
	public void checkWriter() throws InvalidReportWriter {
		/*
		 * We have not subscribed to TestRail so the API will always be unavailable.
		 * If we do use it in the future check the API here, and then throw an error if unavailable.
		 */
		throw new InvalidReportWriter("Unable to get TestRail reporter. Using default instead");
	}
}
