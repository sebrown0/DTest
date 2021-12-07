package dynamic_tests.tests;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Stream;

import dynamic_tests.func_int.FuncInc;

public interface Node {
	boolean evaluate();
	List<Node> children();

	static Node operationFor(TestOperation operator, Node... children) {
		return new ParentNode(operator, children);
	}

	static Node valueOf(long value) {
		return new ValueNode(value);
	}

	class ParentNode implements Node {
		private final TestOperation operator;
		private final Node[] operands;

		public ParentNode(TestOperation operator, Node[] operands) {
			this.operator = requireNonNull(operator);
			this.operands = requireNonNull(operands);
		}

		@Override
		public boolean evaluate() {
			Object[] operandValues = Stream.of(operands)
					.map(Node::evaluate)
					.toArray();
			return operator.evaluate(operandValues);
		}

		@Override
		public List<Node> children() {
			return List.of(operands);
		}

		@Override
		public String toString() {
			return operator.toString();
		}

	}

	class ValueNode implements Node {
		private final long value;

		public ValueNode(long value) {
			this.value = value;
		}

		@Override
		public boolean evaluate() {
			return true;
		}
		@Override
		public List<Node> children() {
			return List.of();
		}
		@Override
		public String toString() {
			return "Value " + value;
		}

	}

	enum TestOperation {
		FAFA(
				() -> { return (2==2); },
				() -> "FaFa"),

		TITLE(
				() -> { return (2==2); },
				() -> "Title");
		

		private final FuncInc compute;
		private final Supplier<String> toString;

		TestOperation(FuncInc compute, Supplier<String> toString) {			
			this.compute = compute;
			this.toString = toString;
		}

		public boolean evaluate(Object... operands) {
			return compute.compute();// .applyAsLong(operands);
		}
//		public boolean evaluate(long... operands) {
//			return compute.applyAsLong(operands);
//		}

		public String toString() {
			return toString.get();
		}
	}
}

//public interface Node {
//	long evaluate();
//
//	List<Node> operands();
//
//	static Node operationFor(ArithmeticOperator operator, Node... operands) {
//		return new OperationNode(operator, operands);
//	}
//
//	static Node valueOf(long value) {
//		return new ValueNode(value);
//	}
//
//	class OperationNode implements Node {
//
//		private final ArithmeticOperator operator;
//
//		private final Node[] operands;
//
//		private OperationNode(ArithmeticOperator operator, Node[] operands) {
//			this.operator = requireNonNull(operator);
//			this.operands = requireNonNull(operands);
//		}
//
//		@Override
//		public long evaluate() {
//			long[] operandValues = Stream.of(operands)
//					.mapToLong(Node::evaluate)
//					.toArray();
//			return operator.evaluate(operandValues);
//		}
//
//		@Override
//		public List<Node> operands() {
//			return List.of(operands);
//		}
//
//		@Override
//		public String toString() {
//			return operator.toString();
//		}
//
//	}
//
//	class ValueNode implements Node {
//
//		private final long value;
//
//		private ValueNode(long value) {
//			this.value = value;
//		}
//
//		@Override
//		public long evaluate() {
//			return value;
//		}
//
//		@Override
//		public List<Node> operands() {
//			return List.of();
//		}
//
//		@Override
//		public String toString() {
//			return "Value " + value;
//		}
//
//	}
//
//	enum ArithmeticOperator {
//
//		MULTIPLY(
//				operands -> LongStream
//						.of(operands)
//						// implementation error to make tests interesting
//						.map(operand -> operand % 10 == 0 ? operand / 10 : operand)
//						.reduce(1, (o1, o2) -> o1 * o2),
//				() -> "Multiplication"),
//
//		ADD(
//				operands -> LongStream
//						.of(operands)
//						// implementation error to make tests interesting
//						.map(operand -> operand == 4 ? 3 : operand)
//						.sum(),
//				() -> "Addition");
//
//		private final ToLongFunction<long[]> compute;
//		private final Supplier<String> toString;
//
//		ArithmeticOperator(ToLongFunction<long[]> compute, Supplier<String> toString) {
//			this.compute = compute;
//			this.toString = toString;
//		}
//
//		public long evaluate(long... operands) {
//			return compute.applyAsLong(operands);
//		}
//
//		public String toString() {
//			return toString.get();
//		}
//	}
//}
