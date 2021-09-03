/**
 * 
 */
package object_models.navigation.left_side_menu;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.MenuMap;
import object_models.helpers.ChildElement;
import object_models.panels.EmployeeDetails;
import object_models.strategies.click.ClickUsingJavaScript;

/**
 * @author Steve Brown
 *
 */
public class LeftMenu {
	private Map<String, WebElement> anchors;	
	private WebDriver driver;
	
	public LeftMenu(WebDriver driver) {
		this.driver = driver;
		try {
			this.anchors = new MenuMap(new LeftMenuFactory(driver)).getAnchors().get();
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public LeftMenu clickParent(String prntName) {
		WebElement e = anchors.get(prntName);			
//		System.out.println("href - " + e.getAttribute("href"));
		e.click();					
		return this;
	}
	
	public ChildElement clickChild(String childName) {
		WebElement e = anchors.get(childName);			
		ClickUsingJavaScript.performClick(driver, e.getAttribute("href"));
		return ChildElementFactory.getChild(childName, driver);
	}
	
	private static class ChildElementFactory{
		public static ChildElement getChild(String childName, WebDriver driver) {
			ChildElement child = null;
			switch (childName) {
			case "Employee Details":
				child = new EmployeeDetails(driver);
				break;

			default:
				break;
			}
			return child;
		}
	}
}
