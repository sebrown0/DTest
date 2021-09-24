/**
 * 
 */
package object_models.dk_grid;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import object_models.element.ElementButton;
import object_models.element.ElementInput;
import object_models.element.ElementPointInTime;

/**
 * @author Steve Brown
 *
 */
public class DkGridToolBarReader {
	private WebDriver driver;
	private WebElement toolbarElement;
	private Logger logger = LogManager.getLogger();	
	private DkGridToolBar toolBar;	
	
	public DkGridToolBarReader(WebDriver driver, DkGridToolBar toolBar) {
		this.driver = driver;
		this.toolBar = toolBar;
		setToolbarElement();
	}

	private void setToolbarElement() {		
		toolbarElement = driver.findElement(By.id("dkrGrid-toolbar"));
	}
	
	public void read() {
		checkForAndMapCommonButtons();
		checkForAndMapExtraButtons();
		checkForAndMapInputs();
	}
	
	private void checkForAndMapCommonButtons() {
		List<WebElement> btnGroups = toolbarElement.findElements(By.cssSelector("div[class='btn-group']"));
		for (WebElement btnGroup : btnGroups) {
			mapButtonsInGroup(btnGroup);
		}
	}
	
	private void mapButtonsInGroup(WebElement btnGroup) {
		btnGroup.findElements(By.xpath("./button")).forEach(b -> {
			mapButton(b);
		});
	}
	
	private void mapButton(WebElement b) {
		ElementButton btn = new ElementButton(b);
		String key = btn.getElementKey();		
		if(key != null && key.length() > 0 && !key.equalsIgnoreCase("none")) {
			logger.info("Button [" + key + "] found in DK Grid toolbal");
			toolBar.addButton(btn);	
		}
	}
	
	private void checkForAndMapExtraButtons() {
		checkForEmployeeView().ifPresent(btn -> mapButton(btn) );		
		checkForHitList().ifPresent(btn -> mapButton(btn) );		
	}
	
	private void checkForAndMapInputs() {		
		checkForOverallFilter().ifPresent(e -> { 
			ElementInput ip = new ElementInput(e);
			toolBar.setOverallFilter(ip);
		} );
		
		checkForPointInTime().ifPresent(pit -> {
			ElementPointInTime pointInTime = new ElementPointInTime(driver, pit);
			toolBar.setPointInTime(pointInTime);
		});		
	}
	
	private Optional<WebElement> checkForOverallFilter() {
		return checkForElement(By.xpath("//div/input[@type='text' and @placeholder='Overall Filter...']"));		
	}	
	
	private Optional<WebElement> checkForEmployeeView() {
		return checkForElement(By.xpath("//div/div/button[@title='Employee View']"));
	}	

	private Optional<WebElement> checkForPointInTime() {
		return checkForElement(By.id("PIT"));
	}

	private Optional<WebElement> checkForHitList() {
		By hitlistLocator = By.xpath("//button[@class='btn btn-light' and contains(., ' Create HitList')]");
		return checkForElement(hitlistLocator);
	}
	
//	private Optional<WebElement> checkForHitList() {
//		return checkForElement(By.cssSelector("#dkrGrid-toolbar > div:nth-child(4) > div > button"));
//	}
	
	
	private Optional<WebElement> checkForElement(By byLocator) {
		WebElement element = null;
		try {
			element = toolbarElement.findElement(byLocator);
		} catch (NoSuchElementException e) {
//			System.out.println("->+" + byLocator.toString());
			return Optional.ofNullable(element);
		}
		return Optional.of(element);		
	}
	
}
