/**
 * 
 */
package context_manager;

import java.util.Optional;

import org.openqa.selenium.By;

/**
 * @author Steve Brown
 *
 */
public class StateLeftMenu extends State {
	
	public StateLeftMenu(Context context, Optional<State> prev) {
		super(context, prev);
		System.out.println("ZZZ_StateLeftMenu->");
		System.out.println("ZZZ_StateLeftMenu->" + prev.get().toString());
//		switchToMe();
	}
// body > form > div.app-body
	@Override
	public Optional<State> getNext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<State> close() {
		// TODO Auto-generated method stub
		return super.getPrev();
	}

	@Override
	public void switchToMe() {
		context.getDriver().findElement(By.cssSelector("body > form > div.app-body > div"));		
	}


}
