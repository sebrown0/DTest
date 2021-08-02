/**
 * 
 */
package reporting.strategy;

import test_result.TestResultGetter;
import testrail_api.MyTestRailAPI;
import testrail_api.TestRailClient;

/**
 * @author SteveBrown
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

}
