package a;

import java.util.List;

import app.xml_content.XmlContent;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import site_mapper.jaxb.pom.Module;
import site_mapper.jaxb.pom.SiteMapInfo;

@XmlRootElement(name="root")//, namespace="root")
public class Root implements XmlContent{	
	@XmlElement(name="Parent", namespace="sch")
	List<Parent> parent;

	@Override
	public SiteMapInfo getSiteMapInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Module> getModules() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Parent> getParent() {
		return parent;
	}

}
