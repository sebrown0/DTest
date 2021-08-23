package reporting.strategy;

public class ReportingStrategyFactory {
	public static ResultWriter getResultWriter(String strategyName) {
		ResultWriter writer = null;
		
		if(strategyName.equalsIgnoreCase("log")) {
			writer = new LogWriter();
		}else if(strategyName.equalsIgnoreCase("console")){
			writer = new ConsoleWriter();
		}else if(strategyName.equalsIgnoreCase("testrail")){
			writer = new TestRailWriter();
		}
		
		return writer;
	}
}
