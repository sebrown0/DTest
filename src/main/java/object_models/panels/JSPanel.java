/**
 * 
 */
package object_models.panels;

import java.time.Duration;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import context_manager.ContextManager;
import context_manager.ContextPanel;
import context_manager.ContextSetter;
import context_manager.ZZZ_ContextManager;
import exceptions.PanelException;
import object_models.forms.ContainerAction;
import object_models.helpers.closers.CloserPanel;
import object_models.helpers.title.PageTitle;
import object_models.helpers.title.TitlePanel;

/**
 * @author Steve Brown
 *
 */
public class JSPanel implements ContainerAction, ContextSetter { 
	protected WebDriver driver;
	protected ContextManager contextManager;
	
	private ZZZ_ContextManager zzz_contextManager;	
	private PageTitle title = null;
	private String expectedTitle;
	private Optional<String> panelId;
	private Logger logger = LogManager.getLogger();
	private WebElement container;
	private JsPanelHeaderBar headerBar;
		
	private static final By TITLE_SELECTOR = By.cssSelector("span[class='jsPanel-title']");
	
	public JSPanel(WebDriver driver, String expectedTitle, ContextManager contextManager) {
		this.driver = driver;
		this.expectedTitle = expectedTitle;
		this.contextManager = contextManager;
		
		
		waitForLoad();		
		setPanelId();
		setContainer();
		setTitle(); //SHOULD THIS BE PART OF THE HEADER BAR???
		setHeaderBar();
		setContext();
	}

	@Override
	public void setContext() {		
		contextManager.setContext(new ContextPanel());		
	}
	
	private void waitForLoad() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			wait.until(ExpectedConditions.attributeContains(TITLE_SELECTOR, "innerHTML", expectedTitle));	
		} catch (Exception e) {
			logger.error("Could not load panel [" + expectedTitle + "]");
			close();
		}						
	}

	private void setPanelId() {
		panelId = JsPanelId.getPanelIdForTitle(driver, expectedTitle);
	}
	
	private void setContainer() {
		panelId.ifPresentOrElse(
				id -> {	container = driver.findElement(By.id(id)); }, 
				new PanelException("Could not set container for [" + expectedTitle + "]. No Panel ID present"));		
	}
	
	private void setTitle() {
		title = new TitlePanel(expectedTitle, driver);
	}
	
	private void setHeaderBar() {
		headerBar = new JsPanelHeaderBar(container);
	}
	
	@Override
	public PageTitle getTitle() {
		return title;
	}

	@Override
	public void close() {
		CloserPanel closer = new CloserPanel(driver);
		try {
			closer.close();
		} catch (Exception e) {
			logger.error("Could not close panel [" + expectedTitle + "]");
		}
	}

	public Optional<String> getPanelId() {
		return panelId;
	}
	
	public JsPanelHeaderBar getHeaderBar() {
		zzz_contextManager.switchToPanelIfNecessary();
		return headerBar;
	}
		
	
	
//	public void setPanelContext(ZZZ_ContextManager contextManager) {
//		this.zzz_contextManager = contextManager;
//	}
//
//	public ZZZ_ContextManager getContextManager() {
//		return zzz_contextManager;
//	}
	
	public WebDriver getDriver() {
		return driver;
	}

}
