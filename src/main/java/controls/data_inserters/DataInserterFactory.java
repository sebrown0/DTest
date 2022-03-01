/**
 * 
 */
package controls.data_inserters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controls.ControlTest;
import site_mapper.jaxb.pom.test_data.TestDataIn;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class DataInserterFactory {
	private static final Logger LOGGER = 
			LogManager.getLogger(DataInserterFactory.class);
	
	public static TestDataInserter getDataInserter(
		ControlTest controlTest, TestDataIn dataIn) {
		
		TestDataInserter dataInsert = null;
		
		var insertWith = dataIn.getInsertWith();
		if(insertWith != null) {
			writeInitialLogMsg(insertWith);			
			Class<?> clazz = getClass(insertWith);
			if(clazz != null) {
				Constructor<?> cnstr = getConstructor(clazz);	
				if(cnstr != null) {
					dataInsert = getDataInserter(cnstr, controlTest);
				}
			}
		}
		return dataInsert;
	}
	
	private static void writeInitialLogMsg(String insertWith) {
		LOGGER.info(
				String.format(
						"Attempting to get DataInserter for [%s]", 
						insertWith) );
	}
	
	private static Class<?> getClass(String insertWith){
		Class<?> clazz = null;
		final String clazzPath = "controls.data_inserters." + insertWith; 
		try {			
			clazz = Class.forName(clazzPath);
		} catch (ClassNotFoundException e) {
			LOGGER.error(String.format("Could not get clazz for path [%s]", clazzPath));
		}	
		return clazz;
	}
	
	private static Constructor<?> getConstructor(Class<?> clazz){
		Constructor<?> cnstr = null; 
		try {
			cnstr = clazz.getConstructor(ControlTest.class, String.class);
		} catch (NoSuchMethodException | SecurityException e) {
			LOGGER.error("Could not get constuctor for clazz");
		}	
		return cnstr;
	}
	
	private static TestDataInserter getDataInserter(Constructor<?> cnstr, ControlTest controlTest) {
		TestDataInserter dataInsert = null; 
		try {
			dataInsert = (TestDataInserter) cnstr.newInstance(controlTest, "Test");
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LOGGER.error("Could not get new instance of clazz");
		}
		return dataInsert;
	}
}
