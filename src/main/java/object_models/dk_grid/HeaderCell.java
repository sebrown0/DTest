package object_models.dk_grid;

import org.openqa.selenium.WebElement;

public class HeaderCell {
//	private WebElement myContainer;
	private FloatingFilter filter;
	
	public HeaderCell(WebElement myContainer) {
//		this.myContainer = myContainer;
		this.filter = new FloatingFilter(myContainer);
	}

	public FloatingFilter getFilter() {
		return filter;
	}
		
}
