/**
 * 
 */
package controls.data_inserters;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controls.interfaces.ControlTest;
import site_mapper.jaxb.pom.test_data.TestData;
import site_mapper.jaxb.pom.test_data.TestDataItem;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Get an object [TestDataInserter] for inserting data 
 * into a control before a test.
 * 
 * All implementations of [TestDataInserter] must 
 * have a constructor that accepts:
 * 	ControlTest: the parent control, i.e. JsPanel.
 *  String: the data to insert.  
 */
public class DataInserterFactory {
	private static final Logger LOGGER = 
			LogManager.getLogger(DataInserterFactory.class);
		
	public static TestDataInserter getDataInserter(int forListItem, ControlTest controlTest, TestData testData) {
		
//		TestDataInserter dataInsert = null;

		/*
		 * WE HAVE TO KNOW IF IT'S FOR IN OR OUT!!!!!
		 * 
		 * WHO IS CALLING THIS?
		 * CAN THE LIST BE PASSED AND THIS JUST DOES THE INSERT?
		 */
		if(testData != null) {			
				List<TestDataItem> items = testData.getTestDataIn();
				if(items != null && items.size() >= forListItem) {
					TestDataItem testDataItem = items.get(forListItem); 
//					return getDataInserter(testDataItem, controlTest, testData);

				}else {
					//LOG
				}
			}else {
				//LOG
			}
		
		LOGGER.error("Error with data to insert");
		return null;
	}
	
	public static TestDataInserter getDataInserter(TestDataItem testDataItem, ControlTest controlTest) {		
			
			TestDataInserter dataInsert = null;

			if(testDataItem != null) {			 
				var insertWith = testDataItem.getInsertWith();
				if(insertWith != null) {
					writeInitialLogMsg(insertWith);			
					Class<?> clazz = getClass(insertWith);
					if(clazz != null) {
						Constructor<?> cnstr = getConstructor(clazz);	
						if(cnstr != null) {
							dataInsert = getDataInserter(cnstr, controlTest, testDataItem.getValue());
							return dataInsert;
						}
					}
				}	
			}
		
			LOGGER.error("Error with data to insert");
			return null;
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
	
	private static TestDataInserter getDataInserter(
		Constructor<?> cnstr, ControlTest controlTest, String insertValue) {
		
		TestDataInserter dataInsert = null; 
		try {
			dataInsert = (TestDataInserter) cnstr.newInstance(controlTest, insertValue);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			LOGGER.error("Could not get new instance of clazz");
		}
		return dataInsert;
	}
}
