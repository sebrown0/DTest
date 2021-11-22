/**
 * 
 */
package object_models.panels;

import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.ContextId;
import context_manager.ContextIdGetter;
import context_manager.ContextManager;
import context_manager.ContextSetter;
import context_manager.ContextState;
import context_manager.contexts.ContextPanel;
import context_manager.states.StateFactorySetter;
import context_manager.states.StateHeaderPanel;
import context_manager.states.StateIframe;
import control_builder.ControlBuilder;
import control_builder.ControlData;
import control_builder.ControlGetter;
import control_builder.PageControl;
import controls.Control;
import controls.ControlName;
import exceptions.PanelException;
import object_models.forms.ContainerAction;
import object_models.helpers.IFrame;
import object_models.helpers.closers.CloserPanel;
import object_models.helpers.title.PageTitle;
import object_models.helpers.title.TitlePanel;

/**
 * @author Steve Brown
 *
 */
public abstract class JsPanel implements ContainerAction, ContextSetter, ContextIdGetter, StateFactorySetter { 
	protected WebDriver driver;
	protected Logger logger;
	protected ContextManager manager;	
	protected String expectedTitle;
	protected ControlBuilder builder;
	protected PageControl panelControl;
	
	private ContextState myContext;
	private PageTitle title = null;	
	private WebElement container;
	private Optional<String> panelId;	
	private JsPanelHeaderBar headerBar;	
			
//	private static final By TITLE_SELECTOR = By.cssSelector("span[class='jsPanel-title']");
		
	public JsPanel(WebDriver driver, String expectedTitle, ContextManager contextManager) {
		this.logger = LogManager.getLogger();
		this.driver = driver;
		this.expectedTitle = expectedTitle;
		this.manager = contextManager;		
		this.builder = new ControlBuilder();		
		
//		waitForLoad(ExpectedConditions.attributeContains(TITLE_SELECTOR, "innerHTML", expectedTitle));		
		setPanelId();
		setContainer();
		setTitle(); //SHOULD THIS BE PART OF THE HEADER BAR???
		setHeaderBar();
		setContext();
		setContextState();		
	}
	
	// StateHeaderPanel needs an IFrame.
	protected abstract void setContextState();
	
	protected void buildPanelControls(List<ControlData> panelControls) {
		builder.addControls(panelControls);
		panelControl = new PageControl(builder);		
	}
	
	/*
	 * Causing errors, if the panel fails to load this could be the cause.
	 */
//	private void waitForLoad(ExpectedCondition<?> expectedConditionFound) {
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		try {
//			wait.until(expectedConditionFound);
//		} catch (Exception e) {
//			logger.error("Could not load panel [" + expectedTitle + "]");
//			close();
//		}						
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
		title = new TitlePanel(expectedTitle, driver, container);
	}
	
	private void setHeaderBar() {
		headerBar = new JsPanelHeaderBar(driver, container);
	}

	protected JsPanelControlBar getControlBar() {
		return headerBar.getControlBar();
	}
	
	public PageControl getPanelControl() {
		manager.switchToStateInCurrentContext(StateIframe.class); 
		manager.setLatestCallingStateToCurrent();
		return panelControl;
	}
	
	public Optional<Control> getControl(ControlName cntrlName){
		return panelControl.getControl(cntrlName);
	}
	
	public void updateControl(ControlName cntrlName, ControlGetter updatedCntrl) {
		panelControl.updateControl(cntrlName, updatedCntrl);
	}
	
	@Override
	public void setContext() {
		myContext = new ContextPanel(manager, this, headerBar, this);
		manager.setContext(myContext);
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
//		System.out.println("** Depreciated. Use ContextManager **"); // TODO - remove or log 	
		
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
	public ContextState getMyContext() {
		return myContext;
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
