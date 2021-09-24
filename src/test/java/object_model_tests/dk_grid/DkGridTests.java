package object_model_tests.dk_grid;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import logging.TestResultLogger;
import object_models.date_picker.DatePickerDay;
import object_models.date_picker.DateSetter;
import object_models.dk_grid.DkGridEmployeeDetails;
import object_models.dk_grid.DkGridToolBar;
import object_models.element.ElementButton;
import object_models.element.ElementInput;
import object_models.element.ElementPointInTime;
import object_models.forms.ContainerAction;
import object_models.left_menu.common.LeftMenu;
import object_models.left_menu.employees.EmployeeDetails;
import object_models.pages.HomePage;
import object_models.pages.UserLoginPage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import test_data.UserProvider;
import xml_reader.config_file.ConfigReader;


@ExtendWith({ 
	ConfigParameterResolver.class, 
	TestResultLogger.class, 
	LoginPageResolverPayroll.class })
public class DkGridTests {	
	private static HomePage homepagePayroll;
	private static LeftMenu menuPayroll;
	private static DkGridEmployeeDetails empDetailsGrid; // CHANGE TO INTERFACE???
	private static DkGridToolBar toolbar;
		
	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		homepagePayroll = userLogin.loginValidUser(UserProvider.userPortal());
		menuPayroll = homepagePayroll.getLeftMenu();
		loadGrid();
	}		
	
	private static void loadGrid() {
		Optional<ContainerAction> obj = menuPayroll
				.clickParent(EmployeeDetails.MENU_PARENT_NAME)
				.clickAndLoad(EmployeeDetails.MENU_TITLE);
		EmployeeDetails empDetails = (EmployeeDetails) obj.get();
		empDetailsGrid = empDetails.showEmpDetailsGrid();
		toolbar = empDetailsGrid.getGrid().getToolBar();
	}
	
	@Test
	void checkSaveIsPresent() {
//		toolbar.getButtons().forEach((k,v) -> System.out.println("->" + k + "<-"));		
		
		Optional<ElementButton> save = toolbar.getButton(DkGridToolBar.BTN_SAVE);
		save.ifPresentOrElse(
				b ->  assertTrue(true), 
				new TestFail("Save is not present")
		);		
	}
	
	@Test
	void checkDownloadToExcelIsPresent() {		
		Optional<ElementButton> downloadToExcel = toolbar.getButton(DkGridToolBar.BTN_DOWNLOAD_TO_EXCEL);
		downloadToExcel.ifPresentOrElse(				
				b ->  assertTrue(true), 
				new TestFail("Download To Excel is not present")
		);		
	}
	
	@Test
	void checkDownloadToCsvIsPresent() {		
		Optional<ElementButton> downloadToCsv = toolbar.getButton(DkGridToolBar.BTN_DOWNLOAD_TO_CSV);
		downloadToCsv.ifPresentOrElse(
				b ->  assertTrue(true), 
				new TestFail("Download To CSV is not present")
		);		
	}
	
	@Test
	void checkUploadIsPresent() {		
		Optional<ElementButton> upload = toolbar.getButton(DkGridToolBar.BTN_UPLOAD);
		upload.ifPresentOrElse(
				b ->  assertTrue(true), 
				new TestFail("Upload is not present")
		);		
	}

	@Test
	void checkSaveGridStateIsPresent() {		
		Optional<ElementButton> saveGridState = toolbar.getButton(DkGridToolBar.BTN_SAVE_GRID_STATE);
		saveGridState.ifPresentOrElse(
				b ->  assertTrue(true), 
				new TestFail("Save grid state is not present")
		);		
	}

	@Test
	void checkRefreshGridStateIsPresent() {		
		Optional<ElementButton> refreshGridState = toolbar.getButton(DkGridToolBar.BTN_REFRESH_GRID_STATE);
		refreshGridState.ifPresentOrElse(
				b ->  assertTrue(true), 
				new TestFail("Refresh grid state is not present")
		);		
	}
	
	@Test
	void checkResetGridStateIsPresent() {		
		Optional<ElementButton> resetGridState = toolbar.getButton(DkGridToolBar.BTN_RESET_GRID_STATE);
		resetGridState.ifPresentOrElse(
				b ->  assertTrue(true), 
				new TestFail("Reset grid state is not present")
		);		
	}

	@Test
	void checkEmployeeViewIsPresent() {		
		Optional<ElementButton> empView = toolbar.getButton(DkGridToolBar.BTN_EMPLOYEE_VIEW);
		empView.ifPresentOrElse(
				b ->  assertTrue(true),  
				new TestFail("Employee view is not present")
		);		
	}

	@Test
	void checkCreateHitlistIsPresent() {
		Optional<ElementButton> hitlist = toolbar.getButton(DkGridToolBar.BTN_CREATE_HITLIST);
		hitlist.ifPresentOrElse(
				b ->  assertTrue(true), 
				new TestFail("Create hitlist is not present")
		);		
	}
	
	@Test
	void checkOverallFilterIsPresent() {
		Optional<ElementInput> filter = toolbar.getOverallFilter();
		filter.ifPresentOrElse(
				f ->  assertTrue(true),  
				new TestFail("Overall filter is not present")
		);		
	}
	
	@Test
	void checkPointInTimeIsPresent() {
		Optional<ElementPointInTime> pit = toolbar.getPointInTime();
		pit.ifPresentOrElse(
				p ->  { DateSetter datePicker = p.click(); datePicker.setDate("01/01/2016"); },  
				new TestFail("Overall filter is not present")
		);		
	}
	
	private class TestFail implements Runnable {
		private String msg;

		public TestFail(String msg) {
			this.msg = msg;
		}

		@Override
		public void run() {	fail(msg);	}
	}
	
	@AfterAll
	public static void tearDown() {			
//		homepagePayroll.close();
	}
	
}