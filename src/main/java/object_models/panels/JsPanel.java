/**
 * 
 */
package object_models.panels;

import java.lang.reflect.Method;
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
import controls.ControlTest;
import dynamic_tests.elements.SiteMapElement;
import dynamic_tests.finders.MethodFinder;
import dynamic_tests.finders.MethodGetter;
import exceptions.PanelException;
import object_models.forms.ContainerAction;
import object_models.helpers.IFrame;
import object_models.helpers.title.PageTitle;
import object_models.helpers.title.TitlePanel;
import object_models.pages.homepage.CoreData;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 *  Add ControlTest & SiteMapElement.
 * @since 1.0
 *
 */
public abstract class JsPanel implements 
	ContainerAction, ContextSetter, ContextIdGetter, 
	StateFactorySetter, ControlTest, SiteMapElement, MethodGetter {
	
	protected WebDriver driver;
	protected Logger logger;
	protected ContextManager manager;	
	protected String expectedTitle;
	protected ControlBuilder builder;
	protected PageControl panelControl;
	protected CoreData coreData;
	
	private ContextState myContext;
	private PageTitle title = null;	
	private WebElement container;
	private Optional<String> panelId;	
	private JsPanelHeaderBar headerBar;	
	
	// USE FOR TESTING WHEN WE WANT A BLANK OBJECT
	public JsPanel() {}
	
	public JsPanel(CoreData coreData, String expectedTitle) {
		this.coreData = coreData;
		this.logger = LogManager.getLogger();
		this.driver = coreData.getWebDriver();
		this.expectedTitle = expectedTitle;
		this.manager = coreData.getContextManager();		
		this.builder = new ControlBuilder();
		
				
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
				id -> {	 container = driver.findElement(By.id(id)); }, 
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
		manager.switchToStateInCurrentContext(StateIframe.class);
		return panelControl.getControl(cntrlName);
	}
	/*
	 * TODO - CHECK TO SEE IF IT'S THE CURRENT CONTROL.
	 */
	@Override //ControlTest
	public Optional<Control> getControl(String cntrlName){		
		manager.switchToStateInCurrentContext(StateIframe.class);
		return panelControl.getControl(cntrlName);
	}
	
	public void updateControl(ControlName cntrlName, ControlGetter updatedCntrl) {
		panelControl.updateControl(cntrlName, updatedCntrl);
	}
	
	@Override
	public void setContext() {
		myContext = new ContextPanel(coreData, this, headerBar, this);
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
		/*
		 * The actual closing of the panel is done in the CM.
		 * If this proves to be unreliable may have to move 
		 * it to here.
		 * 
		 *   headerBar.closeForm();  
		 */
		getContextManager().removeAndCloseContext(getMyContext());
	}
	
	@Override //MethodGetter
	public List<Method> getAllTestMethods(){
		return MethodFinder.getTestMethods(this.getClass());
	}
	
	@Override //MethodGetter
	public List<Method> getAllTestMethodsWithType(String type){
		return MethodFinder.getTestMethodsOfType(this.getClass(), type);
	}
	@Override //MethodGetter
	public Method getMethodsWithTypeAndName(String type, String name) {
		return MethodFinder.getTestMethodOfTypeWithName(this.getClass(), type, name);
	}
	
	public Optional<String> getPanelId() {
		return panelId;
	}
	
	public JsPanelHeaderBar getHeaderBar() {
		driver.switchTo().defaultContent(); 
		manager.switchToStateInCurrentContext(StateHeaderPanel.class);
		return headerBar;
	}
			
	public Optional<String> getHeaderBarTitle() {
		return getHeaderBar().getTitle();
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public WebElement getContainer() {
		return container;
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
