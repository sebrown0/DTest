package dynamic_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

public class Class1 {
	@TestFactory
	Collection<DynamicTest> dynamicTestsWithCollection() {
	    return Arrays.asList(
	      DynamicTest.dynamicTest("Add test",
	        () -> assertEquals(2, Math.addExact(1, 1))),
	      DynamicTest.dynamicTest("Multiply Test",
	        () -> assertEquals(4, Math.multiplyExact(2, 2))));
	}
	
	@TestFactory  // 1
	Stream<DynamicTest> dynamicTestStream() {  // 2
	  return IntStream.of(0, 3, 6, 9)
	      .mapToObj(v ->
	          dynamicTest(v + " is a multiple of 3", () -> assertEquals(0, v % 3))  // 3
	      );
	}
}
