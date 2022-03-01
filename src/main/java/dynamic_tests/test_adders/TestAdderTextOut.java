/**
 * 
 */
package dynamic_tests.test_adders;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import controls.ControlTest;
import controls.data_inserters.DataInserterFactory;
import controls.data_inserters.TestDataInserter;
import dynamic_tests.test_elements.ElementTestFactory;
import site_mapper.elements.ElementCreation;
import site_mapper.jaxb.pom.test_data.TestDataIn;
import site_mapper.jaxb.pom.test_data.TestDataOut;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial 
 * @since 1.0
 */
public class TestAdderTextOut implements TestAdderWithData {
	private TestDataIn dataIn;
	private TestDataOut dataOut;

	public TestAdderTextOut(ElementCreation el) {
		this.dataIn = el.getTestDataIn();
		//check if needed!!!!!!!!!!!!!!!!!!
		this.dataOut = el.getTestDataOut();
	}

	@Override
	public void addTestsWith(ElementTestFactory testFactory) {
		checkDataIn(testFactory);
		testFactory.createTextCheck(this);
	}
	
	private void checkDataIn(ElementTestFactory testFactory) {		
		if(dataIn != null) {
			getDataInserter(testFactory);
		}
	}
	
	private void getDataInserter(ElementTestFactory testFactory) {
		ControlTest controlTest = testFactory.getControlTest();
		TestDataInserter dataInsert = 
				new DataInserterFactory().getDataInserter(controlTest, dataIn);
	}
	
//	private void getDataInserter(ElementTestFactory testFactory) {
//		ControlTest controlTest = testFactory.getControlTest();
//		var insertWith = dataIn.getInsertWith();
//		if(insertWith != null && insertWith.contains("EmployeeLookupByName")) {
//			Class<?> clazz;
//			Constructor<?> cnstr = null;
//			
//			try {
//				clazz = Class.forName("controls.data_inserters." + insertWith);				
//				cnstr = clazz.getConstructor(ControlTest.class, String.class);				
//			} catch (NoSuchMethodException | SecurityException |ClassNotFoundException e) {
//				
//				e.printStackTrace();
//			}
//			
//			
//			TestDataInserter dataInsert = null;
//			try {
//				dataInsert = (TestDataInserter) cnstr.newInstance(controlTest, "Test");
//			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
//					| InvocationTargetException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			dataInsert.insertData();
//		}
//	}
	
	@Override
	public TestDataIn getTestDataIn() {
		return dataIn;
	}
	@Override
	public TestDataOut getTestDataOut() {
		return dataOut;
	}

}
