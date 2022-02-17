/**
 * 
 */
package controls;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import control_builder.ControlData;
import control_builder.ControlGetter;
import control_builder.ControlGetterTab;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TabGroup implements ControlGroup {
	private WebDriver driver;
	private By findBy;
	private WebElement prnt;
	private List<ControlData> controlData = new ArrayList<>();
	private CoreData coreData;
	private Control currentControl;
	
	public TabGroup(CoreData coreData, By findBy) {
		this.coreData = coreData;
		this.driver = coreData.getWebDriver();
		this.findBy = findBy;
		
		setParent();
	}	

	private void setParent() {
		prnt = driver.findElement(findBy);
	}
	
	public TabGroup add(String name, By findBy) {
		WebElement tab = prnt.findElement(findBy);
		controlData.add(new ControlData(name, new ControlGetterTab(coreData, findBy, tab)));
		return this;
	}
				
	@Override //ControlGroup
	public Optional<Control> getControlByTitle(String title) {
		currentControl = null;
		getElementByTitle(title).ifPresent(e -> {
			currentControl = e.getControl();
		});
		return Optional.ofNullable(currentControl);
	}
	
	private Optional<ControlGetter> getElementByTitle(String title) {
		return 
			controlData
				.stream()
				.filter(e -> e.getCntrlName().equals(title))
				.map(c -> c.getControlGetter())
				.findFirst();
	}
	
	public CoreData getCoreData() {
		return coreData;
	}
	public By getFindBy() {
		return findBy;
	}
}
