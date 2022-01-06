package jaxb;

import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.junit.jupiter.api.Test;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import site_mapper.jaxb.dynamic_tests.DynamicTestApp;
import site_mapper.jaxb.dynamic_tests.Menu;
import site_mapper.jaxb.dynamic_tests.Module;

class JaxbTests {
	
//	@Test
//	void moxy_unmarshaller_oxm() {
//		System.setProperty("jakarta.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
//		
//		Map<String, Object> properties = new HashMap<>(1);
//		properties.put(JAXBContextProperties.OXM_METADATA_SOURCE, "jaxb/moxy/models/oxm.xml");
//		
//		try {
//			JAXBContext jc = org.eclipse.persistence.jaxb.JAXBContextFactory.createContext(new Class[] {}, properties);
//			
//      Unmarshaller unmarshaller = jc.createUnmarshaller();
//      unmarshaller.setProperty("eclipselink.media-type", "application/xml");      
//      unmarshaller.setProperty(UnmarshallerProperties.DISABLE_SECURE_PROCESSING, Boolean.TRUE);
//      
//      StreamSource source = new StreamSource("./src/test/resources/foo.xml");      
//      JAXBElement<App> jaxbElement = unmarshaller.unmarshal(source, App.class);
//      
////      System.out.println("->" + jaxbElement.getValue().getModule().getValue()); // TODO - remove or log
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	@Test
	void moxy_unmarshaller() {
		System.setProperty("jakarta.xml.bind.context.factory", "org.eclipse.persistence.jaxb.JAXBContextFactory");
				
		try {
			JAXBContext jc = org.eclipse.persistence.jaxb.JAXBContext.newInstance(DynamicTestApp.class);
			
      Unmarshaller unmarshaller = jc.createUnmarshaller();
      unmarshaller.setProperty("eclipselink.media-type", "application/xml");      
      unmarshaller.setProperty(UnmarshallerProperties.DISABLE_SECURE_PROCESSING, Boolean.TRUE);
      
      StreamSource source = new StreamSource("./src/test/resources/app.xml");
      
      JAXBElement<DynamicTestApp> jaxbElement = unmarshaller.unmarshal(source, DynamicTestApp.class);
      
      Module payroll = jaxbElement.getValue().getModules().get(0);
      
      System.out.println("->" + payroll.getName());
      Menu left = payroll.getMenus().get(0); 
//      left.getNodes().forEach(n -> {
//      	System.out.println("->" + n.getObj());
//      	n.getElements().forEach(e -> System.out.println(" ->" + e)); // TODO - remove or log 	))); // TODO - remove or log 	
//      });
//      
//      System.out.println("->" + left.getPage().getBoofer()); // TODO - remove or log 	
      
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	@Test
//	void moxy_marshaller() {
//		Map<String, Object> properties = new HashMap<>(1);
//		properties.put(JAXBContextProperties.OXM_METADATA_SOURCE, "jaxb/moxy/models/oxm.xml");
//    try {
//			JAXBContext jc = JAXBContextFactory.createContext(new Class[] {}, properties);
//			
//      Module bar = new Module();
//      bar.setValue("XXXX");
//      App foo = new App();
//      foo.setModule(bar);
//      
//      Marshaller marshaller = jc.createMarshaller();
//      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//      marshaller.marshal(foo, new File("./src/test/resources/foo.xml"));
////      marshaller.marshal(foo, System.out);
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//     
//	}
	
//	@Test
//	void moxy2() {
//		try {
//			JAXBContext context = JAXBContext.newInstance(Student.class);
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

//	@Test
//	void unmarshal() {
//		try {
//			JAXBContext context = JAXBContext.newInstance(Student.class);
//			
//					
//			Unmarshaller unmarshaller = context.createUnmarshaller();
//			unmarshaller.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, Boolean.TRUE);
//			Student student = (Student) unmarshaller.unmarshal(new File("./src/test/resources/student.xml"));
//			System.out.println("->" + student); // TODO - remove or log 	
//		} catch (JAXBException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	@Test
//	void marshal() {
//		try {
//			Student student = new Student("jai", "MCA/07/06", "MCA final", 0, 0, Arrays.asList(new Subject("Java", "1"), new Subject("Oracle", "2")));
//			
//			JAXBContext context = JAXBContext.newInstance(Student.class);
//			
//			//Create Marshaller using JAXB context
//			Marshaller marshaller = context.createMarshaller();
//			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//		 
//			//Do the marshal operation
//			///DTest/src/test/java/jaxb/JaxbTests.java
//			marshaller.marshal(student, new FileOutputStream("./src/test/resources/student.xml"));
//			System.out.println("java object converted to xml successfully.");
//		    } catch (Exception e) {
//			e.printStackTrace();
//		    }
//	}

}
