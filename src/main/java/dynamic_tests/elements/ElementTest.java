/**
 * 
 */
package dynamic_tests.elements;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;

import controls.ControlTest;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Pass the container that has the control test.
 * @since 1.0
 */
public abstract class ElementTest implements TestElement{	
	private String name;
	private String type;
	private ControlTest controlTest;
	private List<DynamicTest> tests = new ArrayList<>();

	public ElementTest(String type, String name, ControlTest controlTest) {
		this.controlTest = controlTest;
		this.type = type;
		this.name = name;
	}

	public List<DynamicTest> getTests() {
		return tests;
	}
	
	@Override //TestElement
	public String getName() {
		return name;
	}
	@Override //TestElement
	public String getType() {
		return type;
	}
	
	protected ControlTest getControlTest() {
		return controlTest;
	}
}
