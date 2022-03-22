@XmlSchema(namespace = "sch",
           xmlns = {@XmlNs(prefix = "tns",
                           namespaceURI = "sch")},
           elementFormDefault = XmlNsForm.QUALIFIED)
package a;

import jakarta.xml.bind.annotation.XmlNs;
import jakarta.xml.bind.annotation.XmlNsForm;
import jakarta.xml.bind.annotation.XmlSchema;