package utils_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import object_models.helpers.MenuChecker;

/**
 * @author Steve Brown
 *
 */
class TitleCheckerTests {
	private static MenuChecker checker;
	
	@SuppressWarnings("unchecked")
	public static Map<String, Optional<List<String>>> expected = 				
		Stream.of(new Object[][] {
			{"Menu Item Without Sub Menus 1", Optional.empty()}, // Correct T
			{"Menu Item Without Sub Menus 2", Optional.empty()}, // Correct T
			{"Menu Item Without Sub Menus 3", Optional.empty()}, // Missing from actual X
			{"Menu Item With Sub Menus A", Optional.of(Arrays.asList("Item A1", "Item A2"))}, // Has additional Item A3 T
			{"Menu Item With Sub Menus B", Optional.of(Arrays.asList("Item B1", "Item B2", "Item B3"))}	// Missing B2,B3 T		
		}).collect(Collectors.toMap(d -> (String) d[0], d -> ((Optional<List<String>>) d[1])));
	
	@SuppressWarnings("unchecked")
	public static Map<String, Optional<List<String>>> actual = 				
		Stream.of(new Object[][] {
			{"Additional Menu Item Without Sub Menus 1", Optional.empty()},	// Not in expected T
			{"Additional Menu Item Without Sub Menus 2", Optional.empty()},	// Not in expected T
			{"Additional Menu Item With Sub Menus AA",  Optional.of(Arrays.asList("Item AA1"))}, // Not in expected T
			{"Menu Item Without Sub Menus 1", Optional.empty()}, // Correct T
			{"Menu Item Without Sub Menus 2", Optional.empty()}, // Correct T
			{"Menu Item With Sub Menus A", Optional.of(Arrays.asList("Item A1", "Item A2", "Item A3"))}, // Has additional Item A3 T
			{"Menu Item With Sub Menus B", Optional.of(Arrays.asList("Item B1"))}	// Missing B2,B3 T			
		}).collect(Collectors.toMap(d -> (String) d[0], d -> ((Optional<List<String>>) d[1])));		
	
	@BeforeAll
	public static void setUp() {
		checker = new MenuChecker(expected, actual);
		checker.checkMenu();
	}
				
	@Test
	void checkMissing() {
		assertEquals(2,checker.getMissingItems().size());
		checker.getMissingItems().forEach(x -> System.out.println(x));
	}
	
	@Test
	void checkAdditionalMenus() {
		assertEquals(2,checker.getAdditonalMenuItems().size());
		checker.getAdditonalMenuItems().forEach(x -> System.out.println(x));
	}
	
	@Test
	void checkAdditionalMenusWithSubMenus() {
		assertEquals(1,checker.getAdditonalMenuAndSubMenuItems().size());
		checker.getAdditonalMenuAndSubMenuItems().forEach(x -> System.out.println(x));
	}	
}
