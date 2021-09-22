package object_models.left_menu.payroll;

import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import controls.ControlCombo;
import controls.MapControl;
import controls.MappingStrategy;
import controls.PageMap;
import controls.PageMapper;
import object_models.forms.FormWithIFrame;

/**
 * @author Steve Brown
 *
 */
public class InitialisePayroll extends FormWithIFrame {	
	public static final String MENU_TITLE = "Initialise Payroll";
	public static final String PANEL_TITLE = MENU_TITLE;
	public static final String MENU_PARENT_NAME = "Payroll";
	public static final String SELECT_COMP = "SelectComp";
	public static final String SELECT_PAY_GROUP = "SelectPayg";
	public static final String SELECT_PAY_PERIOD = "SelectPays";
	
	private PageMapper mapper;
	private PageMap pageMap;
	
	public InitialisePayroll(WebDriver driver) {
		super(driver, PANEL_TITLE, "_iframex-DEFAULT");
		
		mapper = new PageMapper(new MappingStrategyInitPayroll());
		pageMap = mapper.mapControls().getPageMap();
	}
		
	public String getIframeTitle() {
		WebElement e = driver.findElement(By.cssSelector("body > form > div > div:nth-child(1) > div"));
		System.out.println("->" + e.getText());
		return e.getText();
	}
	
	public PageMap getPageMap() {
		return pageMap;
	}
	
	// Elements
	public Optional<WebElement> getSelectCompany() {
		return Optional.of(driver.findElement(By.cssSelector("body > form > div > div:nth-child(3) > div:nth-child(2) > select")));
	}

	
	private class MappingStrategyInitPayroll implements MappingStrategy{
		@Override
		public List<MapControl> getList() {
			MapControl[] objs = {
					new ControlCombo(driver, By.tagName("select"), "name")};

			return List.of(objs);
		}		
	}	

}