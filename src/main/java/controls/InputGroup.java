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
import control_builder.ControlGetterButton;
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
//	private List<WebElement> elements;
//	private List<ControlGetterButton> cntrls;
	private List<ControlData> controlData = new ArrayList<>();
	private CoreData coreData;
	
	public InputGroup(CoreData coreData, By findBy) {
		this.coreData = coreData;
		this.driver = coreData.getWebDriver();
		this.findBy = findBy;
		
		setParent();
	}
	
	
	public InputGroup addElement(String name, By findBy) {
		WebElement btn = prnt.findElement(findBy);
		controlData.add(new ControlData(name, new ControlGetterButton(coreData, findBy, btn)));
		return this;
	}
	
//	public InputGroup addElement(String name, ControlGetter cntrl) {
//		controlData.add(new ControlData(name, cntrl));
//		return this;
//	}
		
	public List<ControlData> getControlData(){

		return controlData;
	}
	
	//new ControlData("employee_list", new ControlGetterButton(coreData, By.cssSelector("i[class='fa fa-list']"))),
	private void setParent() {
		prnt = driver.findElement(findBy);
	}
//	private void setElements() {
//		elements = prnt.findElements(By.cssSelector("div[class='input-group-append']"));
//		elements.forEach(e -> {
//			System.out.println("->" + e.getAttribute("title"));
//			e.click();
//		});
//	}
	public Optional<ControlGetter> getElementByTitle(String title) {
		return 
			controlData
				.stream()
				.filter(e -> e.getCntrlName().equals(title))
				.map(c -> c.getControlGetter())
				.findFirst();
	}
//	public Optional<WebElement> getElementByTitle(String title) {
//		return 
//			elements
//				.stream()
//				.filter(e -> e.getAttribute("title").equals(title))
//				.findFirst();
//	}

	@Override
	public boolean isAvailable() {
		// TODO Auto-generated method stub
		return false;
	}

	public CoreData getCoreData() {
		return coreData;
	}

	public By getFindBy() {
		return findBy;
	}
	
}
