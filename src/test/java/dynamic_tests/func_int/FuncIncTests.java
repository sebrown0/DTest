package dynamic_tests.func_int;

import org.junit.jupiter.api.Test;

class FuncIncTests {

	@Test
	void test() {
		x(() -> { return (2==3); });
	}
	
	private static void x(FuncInc funcInc) {
		if(funcInc.compute()) {
			System.out.println("TRUE"); // TODO - remove or log 	
		}else {
			System.out.println("FALSE"); // TODO - remove or log
		}
	}

}
