package parameter_tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import parameters.UserParameterResolver;

class ParameterResolverTests {

	@Test
	void userParameters() {
		UserParameterResolver paramResolver = new UserParameterResolver();
		assertTrue(paramResolver.supportsParameter(null, null));
	}

}
