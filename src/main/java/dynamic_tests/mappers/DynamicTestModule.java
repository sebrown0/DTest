/**
 * 
 */
package dynamic_tests.mappers;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicContainer;
import site_mapper.jaxb.pom.menu.MenuType;
import dynamic_tests.elements.IncludedElements;
import object_models.pages.homepage.HomePage;
import site_mapper.jaxb.pom.Module;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * Get the modules for dynamic tests.
 * 
 */
public class DynamicTestModule {
  private List<DynamicContainer> moduleMenus;

	public DynamicContainer getModuleContainers(Module module, IncludedElements includedElements, HomePage hp) {
		moduleMenus = new ArrayList<>();
		List<MenuType> menus = module.getMenus();
		if(menus != null) {
			var moduleName = module.getName();
			
			menus.stream()
				.filter(m-> m != null)
				.forEach(m -> {
					moduleMenus.add(
							new DynamicTestMenu(m, includedElements, hp, moduleName).getMenuContainers()
					);
				});
			
			return DynamicContainer.dynamicContainer(moduleName, moduleMenus);
		}  	
		
		return null;
	}

}
