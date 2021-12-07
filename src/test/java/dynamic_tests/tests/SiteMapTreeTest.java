package dynamic_tests.tests;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;
import static java.util.stream.Stream.*;
import java.util.Collection;
import java.util.stream.Stream;

import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;

class SiteMapTreeTest {

	@TestFactory
	DynamicNode testArithmeticTree() {
		return generateTestPlan(TestData.generate());
	}

	private DynamicNode generateTestPlan(TestData treeTestData) {
		return generateTestTreeFor(treeTestData.tree(), treeTestData);
	}

	private static DynamicNode generateTestTreeFor(Node node, TestData treeTestData) {
		var testForNode = generateTestFor(node, treeTestData);
		if (node.children().isEmpty())
			return testForNode;
		else {
			var testsForChildren = generateTestsFor(node.children(), treeTestData);
			var expected = treeTestData.resultFor(node);
			var testName = node + " should evaluate to " + expected + " (ops '+3' and '*10' fail)";
			return dynamicContainer(testName, concat(of(testForNode), testsForChildren));
		}
	}

	private static DynamicTest generateTestFor(Node node, TestData treeTestData) {
		var expected = treeTestData.resultFor(node);
		var testName = node.children().isEmpty()
				? node + " should evaluate to " + expected
				: node + " of operands should evaluate to " + expected;
		return dynamicTest(testName, () -> {
			var actual = node.evaluate();
			assertThat(actual).isEqualTo(expected);
		});
	}

	private static Stream<DynamicNode> generateTestsFor(
			Collection<Node> operands, TestData treeTestData) {
		return operands.stream()
				.map(operand -> generateTestTreeFor(operand, treeTestData));
	}

}

//class ArithmeticTreeTest_1 {
//
//	@TestFactory
//	DynamicNode testArithmeticTree() {
//		return generateTestPlan(TestData.generate());
//	}
//
//	private DynamicNode generateTestPlan(TestData treeTestData) {
//		return generateTestTreeFor(treeTestData.tree(), treeTestData);
//	}
//
//	private static DynamicNode generateTestTreeFor(Node node, TestData treeTestData) {
//		var testForNode = generateTestFor(node, treeTestData);
//		if (node.operands().isEmpty())
//			return testForNode;
//		else {
//			var testsForChildren = generateTestsFor(node.operands(), treeTestData);
//			var expected = treeTestData.resultFor(node);
//			var testName = node + " should evaluate to " + expected + " (ops '+3' and '*10' fail)";
//			return dynamicContainer(testName, concat(of(testForNode), testsForChildren));
//		}
//	}
//
//	private static DynamicTest generateTestFor(
//			Node node, TestData treeTestData) {
//		var expected = treeTestData.resultFor(node);
//		var testName = node.operands().isEmpty()
//				? node + " should evaluate to " + expected
//				: node + " of operands should evaluate to " + expected;
//		return dynamicTest(testName, () -> {
//			var actual = node.evaluate();
//			assertThat(actual).isEqualTo(expected);
//		});
//	}
//
//	private static Stream<DynamicNode> generateTestsFor(
//			Collection<Node> operands, TestData treeTestData) {
//		return operands.stream()
//				.map(operand -> generateTestTreeFor(operand, treeTestData));
//	}
//
//}
