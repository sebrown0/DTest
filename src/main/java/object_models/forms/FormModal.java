package object_models.forms;

import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import context_manager.ContextIdGetter;
import context_manager.ContextManager;
import context_manager.ContextSetter;
import context_manager.contexts.ContextForm;
import object_models.helpers.Header;
import object_models.helpers.title.PageTitle;

/**
 * @author Steve Brown
 *
 */
public abstract class FormModal implements ContainerAction, ContextSetter, ContextIdGetter{
	protected WebDriver driver;
	protected ContextManager contextManager;
	protected PageTitle title;
	protected Logger logger = LogManager.getLogger();
	protected WebDriverWait wait;
	protected Header header;
	protected WebElement container;
		
	@SuppressWarnings("unused")
	private String expectedTitle;	
	
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
		setContainer();		
//		setHeader();
		setTitle();
		setContext();
		setContextState();
	}
	
	public abstract void waitForLoad();
	public abstract void setContextState();
	public abstract void setContainer();
	public abstract Header getHeader();
//	public abstract void setHeader();
	public abstract void setTitle();
	
	@Override
	public void setContext() {		
		contextManager.setContext(new ContextForm(contextManager, this));
	}	
	
	@Override
	public PageTitle getTitle() {
		return title;
	}

//	public Header getHeader() {
//		return header;
//	}
//	@Override
//	public ContextId getContextId() {
//		logger.error("NOT IMPLEMENTED");
//		return new ContextId("ERROR", "ERROR"); // TODO - IMPLEMENT 
//	}
}