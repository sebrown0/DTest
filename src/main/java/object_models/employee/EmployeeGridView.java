/**
 * 
 */
package object_models.employee;

import object_models.helpers.Closable;
import object_models.pages.homepage.CoreData;
import object_models.panels.JsPanelWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class EmployeeGridView extends JsPanelWithIFrame implements Closable {
	public static final String PANEL_TITLE = "Employee Details";
	
	public EmployeeGridView(CoreData coreData) {
		super(coreData, PANEL_TITLE);
	}

//	private By byClickLogin = By.cssSelector("[data-original-title='Employee Grid View']");	
	
//	public EmployeeGridView(WebDriver driver) {
//		this.driver = driver;		
//	}

//	@Override
//	public void clickElement(WebElement webElem) {		
//		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//		System.out.println("Clicking");
//		webElem.findElement(By.className("nav-link")).click();
//		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
//		System.out.println("Quitting");
		
//		WebElement clickElem;
//		webElem.click();
//		System.out.println("->" + webElem.findElement(By.className("nav-link")).toString());// .getAttribute("class"));
//		clickElem = wait.until(ExpectedConditions.elementToBeClickable(webElem.findElement(By.className("nav-link"))));
//		clickElem.click();
//		webElem.findElement(By.className("nav-link")).click();
//		System.out.println("->" + driver.findElement(byClickLogin).toString());// .click();
//		System.out.println("->" + driver.findElement(byClickLogin).getTagName());// .click();
//	}

//	@Override
//	public String getOriginalName() {
//		return "Employee Grid View";
//	}
//
//	@Override
//	public By getLocator() {
//		// TODO Auto-generated method stub
//		return null;
//	}
}
