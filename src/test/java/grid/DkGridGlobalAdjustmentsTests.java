package grid;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebElement;

import controls.ComboWriteAndSelect;
import enums.GridButtonNames;
import enums.control_names.EmployeeControlNames;
import enums.control_names.GlobalAdjustmentControlNames;
import logging.TestResultLogger;
import object_models.date_picker.DatePickerPopup;
import object_models.dk_grid.Cell;
import object_models.dk_grid.CellChecker;
import object_models.dk_grid.DkGridContent;
import object_models.dk_grid.DkGridToolBar;
import object_models.dk_grid.Row;
import object_models.element.ElementButton;
import object_models.element.ElementInput;
import object_models.helpers.TestFail;
import object_models.left_menu.payroll.GlobalAdjustments;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

/**
 * <p>
 * Test DKGrid using the grid in Global Payroll Adjustments.
 * </p>
 * <p>
 * The tests should be run in order as they go
 * through the process of creating the grid to getting
 * and using grid elements.
 * </p>
 * 
 * @since 1.0
 * @author SteveBrown
 * @version 1.0
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
public class DkGridGlobalAdjustmentsTests {	
	private static HomePage homepagePayroll;
	private static DkGridToolBar toolbar;
	private static GlobalAdjustments globalAdjustments;
	
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		homepagePayroll = userLogin.loginValidUser(UserProvider.userPortal());
		globalAdjustments =	(GlobalAdjustments) homepagePayroll.getLeftMenu().clickAndLoad(GlobalAdjustments.class).get();
	}		
		
	@Test @Order(1)
	void loadGrid_implicitPass_ifCompletes() {		
		globalAdjustments.getGrid();		
	}
	
	@Test @Order(2)
	void checkToolBar() {
		toolbar = globalAdjustments.getGrid().getToolBar();
		assertFalse(toolbar == null);
	}
		
	@Test @Order(3)
	void checkAddRecordIsPresent() {
		checkButtonIsPresent(GridButtonNames.BTN_ADD);		
	}

	@Test @Order(4)
	void checkDeleteRecordIsPresent() {
		checkButtonIsPresent(GridButtonNames.BTN_DELETE);		
	}
	
	@Test @Order(5)
	void checkSaveIsPresent() {
		checkButtonIsPresent(GridButtonNames.BTN_SAVE);
	}
		
	@Test @Order(6)
	void checkDownloadToCsvIsPresent() {		
		checkButtonIsPresent(GridButtonNames.BTN_DOWNLOAD_TO_CSV);		
	}
		
	@Test @Order(7)
	void checkUploadIsPresent() {		
		checkButtonIsPresent(GridButtonNames.BTN_UPLOAD);
	}
	
	@Test @Order(8)
	void checkSaveGridStateIsPresent() {		
		checkButtonIsPresent(GridButtonNames.BTN_SAVE_GRID_STATE);
	}

	@Test @Order(9)
	void checkRefreshGridStateIsPresent() {
		checkButtonIsPresent(GridButtonNames.BTN_REFRESH_GRID_STATE);
	}
	
	@Test @Order(10)
	void checkResetGridStateIsPresent() {		
		checkButtonIsPresent(GridButtonNames.BTN_RESET_GRID_STATE);
	}
	
	private void checkButtonIsPresent(GridButtonNames btnName) {
		Optional<ElementButton> btn = toolbar.getButton(btnName.getName());
		btn.ifPresentOrElse(
				b ->  assertTrue(true), 
				new TestFail(btnName.getName() + " is not present")
		);
	}
	
	@Test @Order(11)
	void checkOverallFilterIsPresent() {
		Optional<ElementInput> filter = toolbar.getOverallFilter();
		filter.ifPresentOrElse(
				f ->  assertTrue(true),  
				new TestFail("Overall filter is not present")
		);		
	}

	@Test @Order(12)
	void getContent_implictPass_ifCompletes() {
		DkGridContent<?> content = globalAdjustments.getGrid().getContent();
		assertTrue(content != null);
	}
	
	@Test @Order(13)
	void checkContentForRow1() {
		Optional<Row<?>> row1 = globalAdjustments.getRowForRowIndex(1);		
		assertEquals(1, row1.get().getRowIdx().intValue());		
	}
	
	@Test @Order(14)
	void loadEmployee_implictPass_ifCompletes() {
		ComboWriteAndSelect cmbComp = (ComboWriteAndSelect) globalAdjustments.getControl(EmployeeControlNames.EMPLOYEES).get();
		cmbComp.click();		
		cmbComp.selectFullText("Simpson Homer");
		cmbComp.click();		
		
		globalAdjustments.clickButton(GlobalAdjustmentControlNames.ACCEPT_CRITERIA);		
	}
		
	@Test @Order(15)
	void checkRowNumber_WithKey() {
		Optional<Integer> rowIdx = globalAdjustments.getRowNumForKeyValue("Simpson Homer - (0134213A)");		
		assertTrue(rowIdx.get().intValue() >= 0);		
	}

	@Test @Order(16)
	void setDateTo() {				
		Row<?> row = globalAdjustments.getRowForRowIndex(0).get();
		Cell cell = globalAdjustments.getGrid().getCell(row, "Date To");
		WebElement e = cell.getMyElement();		
		CellChecker checker = new CellChecker(homepagePayroll.getWebDriver(), e);
		DatePickerPopup picker = (DatePickerPopup) checker.getPopupType();		
		picker.getDatePicker("inline").setDate("01/01/2021");
		assertEquals("01/01/2021", cell.getCurrentValue().get());
	}
	
	@Test @Order(18)
	void filterEmployeeColumn_shouldNotReturn_rowIdx() {		
		globalAdjustments.filterGridColumn("Employee", "Z_7Z)3K");
		Optional<Integer> rowIdx = globalAdjustments.getRowNumForKeyValue("Simpson Homer - (0134213A)");		
		assertEquals(Optional.empty(), rowIdx);
	}
	
	
	@AfterAll
	public static void tearDown() {			
//		homepagePayroll.close();
	}
	
}