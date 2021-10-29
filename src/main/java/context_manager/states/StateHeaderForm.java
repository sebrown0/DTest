/**
 * 
 */
package context_manager.states;

import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import context_manager.ContextState;

/**
 * @author Steve Brown
 *
 */
public class StateHeaderForm extends State {
	private WebElement myContainer;
	private By byHeaderLocator;
	
	public StateHeaderForm(ContextState cs, WebElement myContainer, By byHeaderLocator, WebDriver driver) {
		super(cs, driver);
		this.myContainer = myContainer;
		this.byHeaderLocator = byHeaderLocator;
	}
		
	@Override
	public Optional<State> getNext() {		
		return super.next;
	}	
	@Override
	public void close() {
		/*
		 * HAVE TO ADD ADDITIONAL FUNCTIONALITY
		 */
		logger.debug("Closing state [" + this + "]");
		logger.error("NOT IMPLEMENTED");
		System.out.println("StateHeaderForm->close"); // TODO - remove or log				
	}
	@Override
	public State switchToMe() {
		// TODO Auto-generated method stub
//		context.getDriver().findElement(null);
//		logger.error("switchToMe not implemented!");
		System.out.println("StateHeaderForm->switchToMe"); // TODO - remove or log				
		System.out.println("T->" + driver.getTitle()); // TODO - remove or log 	

		myContainer.findElement(byHeaderLocator);
		return this;
	}
	@Override
	public boolean isContextCloser() {
		return true;
	}
	@Override
	public boolean isDefaultState() {
		return false;
	}
}
