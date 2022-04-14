package utils_tests;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import helpers.MenuChecker;
import listeners.TestResultLogger;

/**
 * @author Steve Brown
 *
 * Using the data in expected & actual,
 * each test should fail and write the relevant 
 * missing, additional and new menu elements to 
 * the test log.
 * 
 * These test cases are designed to check the 
 * functionality of MenuChecker without having
 * to load the web page. 
 */
@ExtendWith(TestResultLogger.class)
class TitleCheckerTests {
	private static MenuChecker checker;
	
	@SuppressWarnings("unchecked")
	public static Map<String, Optional<List<String>>> expected = 				
		Stream.of(new Object[][] {
			{"Menu Item Without Sub Menus 1", Optional.empty()}, 
			{"Menu Item Without Sub Menus 2", Optional.empty()}, 
			{"Menu Item Without Sub Menus 3", Optional.empty()}, 
			{"Menu Item With Sub Menus A", Optional.of(Arrays.asList("Item A1", "Item A2"))},
			{"Menu Item With Sub Menus B", Optional.of(Arrays.asList("Item B1", "Item B2", "Item B3"))}			
		}).collect(Collectors.toMap(d -> (String) d[0], d -> ((Optional<List<String>>) d[1])));
	
	@SuppressWarnings("unchecked")
	public static Map<String, Optional<List<String>>> actual = 				
		Stream.of(new Object[][] {
			{"New Menu Item Without Sub Menus 1", Optional.empty()},	// Not in expected 
			{"New Menu Item Without Sub Menus 2", Optional.empty()},	// Not in expected
			{"New Menu Item With Sub Menus AA",  Optional.of(Arrays.asList("Item AA1"))}, // Not in expected
			{"Menu Item Without Sub Menus 1", Optional.empty()}, // Correct
			{"Menu Item Without Sub Menus 2", Optional.empty()}, // Correct
			{"Menu Item With Sub Menus A", Optional.of(Arrays.asList("Item A1", "Item A2", "Item A3"))}, // Has additional Item A3 
			{"Menu Item With Sub Menus B", Optional.of(Arrays.asList("Item B1"))}	// Missing B2,B3 
		}).collect(Collectors.toMap(d -> (String) d[0], d -> ((Optional<List<String>>) d[1])));		
	
	@BeforeAll
	public static void setUp() {
		checker = new MenuChecker(expected, actual);
		checker.checkMenu();
	}
				
	@Test
	void checkForMissing() {
		int missing = checker.getMissingItems().size();
		if(missing > 0) {
			fail("Menu has [" + missing + "] missing elements -> " + checker.getMissingItems().toString());
		}
	}
	
	@Test
	void checkForNew() {		
		int nu = checker.getNewMenuItems().size();
		if(nu > 0) {
			fail("Menu has [" + nu + "] new elements -> " + checker.getNewMenuItems().toString());
		}
	}
	
	@Test
	void checkForAdditional() {		
		int nu = checker.getAdditonalMenuAndSubMenuItems().size();
		if(nu > 0) {
			fail("Menu has [" + nu + "] additional elements -> " + checker.getAdditonalMenuAndSubMenuItems().toString());
		}
	}	
}
