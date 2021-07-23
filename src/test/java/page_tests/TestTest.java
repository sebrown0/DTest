package page_tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import listners.TestResultLogger;

@ExtendWith(TestResultLogger.class)
class TestTest {
	
  @Test
  void givenFalseIsTrue_whenTestAbortedThenCaptureResult() {
      Assumptions.assumeTrue(false);
  }

  @Disabled
  @Test
  void givenTrueIsTrue_whenTestDisabledThenCaptureResult() {
  	assertEquals(true, true);
  }
  
  @Test
  @Tag("T123")
  void givenTrueIsNotTrue_fail() {
  	assertEquals(true, false);
  }
  
  @Test
  void givenTrueIsTrue_pass() {
  	assertEquals(true, true);
  }
}
