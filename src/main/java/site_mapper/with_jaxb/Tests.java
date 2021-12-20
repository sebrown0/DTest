package site_mapper.with_jaxb;

import java.util.Collection;

import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DynamicContainer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jaxb.moxy.models.App;
import object_models.pages.UserLoginPage;
import object_models.pages.homepage.HomePage;
import parameter_resolvers.ConfigParameterResolver;
import parameter_resolvers.LoginPageResolverPayroll;
import resources.test_data.UserProvider;
import xml_reader.config_file.ConfigReader;

@ExtendWith({ 
	ConfigParameterResolver.class, 
	LoginPageResolverPayroll.class })
class Tests {
	private static HomePage homepage;	

	@BeforeAll	
	public static void setup(ConfigReader configReader, UserLoginPage userLogin) {
		homepage = userLogin.loginValidUser(UserProvider.userPortal());		
	}		
	
//	@Test
//	void mapXml() {
//		App app = getApp();
//		assertTrue(app != null);
//	}
	
//	@Test
//	void changeModule() {
//		homepage.loadModule("Personnel");
//	}
	
	@TestFactory
	Collection<DynamicContainer> runAppTests() {		
    return getApp().setHomePage(homepage).getTests();
	}

	private App getApp() {
		JAXBContext jc;
		try {
			jc = org.eclipse.persistence.jaxb.JAXBContext.newInstance(App.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
	    unmarshaller.setProperty("eclipselink.media-type", "application/xml");      
	    unmarshaller.setProperty(UnmarshallerProperties.DISABLE_SECURE_PROCESSING, Boolean.TRUE);    
	    StreamSource source = new StreamSource("./src/test/resources/app2.xml");    
	    JAXBElement<App> app = unmarshaller.unmarshal(source, App.class);
	    return app.getValue();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;    
	}
	
}
