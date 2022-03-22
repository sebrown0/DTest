package a;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Optional;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.sax.SAXSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.persistence.jaxb.UnmarshallerProperties;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.XMLReader;

import app.xml_content.XmlContent;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;


/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class SiteMapContentGetter <T extends XmlContent> {	
//	private Logger logger = LogManager.getLogger("site_mapper.app.log");
	private JAXBContext jc;
	private Unmarshaller unmarshaller;	
	private Optional<T> content = Optional.empty();	
	private final String XML_SOURCE;
	private final Class<T> clazz;
	
  private static final String JAXP_SCHEMA_LANGUAGE =
      "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
  
  private static final String W3C_XML_SCHEMA =
      "http://www.w3.org/2001/XMLSchema";
  
	public SiteMapContentGetter(final String XML_SOURCE, Class<T> clazz) {
		this.XML_SOURCE = XML_SOURCE;
		this.clazz = clazz;
	}
		
	public Optional<T> getContent() {
		try {
			setJaxContext(clazz);
			setUnmarshaller();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return content;
	}
	
	private void setJaxContext(Class<T> clazz) throws JAXBException {
		jc = JAXBContext.newInstance(clazz);
	}

	@SuppressWarnings("unchecked")
	private void setUnmarshaller() throws JAXBException {
		unmarshaller = jc.createUnmarshaller();
    unmarshaller.setProperty("eclipselink.media-type", "application/xml");      
    unmarshaller.setProperty(UnmarshallerProperties.DISABLE_SECURE_PROCESSING, Boolean.TRUE);
        
    SAXParserFactory spf = SAXParserFactory.newInstance();

    spf.setXIncludeAware(true);
    spf.setNamespaceAware(true);
    try {
    	
			spf.setFeature("http://apache.org/xml/features/xinclude/fixup-base-uris", false);
		} catch (SAXNotRecognizedException | SAXNotSupportedException | ParserConfigurationException e2) {

			e2.printStackTrace();
		}
    
    SAXParser saxParser = null;
		try {
			saxParser = spf.newSAXParser();
			saxParser.setProperty(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
		} catch (ParserConfigurationException | SAXException e1) {			
			e1.printStackTrace();
		}    

    try {
			XMLReader xr = saxParser.getXMLReader();			
			SAXSource source = new SAXSource(xr, new InputSource(new FileInputStream(XML_SOURCE)));
			content = (Optional<T>) Optional.ofNullable(unmarshaller.unmarshal(source));
		} catch (SAXException | FileNotFoundException e) {
			e.printStackTrace();
		}    
	}
	
}
