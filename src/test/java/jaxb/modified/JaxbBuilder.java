/**
 * 
 */
package jaxb.modified;

import java.io.File;
import java.util.Arrays;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;


/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 */
public class JaxbBuilder {

	public static void main(String[] args) throws Exception {
		JAXBContext jc = 
		   JAXBContext.newInstance(App.class);
		
		App app = new App();
		app.setModules(Arrays.asList(new Module()));
		//Order o = new Order();
		//o.setId(4321);
		//o.setName("new order");
		//
		//Customer customer = new Customer();
		//customer.setOrder(o);
		//customer.setId(123);
		//customer.setName("Jane Doe");
		
		Marshaller m = jc.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		m.marshal(app, System.out);
//		m.marshal(app, new File("./src/test/java/jaxb/modified/app.xml"));
	
	}

}
