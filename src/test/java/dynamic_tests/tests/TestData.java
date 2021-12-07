package dynamic_tests.tests;

import static dynamic_tests.tests.Node.operationFor;
import static dynamic_tests.tests.Node.TestOperation.TITLE;

import java.util.Map;
import java.util.Optional;

public class TestData {
	private final Node tree;
	private final Map<Node, Long> results;

	private TestData(Node tree, Map<Node, Long> results) {
		this.tree = tree;
		this.results = results;
	}

	static TestData generate() {
		var _1 = new Btn(1L);
		var _2 = new Btn(2L);
		var _3 = new Btn(3L);
		
		var add_1_3_2 = operationFor(TITLE, _1, _3, _2);
		
		var results = Map.of(
				_1, 11L,
				_2, 22L,
				_3, 33L,
				add_1_3_2, 44L);
		
		return new TestData(add_1_3_2, results);
	}

	Node tree() {
		return tree;
	}

	Object resultFor(Node node) {
		return Optional.of(results.get(node)).orElseThrow(() -> new IllegalArgumentException("No result for " + node));
	}
}
