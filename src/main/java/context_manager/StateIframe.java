/**
 * 
 */
package context_manager;

import java.util.Optional;

import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 */
public class StateIframe extends State {
		
	public StateIframe(Context context, Optional<State> prev) {
		super(context, prev);		
		context.setState(this);
	}

	@Override
	public Optional<State> getNext() {
		return Optional.empty();
	}

	@Override
	public Optional<State> close() {
		// TODO Add close logic
		System.out.println("->Close StateIframe");
		context.getDriver().switchTo().defaultContent();
		return super.getPrev().get().close();
	}

	public WebDriver f() {
		return context.getDriver().switchTo().defaultContent();
	}
	
	@Override
	public void switchToMe() {
		// TODO Auto-generated method stub

	}
}
