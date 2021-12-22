/**
 * 
 */
package site_mapper.elements;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DynamicTest;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public abstract class ElementTest implements TestElement{	
	private String name;
	private String type;
	private List<DynamicTest> tests = new ArrayList<>();
	private ElementLoader loader;	

	public ElementTest(String type, String name, ElementLoader loader) {
		this.type = type;
		this.name = name;
		this.loader = loader;
	}

	public List<DynamicTest> getTests() {
		return tests;
	}
	
	@Override //TestElement
	public String getName() {
		return name;
	}
	@Override //TestElement
	public String getType() {
		return type;
	}
	
	protected ElementLoader getLoader() {
		return loader;
	}		
}
