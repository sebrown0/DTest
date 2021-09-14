/**
 * 
 */
package object_models.helpers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

	/*
	 * The map missing is used to store any missing
	 * items from expected.
	 * 
	 * When an item is found it is removed from missing.
	 * 
	 * Thus, after all the iterations of actual any items
	 * left in missing have not been found and are demeed
	 * missing from the expected menu.
	 */			
	private void copyExpectedToMissing() {
		expected.entrySet().forEach(set -> {
			String expectedMenuItem = set.getKey();
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
		
		
	/*
	 * Check the actual against the expected.
	 * If an menu item or sub-menu  is found it's removed from missing.
	 * 
	 * If a menu item (top level) does NOT exist in expected, it and
	 * any sub-menus it contains are added to additionalMenu.
	 * 
	 * If a menu item (top level) DOES exist in expected, but it has
	 * additional sub-menu(s) they are added to additionalSubMenu.
	 * 
	 * If a menu item (top level) DOES exist in expected, but it has
	 * missing sub-menu(s) they are not removed from missing.
	 */
	public void checkMenu() {		
		AdditionalMenu additional = new AdditionalMenu();
		actual.entrySet().forEach( set -> {
			String actualMenuItem = set.getKey();
			// Get key (top level of the actual menu item)
			if(isInMenu(actualMenuItem)) {
				if(isParent(actualMenuItem)) {
					Optional<List<String>> expectedChildren = expected.get(actualMenuItem);
					// Check that expected has sub-menu(s)
					expectedChildren.ifPresentOrElse(expectedSubMenus -> {						
						Optional<List<String>> subMenuItems = set.getValue();						
						if(subMenuItems.isPresent()) {
							// The expected menu item has children, so...						
							compareAcutualAndExpected(actualMenuItem, expectedSubMenus,set);
						}else {
							// The actual menu item has children but the expected does not.
							additionalSubMenu.put(actualMenuItem, set.getValue().get());
						}
					},
						// The expected menu item has NO sub-menu(s), so the actual is an additional sub-menu.							
							additional.with(set)
					);					
				}else {
					// Is a single entry element, that exists in expected, so remove from expected.
					removeActualKeyFromMissing(actualMenuItem);
				}
			}else {
				// The whole menu and sub-menu is additional, copy to additional map				
				additional.with(set).addAdditionalMenu();		
			}
		});
	}

	private class AdditionalMenu implements Runnable {
		Entry<String, Optional<List<String>>> entry;
				
		public AdditionalMenu with(Entry<String, Optional<List<String>>> entry) {					
			this.entry = entry;
			return this;
		}
		
		@Override
		public void run() {
			this.addAdditionalMenu();;
		}

		public void addAdditionalMenu() {
			if(entry.getValue().isPresent()) {
				additionalMenu.put(entry.getKey(), entry.getValue());	
			}else {
				additionalMenu.put(entry.getKey(), Optional.empty());
			}						
		}			
	}
	
	private boolean isInMenu(String name) {
		return expected.containsKey(name) ? true : false; 
	}	
	private boolean isParent(String name) {
		return expected.get(name).isPresent() ? true : false; 
	}
	
	/*
	 * Go through the actual and compare with expected.
	 * If there's a match remove sub-menu item from expected.
	 *  
	 * If NO match. It's an additional child.
	 */
	private void compareAcutualAndExpected(String actualMenuItem,	List<String> expectedSubMenus, Entry<String, Optional<List<String>>> entry) {			
		List<String> actualValues = entry.getValue().get();
		List<String> additionalChildren = new ArrayList<>();
		
		actualValues.forEach(actualSubMenu -> {				
			if(expectedSubMenus.contains(actualSubMenu)) {
				removeActualSubMenuFromMissing(actualMenuItem, actualSubMenu);
			}else {
				additionalChildren.add(actualSubMenu);
			}
			addAdditionalSubMenus(actualMenuItem, additionalChildren);
			removeMenuElementIfHasNoSubMenus(actualMenuItem);
		});
	}
			
	private void removeActualKeyFromMissing(String key) {
		missing.remove(key);
	}
	
	private void removeActualSubMenuFromMissing(String menu, String subMenu) {		
		Optional<LinkedList<String>> subMenus = missing.get(menu);
		subMenus.ifPresent(m -> {	m.remove(subMenu); });
	}

	/* 
	 * Check if there are additional children.
	 * If so add to map.
	 */
	private void addAdditionalSubMenus(String actualMenuItem, List<String> additionalChildren) {
		if(additionalChildren.size() > 0) {
			additionalSubMenu.put(actualMenuItem, additionalChildren);
		}
	}

	/*
	 * Check if the menu has any sub elements left.
	 * If it does then that means the menu has missing sub-menu item(s).
	 * Else the the menu has been found and its sub-menu(s) are correct so it can 
	 * be removed from missing.
	 */
	private void removeMenuElementIfHasNoSubMenus(String actualMenuItem) {
		if(missing.containsKey(actualMenuItem)) {
			missing.get(actualMenuItem).ifPresent(subMenus -> {
			if(subMenus.size() <= 0 ) {
				removeActualKeyFromMissing(actualMenuItem);
			};
		});}
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
						msg += " sub-menu(s) -> [";
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
	
	public List<String> getNewMenuItems() {
		List<String> newMenuItems = new ArrayList<>();
		additionalMenu.entrySet().forEach(set -> {
			String msg = "[" + set.getKey() + "] is a new menu item";						
			if(set.getValue().isPresent()) {
				List<String> subMenus = set.getValue().get();
				msg += ", with sub-menu(s) -> [";
				for (String s : subMenus) {					
					msg += s + ", ";
				}					
				msg = msg.substring(0, msg.length()-2) + "]";
			}
			newMenuItems.add(msg);
		});		
		return newMenuItems;
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

