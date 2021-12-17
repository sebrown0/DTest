package jaxb.moxy.models;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;

//@XmlRootElement(name = "Page")
//@XmlType(propOrder = { "boofer" })
@XmlRootElement
@XmlType(namespace = "./src/test/resources/defs")
public class Page {
	@XmlElement(name="boofer")
  private String boofer;
	@XmlElement(name="pooper")
  private String pooper;

  public Page() {
  	System.out.println("NEW PAGE"); // TODO - remove or log 	
  }
  public String getBoofer() {
      return boofer;
  }

//  @XmlAttribute
//  public void setBoofer(String boofer) {
//  	this.boofer = boofer;
//  }

	public String getPooper() {
		return pooper;
	}
//	@XmlAttribute
//	public void setPooper(String pooper) {
//		this.pooper = pooper;
//	}
  
}
