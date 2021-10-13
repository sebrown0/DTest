package context_manager_tests;

import org.junit.jupiter.api.Test;

import context_manager.ContextManager;
import context_manager.ContextPanel;

class ZZZZContextManagerTests {

	@Test
	void test() {
		ContextManager cm = new ContextManager();
		cm.setContext(new ContextPanel());
		cm.getContext().getState().getNext().close();
		cm.getContext().getState().close();
		cm.getContext().getState().getNext().close();
	}

}
