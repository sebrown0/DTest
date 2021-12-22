package utils_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import utils.StringUtil;

class StringUtilTests {

	@Test
	void getValueFromStringAtPostion() {
		assertEquals("B", StringUtil.getValueAt("A,B,C", 1, ","));
	}
	@Test
	void capitialiseFirst() {
		assertEquals("Save", StringUtil.capitiliseFirstChar("save"));
	}

}
