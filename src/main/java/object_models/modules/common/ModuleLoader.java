/**
 * 
 */
package object_models.modules.common;

import object_models.pages.homepage.CoreData;
import object_models.pages.homepage.HomePage;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 * Loads a module using the supplied ModuleElements.
 */
public final class ModuleLoader extends ModuleLoaderAtLogin {
	private HomePage hp;
	
	public ModuleLoader(CoreData coreData, ModuleElements moduleElements) {
		super(coreData, moduleElements);
	}
		
	public HomePage loadModule(HomePage current) {
		this.hp = current;
		if(current.getModuleName().equalsIgnoreCase(moduleName)){			
			logger.info(moduleName + " module not loaded. Loading now");
			moduleElements.getQuickLinkToLoadModule().clickMe();			
			this.hp = super.loadModule();
		}else {
			logger.info(moduleName + " module already loaded");
		}
		
		return this.hp;
	}
	
}
