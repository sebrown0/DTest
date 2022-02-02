/**
 * 
 */
package app.arguments;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import app.test_reader.IncludedTestsReader;
import app.test_reader.TestPackage;
import exceptions.InvalidArgumentException;
import object_models.modules.helpers.Modules;
import providers.XMLFileProvider;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 * Checks that the arguments provided on the cmnd line are correct.
 * If any of the args are invalid an empty Optional<AppArguments> is returned.
 * This will cause execution to stop.
 */
public class ArgumentChecker {
	private String moduleName;
	private String testFileName;
	private String testFilePath;	
	private Logger logger = LogManager.getLogger();
	private TestPackage testPackage;
	private IncludedTestsReader reader = null;
	private String[] args;
	
	public ArgumentChecker withArgs(String[] args) throws InvalidArgumentException{
		this.args = args;
		if(args.length != 2) {
			throw new InvalidArgumentException(getErrMsg());
		}else {
			moduleName = capitalliseFirstChar(args[0]);
			testFileName = capitalliseFirstChar(args[1]);	
		}
		testFilePath = XMLFileProvider.INCLUDE_TESTS_FILE_PATH + "\\" + testFileName + ".xml";
		return this;
	}
	
	private String capitalliseFirstChar(String arg) {
		String result = "";
		try {
			result = arg.substring(0,1).toUpperCase() + arg.substring(1);	
		} catch (Exception e) {
			logger.error("Error capitallising arg");
		}
		
		return result;
	}
	
	public Optional<AppArguments> getCheckedArgs() throws InvalidArgumentException {
		if(allArgsOk()) {
			return Optional.of(new AppArguments(moduleName, testFileName, testPackage));
		}else {
			throw new InvalidArgumentException(getErrMsg());
		}
	}
	
	private String getErrMsg() {
		return 
				"Arguments must be -> 1.module name 2.test file name (XML) " + 
				"Supplied: 1 = " + args[0] + " " +
				"2 = " + args[1];
	}
	
	private boolean allArgsOk() {
		return (
				isModuleOk() && 
				isTestFileOk() && 
				isTestAndModuleCompatible()) == true ? true : false;
	}
	
	private boolean isModuleOk() {
		boolean isOk = true;
		if(!Modules.validModules.contains(moduleName)) {
			logger.error("[" + moduleName + "] is an invalid module name");
			isOk = false;
		}
		return isOk;
	}
	
	private boolean isTestFileOk() {
		boolean isOk = true;		
		if(Files.notExists(Paths.get(testFilePath))) {
			logger.error("Could not find test file XML [" + testFilePath + "]");
			isOk = false;
		}else {
			reader = new IncludedTestsReader(testFilePath);
		}
		return isOk;
	}
	
	private boolean isTestAndModuleCompatible() {
		return checkTestAndModule() == true ? true : false;
	}
	
	private boolean checkTestAndModule() {
		boolean isOk = false;		
		if(!(reader == null)) {
			testPackage = reader.getPackage();
			String applicable = testPackage.getApplicable();
			if(applicable.equalsIgnoreCase("all") || applicable.equalsIgnoreCase(moduleName)) {				
				isOk = true;
			}else {
				logger.error(
						"Test file [" + testFileName + "] " +
						"with applicable = [" + testPackage.getApplicable() + "] " +
						"is not compatible with module [" + moduleName + "]");
			}
		}
		return isOk;
	}
	
}
