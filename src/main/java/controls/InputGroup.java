/**
 * 
 */
package controls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;

import control_builder.control_data.ControlData;
import control_builder.control_getters.ControlGetter;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class InputGroup implements Control {
	private By findBy;
	private List<ControlData> controlData = new ArrayList<>();	
	private Control currentControl;
	
	public InputGroup(By findBy) {		
		this.findBy = findBy;		
	}	

	public InputGroup addElements(List<ControlGetter> elements) {
		if(elements != null) {
			elements.forEach(v -> {
				addElement(v.getName(), v);
			});
		}
		return this;
	}
	private InputGroup addElement(String name, ControlGetter controlGetter) {
		controlData.add(
				new ControlData(
						controlGetter
								.setParent(findBy)));
		return this;
	}
					
	public Optional<Control> getControlByTitle(String title) {
		currentControl = null;
		getElementByTitle(title).ifPresent(e -> {
			currentControl = e.getControl();
		});
		return Optional.ofNullable(currentControl);
	}
	
	public Optional<ControlGetter> getElementByTitle(String title) {
		return 
			controlData
				.stream()
				.filter(e -> e.getCntrlName().equals(title))
				.map(c -> c.getControlGetter())
				.findFirst();
	}

	@Override
	public boolean isAvailable() {
		System.out.println("InputGroup.isAvailable ** NOT IMPLEMENTED **"); // TODO - remove or log 	
		return false;
	}
		
}
