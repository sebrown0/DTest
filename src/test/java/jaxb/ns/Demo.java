package jaxb.ns;

import java.io.File;

import javax.xml.transform.stream.StreamSource;

import jakarta.xml.bind.*;
import site_mapper.jaxb.classes.DynamicTestApp;

public class Demo {
  public static void main(String[] args) throws Exception {
    JAXBContext jc = 
       JAXBContext.newInstance(Customer.class);

    Unmarshaller unmarshaller = jc.createUnmarshaller();
    StreamSource source = new StreamSource("./src/test/java/jaxb/ns/demo.xml");
    
    JAXBElement<Customer> jaxbElement = unmarshaller.unmarshal(source, Customer.class);
    System.out.println("->" + jaxbElement.getValue().getName()); // TODO - remove or log 	
    System.out.println("->" + jaxbElement.getValue().getOrder().getName()); // TODO - remove or log
    
}
//  public static void main(String[] args) throws Exception {
//      JAXBContext jc = 
//         JAXBContext.newInstance(Customer.class);
//
//      
//      Order o = new Order();
//      o.setId(4321);
//      o.setName("new order");
//      
//      Customer customer = new Customer();
//      customer.setOrder(o);
//      customer.setId(123);
//      customer.setName("Jane Doe");
//
//      Marshaller m = jc.createMarshaller();
//      m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//      m.marshal(customer, new File("./src/test/java/jaxb/ns/demo.xml"));
////      m.marshal(customer, System.out);
//  }
}