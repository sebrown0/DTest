/**
 * 
 */
package control_mapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.openqa.selenium.WebElement;

import object_models.left_menu.common.LeftMenuFactory;

/**
 * @author Steve Brown
 *
 */
public class MenuMap {
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	private Map<String, WebElement> anchors = new HashMap<>();
	private LeftMenuFactory lmf;
	
	public MenuMap(LeftMenuFactory leftMenuFactory) {
		this.lmf = leftMenuFactory;
	}

	public Future<Map<String, WebElement>> getAnchors() {
		return executor.submit(() -> {
			List<WebElement> elements = lmf.getList();
			for (WebElement e: elements) { 	
				addAnchor(e.getAttribute("textContent"), e);
			}
			return anchors;
		});
	}
	
	private void addAnchor(String name, WebElement el) {
		if(name != null && name != "") {
			anchors.put(formattedNameAsKey(name), el);
//			System.out.println("adding anchor - " + formattedNameAsKey(name));
		}		
	}
		
	private String formattedNameAsKey(String name) {
		return name.trim().substring(1);
	}
	
	public String getHref(String name) {
		return anchors.get(name).getAttribute("textContent");
	}
	
}
