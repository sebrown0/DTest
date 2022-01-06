package site_map_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.junit.jupiter.api.Test;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import site_mapper.creators.ClassComponentFactory;
import site_mapper.creators.ComponentWriter;
import site_mapper.creators.ComponentWriterJsPanelWithIFrame;
import site_mapper.jaxb.pom.PomMapperApp;

class PomMapperTests {
	@Test
	void classComponents() {
		ComponentWriter cw = ClassComponentFactory.getComponentWriter("JsPanelWithIFrame");
		assertTrue(cw instanceof ComponentWriterJsPanelWithIFrame);
	}
	
	@Test
	void createPomsFromXML() {
		createPoms();
	}
	
	private void createPoms() {
		JAXBContext jc;
		final String XML_SOURCE = "./src/test/resources/site_map.xml";
		try {
			jc = org.eclipse.persistence.jaxb.JAXBContext.newInstance(PomMapperApp.class);
			
			Unmarshaller unmarshaller = jc.createUnmarshaller();
	    unmarshaller.setProperty("eclipselink.media-type", "application/xml");      
	    unmarshaller.setProperty(UnmarshallerProperties.DISABLE_SECURE_PROCESSING, Boolean.TRUE);    
	    
	    StreamSource source = new StreamSource(XML_SOURCE);	    
	    
	    PomMapperApp app = unmarshaller.unmarshal(source, PomMapperApp.class).getValue();	    
	    app.createPoms(XML_SOURCE);

		} catch (JAXBException e) {
			e.printStackTrace();
		}    
	}
	
}
