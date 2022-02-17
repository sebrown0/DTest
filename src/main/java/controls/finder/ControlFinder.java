/**
 * 
 */
package controls.finder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import control_builder.control_data.ControlData;
import control_builder.control_getters.ControlGetter;
import controls.Control;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class ControlFinder {
	private List<ControlData> cntrls = new ArrayList<>();	
	private Control cntrl;
	
	public ControlFinder(List<ControlData> cntrls) {
		this.cntrls = cntrls;
	}

	public Optional<Control> getControlByTitle(String title) {
		cntrl = null;
		getElementByTitle(title).ifPresent(e -> {
			cntrl = e.getControl();
		});
		return Optional.ofNullable(cntrl);
	}
	
	private Optional<ControlGetter> getElementByTitle(String title) {
		return 
			cntrls
				.stream()
				.filter(e -> e.getCntrlName().equals(title))
				.map(c -> c.getControlGetter())
				.findFirst();
	}
}
