/**
 * 
 */
package testrail_api;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.gurock.testrail.APIClient;
import com.gurock.testrail.APIException;

import tags.TagParser;

/**
 * @author Steve Brown
 *
 */
public class MyTestRailAPI {
	APIClient client;

	@SuppressWarnings("unused")
	private MyTestRailAPI() {}
	
	public MyTestRailAPI(APIClient client) {
		super();
		this.client = client;
	}
	
	private String constructPost(String postType, String id) {
		return postType + "/" +	id;
	}
	
	public JSONObject postSingleTest(TestCaseDataGetter dg) {
		JSONObject json = null;		
		
		try {
			json = (JSONObject) client.sendPost(constructPost("add_result", TagParser.getTestNum(dg.testCaseId())), dg.getData());
		} catch (IOException | APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	public JSONArray getTest(TestCaseDataGetter dg) {
		JSONArray json = null;
		
		try {
			json = (JSONArray) client.sendGet(constructPost("get_results", dg.testCaseId()));
		} catch (IOException | APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	public APIClient getClient() {
		return client;
	}
}
