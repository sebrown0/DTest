package jaxb.ns;

import com.sun.xml.txw2.annotation.XmlElement;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlType;

@XmlElement
@XmlType(namespace="http://www.example.org/order")
public class Order {
  private long id;
  private String name;

  @XmlAttribute
  public long getId() {
      return id;
  }

  public void setId(long id) {
      this.id = id;
  }

  public String getName() {
      return name;
  }

  public void setName(String name) {
      this.name = name;
  }
}
