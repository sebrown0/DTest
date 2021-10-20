package object_models.forms;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import context_manager.ContextId;
import context_manager.ContextIdGetter;
import context_manager.ContextManager;
import context_manager.ContextSetter;
import context_manager.contexts.ContextForm;
import object_models.helpers.closers.CloserModalForm;
import object_models.helpers.title.PageTitle;
import object_models.helpers.title.TitleModalForm;

/**
 * @author Steve Brown
 *
 */
public abstract class FormModal implements ContainerAction, ContextSetter, ContextIdGetter{
	protected WebDriver driver;
	protected ContextManager contextManager;
	
	private PageTitle title;
	private Logger logger = LogManager.getLogger();

	public FormModal(WebDriver driver, ContextManager contextManager) {
		this.driver = driver;		
		this.contextManager = contextManager;
		
		initialise();		
	}	

	public FormModal(WebDriver driver, String expectedTitle, ContextManager contextManager) {
		this.driver = driver;
		this.title = new TitleModalForm(expectedTitle, driver);		
		this.contextManager = contextManager;
		
		initialise();
	}
	
	private void initialise() {
		waitForLoad();		
//	setPanelId();
//	setContainer();
//	setTitle(); //SHOULD THIS BE PART OF THE HEADER BAR???
//	setHeaderBar();
		setContext();
		setContextStateToPanel();
	}
	
	protected void waitForLoad() {
		//overload
	}
	
	protected void setContextStateToPanel() {
		//overload
	}
	
	
	@Override
	public void setContext() {		
		contextManager.setContext(new ContextForm(contextManager, this));
	}	
	
	@Override
	public PageTitle getTitle() {
		return title;
	}

	@Override
	//IF WE'RE IN THE iFRAME WILL HAVE TO SWITCH BACK TO THE FORM.
	public void close() {
		CloserModalForm closer = new CloserModalForm(driver);
		try {
			closer.close();
		} catch (Exception e) {
			logger.error("Could not close form [" + title.getExpected() + "]");
		}		
	}

	@Override
	public ContextId getContextId() {
		logger.error("NOT IMPLEMENTED");
		return new ContextId("ERROR", "ERROR"); // TODO - IMPLEMENT 
	}
}