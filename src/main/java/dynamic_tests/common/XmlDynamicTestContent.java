package dynamic_tests.common;

import dynamic_tests.elements.IncludedElements;
import dynamic_tests.elements.IncludedTests;
import dynamic_tests.test_strategy.DynamicTestReportStrategy;
import dynamic_tests.test_strategy.DynamicTestReportStrategyJunit;
/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 * 
 * POJO for dynamic test info from the XML file.
 */
public class XmlDynamicTestContent implements XmlInfo { 
	private IncludedElements includedElements;
	private DynamicTestInfoSetter dynamicTestInfo;
	private DynamicTestReportStrategy strat;
	
	private final DynamicTestInfoFromXml infoFromXml;
	/**
	 * 
	 * @param includedElements 
	 * 	The elements that are to be included in tests.
	 * @param dynamicTestInfo  
	 * 	Info about the dynamic tests. 
	 * 	The JAXB object is passed and the details copied from it. 
	 */
	
	public XmlDynamicTestContent(DynamicTestInfoFromXml infoFromXml) {
		this.infoFromXml = infoFromXml;
		
		setIncludedElements();
		setReportingStrategy();
	}

	private void setReportingStrategy() {		 
		var stratType = infoFromXml.getDynamicTestInfo().getReportStrategy();
		if(stratType.equalsIgnoreCase("junit")) {
			strat = new DynamicTestReportStrategyJunit();
		}else if(stratType.equalsIgnoreCase("console")) {
			System.out.println("XmlDynamicTestContent.getTestReportStrategy for [CONSOLE] NOT IMPLEMENTED."); // TODO - remove or log 	
		}			
	}

	private void setIncludedElements() {
		includedElements = new IncludedTests(infoFromXml.getIncludedElementsForTest());
	}
	
	@Override
	public IncludedElements getIncludedElements() {
		return includedElements;
	}
	@Override
	public DynamicTestInfoFromXml getDynamicTestInfo() {		
		return dynamicTestInfo;
	}
	@Override
	public DynamicTestReportStrategy getTestReportStrategy() {	
		return strat;
	}	
	
}
