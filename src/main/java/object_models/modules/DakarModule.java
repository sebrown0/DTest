/**
 * 
 */
package object_models.modules;

import org.openqa.selenium.WebDriver;

/**
 * @author Steve Brown
 *
 */
public abstract class DakarModule  {
	protected WebDriver driver;
	private ModuleLoader moduleLoader;
	
	public DakarModule(WebDriver driver, ModuleLoader moduleLoader) {
		this.driver = driver;
		this.moduleLoader = moduleLoader;
	}
	
	public abstract String getName(); 
	
	public void loadModule() {
		moduleLoader.loadModule();
	}
}
