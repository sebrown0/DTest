
package logging;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Filter.Result;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.filter.LevelRangeFilter;
import org.apache.logging.log4j.core.layout.PatternLayout;

import test_result.TestResultGetter;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * @params
 *  logDir: where the log is kept. Specified in config.xml.
 *  testSuiteName: forms part of the name of the log result file.
 *  
 * Configure the Logger.
 * 
 * Write the result to the test log in the format
 * specified in constructLogMsg().
 * 
 */
public class TestLog {
	private Logger logger;
	private String testSuiteName;
	private String logDir;
	
	public TestLog(String logDir, String testSuiteName) {
		this.testSuiteName = testSuiteName;
		this.logDir = logDir;
		configureLog();
	}
	
	private void configureLog() {
		resetContext();
		setLogger();
	}
		
	private void resetContext() {
		final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
		final Configuration config = ctx.getConfiguration();
		
		final PatternLayout layout = PatternLayout.newBuilder()
				.withConfiguration(config)
				.withPattern("%d, [%p] %msg; %n")
				.build(); 
		
		final LevelRangeFilter filter = LevelRangeFilter
				.createFilter(
						Level.getLevel("FAILED"), Level.getLevel("IGNORED"), Result.ACCEPT, Result.DENY);
		
		final Appender appender = FileAppender.newBuilder()
				.setConfiguration(config)
				.setName("test_appender")
				.setFilter(filter)
				.setLayout(layout)
				.withFileName(logDir + "/" + getFileName())				
				.build();
		
		appender.start();
		config.addAppender(appender);
		
		AppenderRef ref = AppenderRef.createAppenderRef("test_appender", null, null);
		AppenderRef[] refs = new AppenderRef[] { ref };
		LoggerConfig loggerConfig = LoggerConfig.createLogger(false, Level.getLevel("IGNORED"), "test_log", "true", refs, null, config, null);
		loggerConfig.addAppender(appender, Level.ALL, null);
		config.addLogger("test_log", loggerConfig);
		ctx.updateLoggers();
	}
	
	
	private void setLogger() {
		logger = LogManager.getLogger("test_log");
	}
	
	private String getFileName() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy_HH;mm;ss");
		return testSuiteName + "_" + now.format(formatter) + ".txt";
	}
	
	public void writeTestResultToLog(TestResultGetter resultGetter) {
		logger.log(resultGetter.getLogLevel(), constructLogMsg(resultGetter));
	}
	
	private String constructLogMsg(TestResultGetter resultGetter) { 	
		return 	
				"[TEST: " + 
				resultGetter.getTestName() + "], [ID: " + 
				resultGetter.getTestIds() + "], [COMMENTS: " +
				resultGetter.getTestComments() + "]";
	}
}
