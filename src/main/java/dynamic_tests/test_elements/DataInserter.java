/**
 * 
 */
package dynamic_tests.test_elements;

import library.common.controls.interfaces.Control;
import library.common.controls.interfaces.ControlTest;

//import controls.interfaces.Control;
//import controls.interfaces.ControlTest;

//import controls.interfaces.Control;
//import controls.interfaces.ControlTest;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public interface DataInserter {
	DataInserter setControlTest(ControlTest controlTest);
	DataInserter setControl(Control control);
	DataInserter setData(Object data);
	void insertData();
}
