/**
 * 
 */
package app.test_reader;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xml_file.XMLFile;

/**
 * @author SteveBrown
 * @version 1.0
 * @since 1.0
 *
 * Get the required elements from the include tests XML file.
 */
public class IncludedTestsReader {
	private XMLFile xmlFile;
	private TestPackage testPackage;	
	
	public IncludedTestsReader(String filePath) {		
		xmlFile = new XMLFile(filePath);
		testPackage = new TestPackage();
	}	
		
	public TestPackage getPackage() {
		String packageName = getName();
		String applicableTo = getApplicableTo();
		TestFileReader fileReader = null;
		
		testPackage.setPackageName(packageName);
		testPackage.setApplicable(applicableTo);
		
		if(packageName.equalsIgnoreCase("ALL")) {
			fileReader = new AllTests();
		}else if (includingAllTestsInPackage()) {
			fileReader = new AllTestsInPackage();		
		}else {
			fileReader = new SpecificTestsInPackage(xmlFile);
		}
		
		fileReader.addTestsToPackage(testPackage);
		return testPackage;
	}

	private String getName() {
		 return xmlFile.getElement("TestPackage").getAttribute("name");
	}
	
	private String getApplicableTo() {
		 return xmlFile.getElement("TestPackage").getAttribute("applicable");
	}
	
	private boolean includingAllTestsInPackage() {
		boolean result = false;
		NodeList nodeList = xmlFile.getNodes("Test");
		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);
			if (node instanceof Element) {				
				var name = node.getTextContent().trim();
				if(name.equalsIgnoreCase("all")) {
					result = true;
					break;
				}				
			}		
		}
		return result;
	}
	
}