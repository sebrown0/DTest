package dynamic_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;

import logging.TestResultLogger;

@ExtendWith({
	 
	TestResultLogger.class, 
	})
class Tests1 {
/*
 * Have to get the Map/List of objects from the site mapper. 
 */
	
	private List<Integer> ints = Arrays.asList(0,3,6,9);
	
	@TestFactory  // 1
	Stream<DynamicTest> dynamicTestStream() {  // 2
	  return ints.stream()
	  		.mapToInt(i -> { return i; })
	      .mapToObj(v ->
	          dynamicTest(v + " is a multiple of 3", () -> { 
	          	assertEquals(0, v % 3);
	          	System.out.println("->" + v); // TODO - remove or log 	
	          })  // 3
	      );
	}
	
//	@TestFactory  // 1
//	Stream<DynamicTest> dynamicTestStream() {  // 2
//	  return IntStream.of(0, 3, 6)
//	      .mapToObj(v ->
//	          dynamicTest(v + " is a multiple of 3", () -> { 
//	          	assertEquals(0, v % 3);
//	          	System.out.println("->" + v); // TODO - remove or log 	
//	          })  // 3
//	      );
//	}
}
