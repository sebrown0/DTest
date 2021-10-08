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

import exceptions.PanelException;
import object_models.forms.ContainerAction;
import object_models.helpers.closers.CloserPanel;
import object_models.helpers.title.PageTitle;
import object_models.helpers.title.TitlePanel;

/**
 * @author Steve Brown
 *
 */
public class JSPanel implements ContainerAction { // ContainerAction extends Closable (was ChildElement)
	protected WebDriver driver;
//	protected FrameOrPanel frameOrPanel = FrameOrPanel.FRAME;
//	protected JsPanelContext panelContext;
	
	private JsPanelContextManager contextManager;
	private PageTitle title = null;
	private String expectedTitle;
	private Optional<String> panelId;
	private Logger logger = LogManager.getLogger();
	private WebElement container;
	private JsPanelHeaderBar headerBar;
		
	private static final By TITLE_SELECTOR = By.cssSelector("span[class='jsPanel-title']");
	
//	public static enum FrameOrPanel {
//		FRAME, PANEL
//	}
	
	public JSPanel(WebDriver driver, String expectedTitle) {
		this.driver = driver;
		this.expectedTitle = expectedTitle;
				
		try {
			waitForLoad();
		} catch (Exception e) {
			logger.error("Could not load panel [" + expectedTitle + "]");
			close();
		}
		setPanelId();
		setContainer();
		setTitle(); //SHOULD THIS BE PART OF THE HEADER BAR???
		setHeaderBar();
	}

	private void waitForLoad() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(TITLE_SELECTOR, "innerHTML", expectedTitle));				
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
//		switchToPanelIfNecessary();
		contextManager.switchToPanelIfNecessary();
		return headerBar;
//		return new JsPanelHeaderBar(container);
	}
	
//	private void switchToPanelIfNecessary() {
		
//		if(frameOrPanel == FrameOrPanel.FRAME) {
////			System.out.println("->is frame going to panel");
//			driver.switchTo().defaultContent();
//			frameOrPanel = FrameOrPanel.PANEL;	
//		}		
//	}

//	public FrameOrPanel getFrameOrPanel() {
//		return frameOrPanel;
//	}
	
	public void setPanelContext(JsPanelContextManager contextManager) {
		this.contextManager = contextManager;
	}

	public JsPanelContextManager getContextManager() {
		return contextManager;
	}
}
