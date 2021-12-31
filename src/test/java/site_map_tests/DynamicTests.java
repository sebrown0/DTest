package site_map_tests;

import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.UserProvider;
import site_mapper.jaxb.classes.DynamicTestApp;
import xml_reader.config_file.ConfigReader;

@ExtendWith({ 
	ConfigParameterResolver.class, 
	LoginPageResolverPayroll.class })
class DynamicTests {
	private static HomePage homepage;	

	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		homepage = userLogin.loginValidUser(UserProvider.userPortal());		
	}		
		
	/*
	 *  Implicit pass if there are no errors.
	 *  Check that the created tests conform to the XML.
	 */
	@TestFactory	
	DynamicContainer runTests() {		
    return getApp().setHomePage(homepage).getTests();
	}

	private DynamicTestApp getApp() {
		JAXBContext jc;
		try {
			jc = org.eclipse.persistence.jaxb.JAXBContext.newInstance(DynamicTestApp.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
	    unmarshaller.setProperty("eclipselink.media-type", "application/xml");      
	    unmarshaller.setProperty(UnmarshallerProperties.DISABLE_SECURE_PROCESSING, Boolean.TRUE);    
	    StreamSource source = new StreamSource("./src/test/resources/site_map.xml");	    
	    JAXBElement<DynamicTestApp> app = unmarshaller.unmarshal(source, DynamicTestApp.class);
	    
	    return app.getValue();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return null;    
	}
	
}
