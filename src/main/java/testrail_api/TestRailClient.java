/**
 * 
 */
package testrail_api;

import com.gurock.testrail.APIClient;

/**
 * @author Brown
 *
 */
public class TestRailClient {
	public static APIClient getInitialisedClient() {
		APIClient client = new APIClient("https://sbtreval.testrail.io/");
		client.setUser("sbrown@dakarsoftware.com");
		client.setPassword( "12345");
		return client;
	}
}
