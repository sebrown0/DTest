/**
 * 
 */
package object_models.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Steve Brown
 *
 */
public class MenuChecker {	
	private Map<String, Optional<List<String>>> expected;
	private Map<String, Optional<List<String>>> actual;
	private Map<String, Optional<List<String>>> additionalMenu = new HashMap<>();	
	private Map<String, List<String>> additionalSubMenu = new HashMap<>();
	private Map<String, Optional<LinkedList<String>>> missing = new HashMap<>();
	
	public MenuChecker(Map<String, Optional<List<String>>> expected, Map<String, Optional<List<String>>> actual) {
		this.expected = expected;
		this.actual = actual;
		copyExpectedToMissing();
	}

	private void copyExpectedToMissing() {
		expected.entrySet().forEach(set -> {
			String expectedMenuItem = set.getKey();
//			System.out.println("Menu -> " + expectedMenuItem);
			set.getValue().ifPresentOrElse(v -> {
				LinkedList<String> subMenu = new LinkedList<>();
				v.forEach(item -> {
					subMenu.add(item);
				});
				missing.put(expectedMenuItem, Optional.of(subMenu));
			}, new Runnable() {				
				@Override
				public void run() {
					missing.put(expectedMenuItem, null);					
				}
			});
		});
	}
	
	public void checkMenu() {		
		actual.entrySet().forEach( set -> {
			String actualMenuItem = set.getKey();			
			// Get key (top level of the actual menu item)
			if(isInMenu(actualMenuItem)) {
//				System.out.println("Is in menu: " + actualMenuItem);
				if(isParent(actualMenuItem)) {
//					System.out.println("Is parent: " + actualMenuItem);
					// Get children and check
					Optional<List<String>> expectedChildren = expected.get(actualMenuItem);
					// Check that the expected is present
					expectedChildren.ifPresentOrElse(expectedSubMenus -> {
//						System.out.println(actualMenuItem + " found in expected");
						Optional<List<String>> subMenuItems = set.getValue();						
						if(subMenuItems.isPresent()) {
							// The expected menu item has children.
							List<String> actualValues = set.getValue().get();
							List<String> additionalChildren = new ArrayList<>();
							actualValues.forEach(actualSubMenu -> {								
								// Go thru the actual and compare with expected.
								if(expectedSubMenus.contains(actualSubMenu)) {
									// IS a match remove sub-menu item from expected.
									removeActualSubMenuFromExpected(actualMenuItem, actualSubMenu);
								}else {
									// No match. Is additional child.
									additionalChildren.add(actualSubMenu);
								}
								
								// Check if there are additional children.
								// If so add to map.
								if(additionalChildren.size() > 0) {
									additionalSubMenu.put(actualMenuItem, additionalChildren);
								}
								
								// Check if the menu has any sub elements left.
								// If it does then that means the menu has missing sub-menu item(s).
								// Else the the menu has been found and its sub-menu(s) are correct so it can 
								// be removed from missing.
//								System.out.println("Get menu [" + actualMenuItem + "] from missing" );
								if(missing.containsKey(actualMenuItem)) {
									missing.get(actualMenuItem).ifPresent(subMenus -> {
									if(subMenus.size() <= 0 ) {
										removeActualKeyFromExpected(actualMenuItem);
									};
								});}																
							});
						}else {
							// The actual menu item has children but the expected does not.
							additionalSubMenu.put(actualMenuItem, set.getValue().get());
						}
					},
						// The expected menu item has NO children.
							new Runnable() {								
								@Override
								public void run() {
//									System.out.println("Expected has no children, but actual [" +actualMenuItem + "] does");	
									addAdditionalMenuWithSubMenu(actualMenuItem, set.getValue()); 
								}
							}							
					);					
				}else {
					// Is a single entry element, that exists in expected, so remove from expected.
//					System.out.println(actualMenuItem + " Is a single entry element, that exists in expected, so remove from expected.");
					removeActualKeyFromExpected(actualMenuItem);
				}
			}else {
				// The whole menu and sub-menu is additional, copy to additional map				
//				System.out.println(actualMenuItem + " The whole menu and sub-menu is additional, copy to additional map.");
				addAdditionalMenuWithSubMenu(actualMenuItem, set.getValue());
			}
		});
	}
	
	private boolean isInMenu(String name) {
		return expected.containsKey(name) ? true : false; 
	}	
	private boolean isParent(String name) {
		return expected.get(name).isPresent() ? true : false; 
	}
	
	private void addAdditionalMenuWithSubMenu(String menu, Optional<List<String>> subMenu) {
		additionalMenu.put(menu, subMenu);
	}
	
	private void removeActualKeyFromExpected(String key) {
		missing.remove(key);
	}
	
	private void removeActualSubMenuFromExpected(String menu, String subMenu) {		
		Optional<LinkedList<String>> subMenus = missing.get(menu);
		subMenus.ifPresent(m -> {
			m.remove(subMenu);
		});
	}
	
	public List<String> getMissingItems() {
		List<String> missingItems = new ArrayList<>();
		missing.entrySet().forEach(set -> {			
			String menuItem = set.getKey();
			String msg = "[" + menuItem + "] is missing";		
			
			Optional<LinkedList<String>> subMenus = set.getValue();
			if(subMenus != null ) {				
				if(subMenus.isPresent()){
					LinkedList<String> missingSubMenus = set.getValue().get();
					if(missingSubMenus.size() > 0) {
						msg += " sub menu(s) -> [";
						for (String s : missingSubMenus) {
							msg += s + ", ";
						}	
					}				
					msg = msg.substring(0, msg.length()-2) + "]";	
					missingItems.add(msg);
				}
			}else {
				missingItems.add(msg);
			}
		});
		return missingItems;
	}
	
	public List<String> getAdditonalMenuItems() {
		List<String> additionalMenuItems = new ArrayList<>();
		additionalMenu.entrySet().forEach(set -> {
			String msg = "[" + set.getKey() + "] is an additional menu item";			
			Optional<List<String>> subMenus = set.getValue();
			if(!subMenus.isPresent()) {
				additionalMenuItems.add(msg);			
			}
		});		
		return additionalMenuItems;
	}	

	public List<String> getAdditonalMenuAndSubMenuItems() {
		List<String> additionalMenuItemsWithSubMenu = new ArrayList<>();
		additionalSubMenu.entrySet().forEach(set -> {
			String msg = "[" + set.getKey() + "] has additional sub-menu(s) -> [";			
			List<String> subMenus = set.getValue();
			for (String s : subMenus) {
				msg += s + ", ";
			}
			msg = msg.substring(0, msg.length()-2) + "]";
			additionalMenuItemsWithSubMenu.add(msg);
		});
		return additionalMenuItemsWithSubMenu;
	}
}	
