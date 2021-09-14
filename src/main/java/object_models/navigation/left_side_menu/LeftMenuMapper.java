/**
 * 
 */
package object_models.navigation.left_side_menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * @author Steve Brown
 *
 * Create a map of the Left Menu.
 * 
 */
public class LeftMenuMapper {	
	private List<WebElement> children;	
	private Optional<List<String>> childNames;
	private Map<String, Optional<List<String>>> menu = new HashMap<>();
	
	@FindBy(id = "nav-accordion")
	WebElement nav;
	
	public LeftMenuMapper(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public LeftMenuMapper map() {
		mapSingleElements();
		mapMultipleElements();
		return this;
	}
	
	private void mapSingleElements() {
		List<WebElement> top = nav.findElements(By.cssSelector("li[class='mobile-leftside-hider']"));
		
		List<WebElement> e = top.stream().filter(t -> t.getText().length() > 0 ).collect(Collectors.toList());
		e.forEach(n -> {
			menu.put(n.getText().trim(), Optional.ofNullable(null));
		});		
	}
		
	private void mapMultipleElements() {		
		List<WebElement> parents = nav.findElements(By.cssSelector("li[class='sub-menu dcjq-parent-li']"));		

		parents.forEach(n -> {
			childNames = Optional.of(new ArrayList<>());
			children = n.findElements(By.cssSelector("li[class='mobile-leftside-hider']"));
			children.forEach(c -> { 
				String title = c.getAttribute("textContent").trim();
				title = title.replace(String.valueOf(((char)160)), "");
				childNames.get().add(title);
//				System.out.println("->" + title); 
			});
			menu.putIfAbsent(getParentName(n), childNames);
		});		
	}
	
	private String getParentName(WebElement e) {
		String name;
		WebElement anchor = e.findElement(By.tagName("a"));
		name = anchor.getAttribute("textContent").trim();
		return name.replace(String.valueOf(((char)160)), "");
	}

	public Map<String, Optional<List<String>>> getMenu() {
		return menu;
	}

	public void print() {
		menu.entrySet().forEach(set -> {
			System.out.println();
			System.out.println(set.getKey());
			System.out.println("-----------------");
			set.getValue().ifPresent(v -> { 
				v.forEach( n -> {
					System.out.println(n);
				});
			});
		});
	}
}

//System.out.println("Parent ->" + set.getKey() + " count = " + set.getValue().orElse(new ArrayList<>()).size());