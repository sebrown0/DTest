/**
 * 
 */
package testrail_api;

import java.io.IOException;

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
	
	private String constructPost(String testCaseId) {
		String post = "index.php?/api/v2/add_result/" +	TagParser.getTestCaseNum(testCaseId);
		System.out.println(post);
		return post;
	}
	
	public JSONObject postSingleTest(TestCaseDataGetter dg) {
		JSONObject json = null;		
		
		try {
			json = (JSONObject) client.sendPost(constructPost(dg.testCaseId()), dg.getData());
		} catch (IOException | APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
}
