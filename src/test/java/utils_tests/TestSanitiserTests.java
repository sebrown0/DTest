package utils_tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import helpers.text_utils.RemoveX;
import helpers.text_utils.TextSanitiser;

class TestSanitiserTests {

	@Test
	void oneLine_withX_shouldReturn_noLines() {
		TextSanitiser sanitiser = new RemoveX();
		List<String> sanitisedLines = sanitiser.sanitiseText("X");
		assertTrue(sanitisedLines.size() == 0);
	}

	@Test
	void twoLines_with_X_and_GoodVal_shouldReturn_goodVal() {
		TextSanitiser sanitiser = new RemoveX();
		List<String> sanitisedLines = sanitiser.sanitiseText("X\nGoodLine");
		assertEquals("GoodLine", sanitisedLines.get(0));
	}

	@Test
	void twoLines_with_x_and_GoodVal_shouldReturn_goodVal() {
		TextSanitiser sanitiser = new RemoveX();
		List<String> sanitisedLines = sanitiser.sanitiseText("x\nGoodLine");
		assertEquals("GoodLine", sanitisedLines.get(0));
	}
	
	@Test
	void noLines_shouldReturn_emptyList() {
		TextSanitiser sanitiser = new RemoveX();
		List<String> sanitisedLines = sanitiser.sanitiseText("");
		assertTrue(sanitisedLines.size() == 0);
	}
}
