/**
 * 
 */
package xml_reader.test_file;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xml_file.XMLFile;

/**
 * @author Steve Brown
 *
 * Get the required elements from the include tests XML file.
 */
public class IncludedTestsReader {
	private XMLFile xmlFile;
	private TestPackage testPackage = new TestPackage();
	
	public IncludedTestsReader(String filePath) {		
		xmlFile = new XMLFile(filePath);
	}	
		
	public TestPackage getPackage() {
		testPackage.setPackageName(getName());
		testPackage.setApplicable(getApplicableTo());
		testPackage.setTestClasses(getClasses());
		return testPackage;
	}
	
	private String getName() {
		 return xmlFile.getElement("TestPackage").getAttribute("name");
	}
	
	private String getApplicableTo() {
		 return xmlFile.getElement("TestPackage").getAttribute("applicable");
	}
	
	private List<TestClass> getClasses() {
//		NodeList nodeList = xmlFile.getNodes("Class");
		NodeList nodeList = xmlFile.getNodes("Test");
		List<TestClass> testClasses = new ArrayList<>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node =nodeList.item(i);
			if (node instanceof Element) {
				var name = node.getTextContent().trim();
				var priority = ((Element) node).getAttribute("priority");				
				TestClass testClass = new TestClass(name, TestClass.TestPriority.valueOf(priority.toUpperCase()));				
				testClasses.add(testClass);	
			}		
		}
		return testClasses;
	}
}
