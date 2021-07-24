package app;

import com.gurock.testrail.APIClient;

import testrail_api.TestRail;

public class Application {

	public static void main(String[] args) {
		TestRail api = new TestRail("sbrown@dakarsoftware.com", "12345");
		APIClient client = api.getInitialisedClient();
		System.out.println(client.toString());		
	}

}
