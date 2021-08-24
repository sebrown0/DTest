package test_result;

import java.util.Optional;

import org.apache.logging.log4j.Level;

import factories.TestStatusFactory;
import testrail_api.TestCaseData;

/**
 * @author SteveBrown
 * A test result type.
 * Sets the data specific to a disabled test.
 */
public class ResultDisabled implements TestResultSetter{
	private Optional<String> disabledReason;
		
	
	public ResultDisabled(Optional<String> disabledReason) {
		this.disabledReason = disabledReason;
	}

	@Override
	public void setResult(TestCaseData data) {
		data.setStatus(TestStatusFactory.blocked());
		data.setComment("Test disabled for reason [" + disabledReason.get() + "]");
		data.setLogLevel(Level.forName("IGNORED", 0));
	}
	
}