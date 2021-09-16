/**
 * 
 */
package app;

import java.nio.file.Paths;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import app.test_runner.TestRunner;
import exceptions.InvalidArgumentException;
import providers.XMLFileProvider;

/**
 * @author Steve Brown
 *
 */
public class StartUp {
	private String[] args;	
	private Logger logger = LogManager.getLogger();
	private ExecutorService executor = Executors.newFixedThreadPool(2);
	
	public StartUp(String[] args) {
		this.args = args;		
	}

	public StartUp start() {		
		logWelcomeMessage();		
		return this;
	}	

	public void logWelcomeMessage() {
		String msg = getMsg(args); 
		System.out.println(msg);		
		logger.info(msg);					
	}
	
	private String getMsg(String[] args) {
		return "Running tests with arguments:" +
				"\n [module name = " + args[0] + "]" +
				"\n [test file path = " + getFullPath()  + "]" +
				"\n [test file = " + args[1] + "]";
	}
	
	private String getFullPath() {
		return Paths
				.get(XMLFileProvider.INCLUDE_TESTS_FILE_PATH)
				.toAbsolutePath()
				.normalize()
				.toString();
	}
	
	public StartUp runTests() {
		try {			
			ArgumentChecker checker = new ArgumentChecker();
			Optional<AppArguments> appArgs = checker.withArgs(args).getCheckedArgs();
			appArgs.ifPresentOrElse(
					a -> {
						TestRunner runner = new TestRunner(a);
						runner.runTests();
					},						
					new Runnable() {				
						@Override	public void run() {
							logAndQuit(new LogError("Invalid arguments! Quitting", executor));
						}
					}
			);
		} catch (InvalidArgumentException | ArrayIndexOutOfBoundsException e) {
			logAndQuit(new LogError("Fatal error! Probably due to passed args. Quitting", executor));			
		}
		return this;
	}
	
	private void logAndQuit(LogMessage logError) {		
		FutureTask<String> future = new FutureTask<String>(logError, "Msg Written");
		executor.submit(future);
		while(!future.isDone()) {
			// Block
		}
		logError.quit();	
	}

	private abstract class LogMessage implements Runnable {
		protected String msg;
		protected ExecutorService executor;
		
		public LogMessage(String msg, ExecutorService executor) {
			this.msg = msg;
			this.executor = executor;
			System.out.println(msg);
		}		

		public void quit() {
			executor.shutdown();
			System.exit(0);	
		}
	}
	
	private class LogError extends LogMessage {
		public LogError(String msg, ExecutorService executor) {
			super(msg, executor);
		}
		
		@Override
		public void run() {
			CompletableFuture.runAsync(new Runnable() {				
				@Override
				public void run() {
					logger.error(msg);
				}
			}, executor);
		}		
	}

	private class LogInfo extends LogMessage {
		public LogInfo(String msg, ExecutorService executor) {
			super(msg, executor);
		}
		
		@Override
		public void run() {
			CompletableFuture.runAsync(new Runnable() {				
				@Override
				public void run() {
					logger.info(msg);
				}
			}, executor);
		}		
	}
	
	public void finish() {
		LogMessage logInfo = new LogInfo("-- Finished --", executor);
		FutureTask<String> future = new FutureTask<String>(logInfo, "Msg Written");
		executor.submit(future);
		while(!future.isDone()) {
			// Block
		}
		logInfo.quit();	
	}
}