/**
 * 
 */
package site_mapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SteveBrown
 * @version 1.0
 * 	Initial
 * @since 1.0
 */
public class TestFactory {
	public static List<String> getTests(ElementType testType){
		List<String> tests = new ArrayList<>();
		switch (testType.getType()) {
		case "button":
			tests.add("fafa");
			tests.add("text");
			break;

		default:
			break;
		}
		return tests;
	}
}
