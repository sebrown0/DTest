/**
 * 
 */
package dynamic_tests.test_adders;

import java.util.Optional;

import org.junit.jupiter.api.DynamicTest;

import controls.Control;
import controls.ControlTest;
import site_mapper.elements.ElementDetails;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public abstract class XXTestAdder {
	protected String elName;
	protected ElementDetails elDetails;
	protected ControlTest controlTest;
	
	private Control cntrl;

	public abstract DynamicTest addTest();

	public XXTestAdder setElementName(String elName) {
		this.elName = elName;
		return this;
	}
	public XXTestAdder setCntrl(Control cntrl) {
		this.cntrl = cntrl;
		return this;
	}
	public XXTestAdder setElementDetails(ElementDetails e) {
		this.elDetails = e;
		return this;
	}
	public XXTestAdder setControlTest(ControlTest controlTest) {
		this.controlTest = controlTest;
		return this;
	}
	protected Optional<Control> getControl(){
		return Optional.ofNullable(cntrl);
	}
}
