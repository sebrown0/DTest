package object_models.forms;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import context_manager.ContextId;
import context_manager.ContextIdGetter;
import context_manager.ContextManager;
import context_manager.ContextSetter;
import context_manager.ContextState;
import context_manager.contexts.ContextForm;
import context_manager.states.State;
import context_manager.states.StateFactorySetter;
import context_manager.states.StateHeaderForm;
import object_models.helpers.Header;
import object_models.helpers.IFrame;
import object_models.helpers.title.PageTitle;
import object_models.helpers.title.TitleModalForm;
import object_models.panels.JsPanelHeaderBar;

/**
 * @author Steve Brown
 *
 */
public abstract class FormModal implements ContainerAction, ContextSetter, ContextIdGetter, StateFactorySetter{
	protected WebDriver driver;
	protected ContextManager contextManager;
	protected PageTitle title;
	protected Logger logger = LogManager.getLogger();
	protected WebDriverWait wait;
	protected Header header;
	protected WebElement formContainerElement;
	protected String expectedTitle;
	protected By byFormContainer = By.cssSelector("div[class='modal show']");	
	protected ContextState myContext;	

	public FormModal(WebDriver driver, String expectedTitle, ContextManager contextManager) {
		this.driver = driver;
		this.expectedTitle = expectedTitle;
//		this.title = new TitleModalForm(expectedTitle, driver);		
		this.contextManager = contextManager;
		
		wait = new WebDriverWait(driver, Duration.ofSeconds(2));
		initialise();
	}
	
	private void initialise() {
		waitForLoad();
		setTopContainer();		
		setHeader();
		setTitle();
		setContext();
		setContextState();
	}
		
	public abstract void setMyContainers();
		
	// Override if the form should wait for a different element. 
	protected void waitForLoad() {
		wait.until(ExpectedConditions.visibilityOfElementLocated(byFormContainer));
	}	
	// Override this if the top element of the form is different from byFormContainer
	protected void setTopContainer() {
		formContainerElement = driver.findElement(byFormContainer);
	}
	// Override if title is different.		
	protected void setTitle() {
		title = new TitleModalForm(expectedTitle, driver);
	}
	// Override if header is different.
	protected void setHeader() {
		header = new ModalHeader(formContainerElement);
	}
	/*
	 * If the header is different from ModalHeader 
	 * it should be set by the child form -> setHeader().
	 */
	public Header getHeader() {
		return header;
	}
	public void setContextState() {
		// This was moved from FormWithIFrame. For a JsPanel it's in the iFrame implementation.
		By byLocator = By.className("modal-header");	 	
		State header = new StateHeaderForm(myContext, formContainerElement, byLocator, driver);		
		myContext.setState(header);
	}
	
	@Override	// ContainerAction
	public StateFactorySetter getStateFactorySetter() {
		return this;
	}	
	@Override	// ContainerAction
	public PageTitle getTitle() {
		return title;
	}
	@Override	// ContainerAction
	public ContextState getMyContext() {
		return myContext;
	}
	
	@Override // Closable
	public void close() {
		/*
		 * Has been tested with DropdownCombo.
		 * At this point we are in the iFrame of DDC^
		 * 
		 * Using driver.switchTo().defaultContent(); to go
		 * back to prev iFrame
		 */				
		driver.switchTo().defaultContent();
		contextManager.closeCurrentContextAndRevertToCallingContext();		
		header.closeForm();
	}
			
	@Override // ContextSetter
	public void setContext() {		
		myContext = new ContextForm(contextManager, this, this);		
		contextManager.setContext(myContext);
	}	
	
	@Override // ContextIdGetter
	public ContextId getContextId() {		
		return new ContextId(expectedTitle, "");
	}
	@Override // ContextIdGetter
	public String getContextExpectedName() {
		return expectedTitle;
	}
	
	@Override 	// StateFactorySetter
	public ContextManager getContextManager() {
		return contextManager;
	}
	@Override 	// StateFactorySetter
	public
	WebDriver getWebDriver() {
		return driver;
	}	
	@Override 	// StateFactorySetter
	public JsPanelHeaderBar setJsPanelHeaderBar() {
		return null;
	}
	@Override 	// StateFactorySetter
	public IFrame getIFrame() {
		return null; // from FormWithIFrame
	}

}