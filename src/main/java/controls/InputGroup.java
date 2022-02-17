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

import control_builder.ControlGetter;
import control_builder.ControlGetterButton;
import control_builder.control_data.ControlData;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class InputGroup implements Control {
	private WebDriver driver;
	private By findBy;
	private WebElement prnt;
	private List<ControlData> controlData = new ArrayList<>();
	private CoreData coreData;
	private Control currentControl;
	
	public InputGroup(CoreData coreData, By findBy) {
		this.coreData = coreData;
		this.driver = coreData.getWebDriver();
		this.findBy = findBy;
		
		setParent();
	}	

	private void setParent() {
		prnt = driver.findElement(findBy);
	}
	
	public InputGroup addElement(String name, By findBy) {
		WebElement btn = prnt.findElement(findBy);
		controlData.add(new ControlData(name, new ControlGetterButton(coreData, findBy, btn)));
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
		// TODO Auto-generated method stub
		return false;
	}
	
//	public List<ControlData> getControlData(){
//		return controlData;
//	}	
	public CoreData getCoreData() {
		return coreData;
	}
	public By getFindBy() {
		return findBy;
	}

//private void setElements() {
//	elements = prnt.findElements(By.cssSelector("div[class='input-group-append']"));
//	elements.forEach(e -> {
//		System.out.println("->" + e.getAttribute("title"));
//		e.click();
//	});
//}
//	public Optional<WebElement> getElementByTitle(String title) {
//		return 
//			elements
//				.stream()
//				.filter(e -> e.getAttribute("title").equals(title))
//				.findFirst();
//	}
	
}
