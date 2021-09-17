/**
 * 
 */
package object_models.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import utils.StringUtil;

/**
 * @author Steve Brown
 *
 */
public class Menu {
	private MenuItem parent;	
	private Map<String, MenuItem> menuItems = new HashMap<>();
	private List<MenuItem> parents = new ArrayList<>();
	
	public void getSubElements(WebElement we){
		addParent(we);
		addChildren(we);
	}
	
	public void addElement(WebElement anchor) {
		String href = anchor.getAttribute("href");
		MenuItem element = new MenuItem(
				StringUtil.getValueAt(anchor.getAttribute("href"), 2, ","), 
				null, 
				href);
		
		menuItems.put(element.getName(), element);
	}
	
	private void addParent(WebElement we){
		parent = new MenuItem(we.findElement(By.cssSelector("span")).getAttribute("innerHTML"));
		parents.add(parent);
	}
	
	private void addChildren(WebElement we) {
		List<WebElement> mobileLeftSideHiders = we.findElements(By.className("mobile-leftside-hider"));
		MenuItem child;
		String href;
		
		for (WebElement mlsh : mobileLeftSideHiders) {
			WebElement anchor = mlsh.findElement(By.tagName("a"));
			href = anchor.getAttribute("href");
			child = new MenuItem(
					StringUtil.getValueAt(href, 2, ","), 
					parent, 
					href
			);			
			menuItems.put(child.getName(),child);			
		}	
	}
	
	public List<MenuItem> getParents(){
		return parents;
	}
	
	public Map<String, MenuItem> getAllMenuItems(){
		return menuItems;
	}
	
	public MenuItem getMenuItem(String itemName) {
		MenuItem item = menuItems.get(itemName);
		return item;
	}
}
