/**
 * 
 */
package app.test_reader;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xml_file.XMLFile;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 * 
 * Get the test classes as specified in the test XML file.
 */
public class SpecificTestsInPackage implements TestFileReader {
	private XMLFile xmlFile;
	
	public SpecificTestsInPackage(XMLFile xmlFile) {
		this.xmlFile = xmlFile;
	}

	@Override
	public void addTestsToPackage(TestPackage testPackage) {
		NodeList nodeList = xmlFile.getNodes("Test");
		List<TestClass> testClasses = new ArrayList<>();
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node instanceof Element) {
				var name = node.getTextContent().trim();
				var priority = ((Element) node).getAttribute("priority");
				
				TestClass testClass = 
						new TestClass(
								name, 
								testPackage.getPackageName(), 
								TestClass.TestPriority.valueOf(priority.toUpperCase()));
				
				testClasses.add(testClass);	
			}		
		}
		testPackage.setTestClasses(testClasses);
	}
}
