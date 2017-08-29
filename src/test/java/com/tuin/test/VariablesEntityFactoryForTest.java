package com.tuin.test;

import com.tuin.bean.jpa.VariablesEntity;

public class VariablesEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public VariablesEntity newVariablesEntity() {

		Long id = mockValues.nextLong();

		VariablesEntity variablesEntity = new VariablesEntity();
		variablesEntity.setId(id);
		return variablesEntity;
	}
	
}
