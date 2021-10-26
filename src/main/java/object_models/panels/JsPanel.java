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
import context_manager.states.StateFactorySetter;
import context_manager.states.StateHeaderPanel;
import exceptions.PanelException;
import object_models.forms.ContainerAction;
import object_models.helpers.ClassFieldGetter;
import object_models.helpers.IFrame;
import object_models.helpers.closers.CloserPanel;
import object_models.helpers.title.PageTitle;
import object_models.helpers.title.TitlePanel;

/**
 * @author Steve Brown
 *
 */
public abstract class JsPanel implements ContainerAction, ContextSetter, ContextIdGetter, ZZZ_PanelSwitcher, StateFactorySetter { 
	protected WebDriver driver;
	protected ContextManager manager;
	protected Logger logger = LogManager.getLogger();
	protected String expectedTitle;
	
	private PageTitle title = null;	
	private Optional<String> panelId;	
	private WebElement container;
	private JsPanelHeaderBar headerBar;
	private ContextState thisContext;
		
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

//	@Override
//	public <T extends JsPanel> void switchToExistingPanel(Class<T> panel) {
//		ContextState cs = manager.getLastContext();
//		Optional<State> stateHdrPanel = manager.switchToStateInContext(StateHeaderPanel.class, cs);
//		stateHdrPanel.ifPresent(h -> {
//			StateHeaderPanel hdrPanel = (StateHeaderPanel) h;
//			hdrPanel.switchToExistingPanel(panel);
//		});		
//	}

	@Override
	public <T extends JsPanel> void switchToExistingPanel(Class<T> panel) {
//		driver.switchTo().defaultContent();
//		headerBar.getToolBar().showDropDownMenu();
		
//		ClassFieldGetter fieldGetter = new ClassFieldGetter(panel);
//		Optional<String> panelTitle = fieldGetter.getPanelTitle();
		
//		panelTitle.ifPresent(forTitle -> {
			manager.switchToStateInContext(StateHeaderPanel.class, thisContext);				
//		});
		
//		panelTitle.ifPresent(forTitle -> {
//			Optional<ContextState> findCs = manager.findContext(forTitle);
//			findCs.ifPresent(cs -> {
//				manager.switchToStateInContext(StateHeaderPanel.class, cs);
//			});	
//		});
		
		
//		Optional<State> stateHdrPanel = manager.switchToStateInContext(StateHeaderPanel.class, thisContext);
		
//		stateHdrPanel.ifPresent(h -> {
//			StateHeaderPanel hdrPanel = (StateHeaderPanel) h;
//			hdrPanel.switchToExistingPanel(panel);
//		});		
	}
	
	// REMOVE ?????????????
	@Override
	public void switchToExistingPanel(JsPanel panelToSwitchTo, ContextState cs) {
////		ContextState cs = manager.getLastContext();
//		Optional<State> hdrPanelOfCurrentContext = manager.switchToStateInContext(StateHeaderPanel.class, manager.getCurrentContext());
//		hdrPanelOfCurrentContext.ifPresent(h -> {
//			StateHeaderPanel hdrPanel = (StateHeaderPanel) h;
//			hdrPanel.switchToExistingPanel(panelToSwitchTo, manager.getCurrentContext());
//		});		
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
		headerBar = new JsPanelHeaderBar(driver, container);
	}

	protected JsPanelControlBar getControlBar() {
		return headerBar.getControlBar();
	}
	
	@Override
	public void setContext() {
		thisContext = new ContextPanel(manager, this, headerBar, this);
		manager.setContext(thisContext);
	}
	
	@Override	// ContainerAction
	public PageTitle getTitle() {
		return title;
	}
	
	@Override	// ContainerAction
	public StateFactorySetter getStateFactorySetter() {
		return this;
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
		driver.switchTo().defaultContent(); // TODO - REMOVE !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		manager.switchToStateInCurrentContext(StateHeaderPanel.class);
		return headerBar;
	}
			
	public Optional<String> getHeaderBarTitle() {
		return getHeaderBar().getTitle();
	}
	
	public WebDriver getDriver() {
		return driver;
	}
		
	@Override
	public ContextId getContextId() {		
		return new ContextId(expectedTitle, panelId.get());
	}

	@Override
	public String getContextExpectedName() {
		return expectedTitle;
	}

	@Override 	// StateFactorySetter
	public ContextManager getContextManager() {
		return manager;
	}
	@Override 	// StateFactorySetter
	public
	WebDriver getWebDriver() {
		return driver;
	}	
	@Override 	// StateFactorySetter
	public JsPanelHeaderBar setJsPanelHeaderBar() {
		return headerBar;
	}	
	@Override 	// StateFactorySetter
	public IFrame getIFrame() {
		return null; // from JsPanelWithIFrame
	}	
}
