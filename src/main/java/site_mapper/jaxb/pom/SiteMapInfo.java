/**
 * 
 */
package site_mapper.jaxb.pom;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
@XmlRootElement(name="Info")
@XmlAccessorType(XmlAccessType.FIELD)
public class SiteMapInfo {
	@XmlElement(name="Author")
	private String author;
	@XmlElement(name="Version")
	private String version;
	
	private String xmlSource;
	
	public SiteMapInfo setAuthor(String author) {
		this.author = author;
		return this;
	}
	public SiteMapInfo setVersion(String version) {
		this.version = version;
		return this;
	}
	public SiteMapInfo setXmlSource(String xmlSource) {
		this.xmlSource = xmlSource;
		return this;
	}
	
	public String getAuthor() {
		return author;
	}
	public String getVersion() {
		return version;
	}
	public String getXmlSource() {
		return xmlSource;
	}
	
	@Override
	public String toString() {
		return "SiteMap [author=" + author + ", version=" + version + "]";
	}
	
}
