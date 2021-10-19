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

import context_manager.ContextState;
import context_manager.ContextId;
import context_manager.ContextIdGetter;
import context_manager.ContextManager;
import context_manager.ContextSetter;
import context_manager.contexts.ContextPanel;
import context_manager.states.State;
import context_manager.states.StateHeaderPanel;
import exceptions.PanelException;
import object_models.forms.ContainerAction;
import object_models.helpers.closers.CloserPanel;
import object_models.helpers.title.PageTitle;
import object_models.helpers.title.TitlePanel;

/**
 * @author Steve Brown
 *
 */
public class JSPanel implements ContainerAction, ContextSetter, ContextIdGetter { 
	protected WebDriver driver;
	protected ContextManager contextManager;
	protected Logger logger = LogManager.getLogger();
	
	private PageTitle title = null;
	private String expectedTitle;
	private Optional<String> panelId;	
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
		setContextStateToPanel();		
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

	private void setContextStateToPanel() {
		ContextState con = contextManager.getCurrentContext();			 	
		State header = new StateHeaderPanel(con, headerBar.getControlBar());		
		con.setState(header);
	}
	
	@Override
	public void setContext() {		
		contextManager.setContext(new ContextPanel(contextManager, this, headerBar));
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
		return headerBar;
	}
			
	public WebDriver getDriver() {
		return driver;
	}

	@Override
	public ContextId getContextId() {		
		return new ContextId(expectedTitle, panelId.get());
	}

}
