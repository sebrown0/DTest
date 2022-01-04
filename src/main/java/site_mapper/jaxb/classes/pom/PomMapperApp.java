package site_mapper.jaxb.classes.pom;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.creators.PackageMaker;
import site_mapper.jaxb.classes.Module;

/** 
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @version 1.1
 * 	Rename
 * @since 1.0
 * 
 * JAXB class representing the top level element root.
 * This will return a DynamicContainer with all the 
 * sub DynamicContainers found in the relevant XML doc.
 */
@XmlRootElement(name = "App")
public class PomMapperApp {
	
	@XmlElementWrapper(name="Modules")
  @XmlElement(name="Module")
  private List<Module> modules;

	public void createPoms(PackageHierarchy ph) {
			
		for (Module module : modules) {
//			Queue<String> packageNames = new LinkedList<String>();
//			packageNames.add("object_models");
			
			System.out.println(module.getName()); // TODO - remove or log			
			
//			PackageMaker.makeWithPackageInfo("./src/main/java", "object_models", "a_" + name);
//			module.getModuleContainers("./src/main/java", "object_models", packageNames);
			module.getModuleContainers(ph);
		}			
	}
	
}