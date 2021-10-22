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
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import context_manager.ContextId;
import context_manager.ContextIdGetter;
import context_manager.ContextManager;
import context_manager.ContextSetter;
import context_manager.ContextState;
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
public abstract class JsPanel implements ContainerAction, ContextSetter, ContextIdGetter { 
	protected WebDriver driver;
	protected ContextManager manager;
	protected Logger logger = LogManager.getLogger();
	protected String expectedTitle;
	
	private PageTitle title = null;	
	private Optional<String> panelId;	
	private WebElement container;
	private JsPanelHeaderBar headerBar;
		
	private static final By TITLE_SELECTOR = By.cssSelector("span[class='jsPanel-title']");
		
	public JsPanel(WebDriver driver, String expectedTitle, ContextManager contextManager) {
		this.driver = driver;
		this.expectedTitle = expectedTitle;
		this.manager = contextManager;		
		
		waitForLoad(ExpectedConditions.attributeContains(TITLE_SELECTOR, "innerHTML", expectedTitle));		
		setPanelId();
		setContainer();
		setTitle(); //SHOULD THIS BE PART OF THE HEADER BAR???
		setHeaderBar();
		setContext();
		setContextState();		
	}
	
	// StateHeaderPanel needs an IFrame.
	public abstract void setContextState();
	
	private void waitForLoad(ExpectedCondition<?> expectedConditionFound) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		try {
			wait.until(expectedConditionFound);
		} catch (Exception e) {
			logger.error("Could not load panel [" + expectedTitle + "]");
			close();
		}						
	}

	public <T extends JsPanel> void switchToExistingPanel(Class<T> panel) {
		ContextState cs = manager.getCurrentContext();
		Optional<State> stateHdrPanel = manager.switchToStateInContext(StateHeaderPanel.class, cs);
		stateHdrPanel.ifPresent(h -> {
			StateHeaderPanel hdrPanel = (StateHeaderPanel) h;
			hdrPanel.switchToExistingPanel(panel);
		});		
	}
	
//	public <T extends JsPanel> void switchToExistingPanel(Class<T> panel) {
//		ClassFieldGetter fieldGetter = new ClassFieldGetter(panel);		
//		Optional<String> panelTitle = fieldGetter.getPanelTitle();
//		
//		panelTitle.ifPresent(title -> {
//			Optional<ContextState> csCurr = manager.findContext(title);
//			if(csCurr.isPresent()) {
//				ContextState cs = csCurr.get();
//				manager.switchToStateInContext(StateHeaderPanel.class, cs);		//should StateHeaderPanel have header bar obj???????? 		
//				getHeaderBar().getToolBar().switchToPanel(cs.getContextId().getActualId());
//				logger.debug("Switched to panel [" + cs.getContextId() + "]"); 	
//			}else {
//				logger.error("Could not switch to panel [" + panel + "]"); 	
//			}
//		});		
//	}
	
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
		headerBar = new JsPanelHeaderBar(driver, container);
	}

	protected JsPanelControlBar getControlBar() {
		return headerBar.getControlBar();
	}
	
	@Override
	public void setContext() {		
		manager.setContext(new ContextPanel(manager, this, headerBar, this));
	}
	
	@Override
	public PageTitle getTitle() {
		return title;
	}

	@Override
	public void close() {
		logger.info("** Depreciated. Use ContextManager **");
		System.out.println("** Depreciated. Use ContextManager **"); // TODO - remove or log 	
		
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

	public ContextManager getContextManager() {
		return manager;
	}

	@Override
	public String getContextExpectedName() {
		return expectedTitle;
	}
	
}
