package tag_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import tags.TagParser;
import tags.TestCaseTag;

class TagParserTest {

	@Test
	void getTestCaseNum_ReturnNum() {
		assertEquals("123", TagParser.getTestCaseNum("C123"));
	}
	
	@Test
	void getTestNum_ReturnNum() {
		assertEquals("123", TagParser.getTestNum("T123"));
	}
	
	@Test
	void getTestCaseNum_ReturnNoNum() {
		assertEquals("0", TagParser.getTestCaseNum("X123"));
	}
	
	@Test
	void createTestCaseTagObj() {
		TestCaseTag tagObj = new TestCaseTag("R1", "C1");
		assertEquals("R1", tagObj.getTestRunNum());
	}
	
	@Test
	void createTestCaseTagObj_FromSet() {
		Set<String> tags = new HashSet<String>(Arrays.asList("R1","C1"));
		TestCaseTag tagObj = TagParser.getTestCaseTag(tags);
		assertEquals("R1:C1", tagObj.getTestRunNum() + ":" + tagObj.getTestNum());
	}
}
