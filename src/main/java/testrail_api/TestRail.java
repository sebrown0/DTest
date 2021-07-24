/**
 * 
 */
package testrail_api;

import com.gurock.testrail.APIClient;

/**
 * @author Brown
 *
 */
public class TestRail {

	private String usrName;
	private String usrPswd;
	
	@SuppressWarnings("unused")
	private TestRail() {}
	
	public TestRail(String usrName, String usrPswd) {
		super();
		this.usrName = usrName;
		this.usrPswd = usrPswd;
	}

	public APIClient getInitialisedClient() {
		APIClient client = new APIClient("https://sbtreval.testrail.io/");
		client.setUser(this.usrName);
		client.setPassword(this.usrPswd);
		return client;
	}
}
