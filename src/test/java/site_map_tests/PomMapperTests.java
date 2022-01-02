package site_map_tests;

import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.junit.jupiter.api.Test;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import site_mapper.jaxb.classes.pom.PackageHierarchy;
import site_mapper.jaxb.classes.pom.PomMapperApp;

class PomMapperTests {
	@Test
	void jsdksdjds() {
		createPoms();
	}
	
	private void createPoms() {
		JAXBContext jc;
		try {
			jc = org.eclipse.persistence.jaxb.JAXBContext.newInstance(PomMapperApp.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
	    unmarshaller.setProperty("eclipselink.media-type", "application/xml");      
	    unmarshaller.setProperty(UnmarshallerProperties.DISABLE_SECURE_PROCESSING, Boolean.TRUE);    
	    StreamSource source = new StreamSource("./src/test/resources/site_map.xml");	    
	    PomMapperApp app = unmarshaller.unmarshal(source, PomMapperApp.class).getValue();
	    
	    /*
	     * give it the root & current 
	     * "./src/main/java", "object_models"
	     */
	    PackageHierarchy ph = new PackageHierarchy("./src/main/java", "object_models");
	    app.createPoms(ph);
		} catch (JAXBException e) {
			e.printStackTrace();
		}    
	}
	
}
