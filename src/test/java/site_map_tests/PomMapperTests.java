package site_map_tests;

import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.junit.jupiter.api.Test;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import site_mapper.jaxb.classes.pom.PomMapperApp;
import utils.PackageMaker;

class PomMapperTests {
	@Test
	void jsdksdjds() {
		createPoms();
	}

//	@Test
//	void sdjksjku() {
//		PackageMaker.makeWithPackageInfo("./src/test/java", "a");
//		PackageMaker.makeWithPackageInfo("./src/test/java", "a", "b");		
//	}
	
	private void createPoms() {
		JAXBContext jc;
		try {
			jc = org.eclipse.persistence.jaxb.JAXBContext.newInstance(PomMapperApp.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
	    unmarshaller.setProperty("eclipselink.media-type", "application/xml");      
	    unmarshaller.setProperty(UnmarshallerProperties.DISABLE_SECURE_PROCESSING, Boolean.TRUE);    
	    StreamSource source = new StreamSource("./src/test/resources/site_map.xml");	    
	    PomMapperApp app = unmarshaller.unmarshal(source, PomMapperApp.class).getValue();
	    
	    app.createPoms();
		} catch (JAXBException e) {
			e.printStackTrace();
		}    
	}
	
}
