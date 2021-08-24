package reporting.strategy;

import exceptions.InvalidReportWriter;

/**
 * @author Steve Brown
 *
 * Get a result writer to that will write the test results to
 * the place specified in the config.xml file.
 * 
 * If the writer is unavailable the default (console) is used.
 */
public class ReportingStrategyFactory {
	
	public static ResultWriter getResultWriter(String strategyName, String logDir, String testSuiteName) {
		ResultWriter writer = null;

		if(strategyName.equalsIgnoreCase("log")) {
			writer = new LogWriter(logDir, testSuiteName);
		}else if(strategyName.equalsIgnoreCase("console")){
			writer = new ConsoleWriter();			
		}else if(strategyName.equalsIgnoreCase("testrail")){
			writer = new TestRailWriter();			
		}		
		return checkWriter(writer);
	}
	
	private static ResultWriter checkWriter(ResultWriter writer) {
		ResultWriter checkedWriter;
		try {
			writer.checkWriter();
			checkedWriter = writer;
		} catch (InvalidReportWriter e) {
			checkedWriter = getDefaultWriter();
		}
		return checkedWriter;
	}
	
	private static ResultWriter getDefaultWriter() {
		return new ConsoleWriter(); 
	}
}
