package com.tuin.test;

import com.tuin.bean.Variables;

public class VariablesFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Variables newVariables() {

		Long id = mockValues.nextLong();

		Variables variables = new Variables();
		variables.setId(id);
		return variables;
	}
	
}
