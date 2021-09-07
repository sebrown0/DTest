package object_models.forms;

import org.openqa.selenium.WebDriver;

import object_models.helpers.closers.CloserModalForm;
import object_models.helpers.title.PageTitle;
import object_models.helpers.title.TitleModalForm;

/**
 * @author Steve Brown
 *
 */
public class FormModal implements ContainerAction {
	private WebDriver driver;
	private PageTitle title;
		
	public FormModal(WebDriver driver, String expectedTitle) {
		this.driver = driver;
		this.title = new TitleModalForm(expectedTitle, driver);		
	}

	@Override
	public PageTitle getTitle() {
		return title;
	}

	@Override
	public void closeElement() {
		CloserModalForm closer = new CloserModalForm(driver);
		closer.close();
	}
		
}