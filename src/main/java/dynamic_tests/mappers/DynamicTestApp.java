package dynamic_tests.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DynamicContainer;

import app.xml_content.XmlTestContent;
import dynamic_tests.common.DynamicTestInfoTransformer;
import dynamic_tests.common.XmlDynamicTestContent;
import dynamic_tests.common.XmlInfo;
import library.pages.homepage.HomePage;
import site_mapper.jaxb.pom.Module;

/** 
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0 
 * 
 */

public class DynamicTestApp {	
	private final XmlTestContent content;
	private final HomePage hp;
	
	private List<DynamicContainer> moduleMenus = new ArrayList<>();;
	private XmlInfo xmlInfo;
	
	public DynamicTestApp(XmlTestContent content, HomePage hp) {
		this.content = content;
		this.hp = hp;
	}

	public DynamicContainer getAppTests() {		
		if(homepageOk(hp) && content != null) {			
			setXmlInfo();
			getModules().ifPresent( mods ->{
				mods.forEach(m -> {					
					moduleMenus.add(
							new DynamicTestModule().getModuleContainers(m, xmlInfo, hp)
					);
		  	});
			});
		}		  	
		return DynamicContainer.dynamicContainer("ROOT", moduleMenus);
	}

	private Optional<List<Module>> getModules(){
		return Optional.ofNullable(content.getModules());
	}
	
	private boolean homepageOk(HomePage homePage) {	
		return (homePage != null) ? true : false;
	}

	private void setXmlInfo() {
		xmlInfo = new XmlDynamicTestContent(new DynamicTestInfoTransformer(content));
	}
	

}