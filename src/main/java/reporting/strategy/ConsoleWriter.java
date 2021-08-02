/**
 * 
 */
package reporting.strategy;

import test_result.TestResultGetter;

/**
 * @author Steve Brown
 *
 */
public class ConsoleWriter implements ResultWriter {
	@Override
	public void writeResult(TestResultGetter resultGetter) {
		System.out.println(resultGetter.getResult().toString());
	}
}
