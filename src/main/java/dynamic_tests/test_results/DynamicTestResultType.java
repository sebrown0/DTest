/**
 * 
 */
package dynamic_tests.test_results;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class DynamicTestResultType implements DynamicTestResult {
	private DynamicTestData data;
		
	public DynamicTestResultType(DynamicTestData data) {
		this.data = data;
	}

	@Override
	public String getExpected() {		
		return data.getExpectedResult();
	}

	@Override
	public String getActual() {
		return data.getActualResult();
	}


}
