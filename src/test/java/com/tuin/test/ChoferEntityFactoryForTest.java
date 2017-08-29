package com.tuin.test;

import com.tuin.bean.jpa.ChoferEntity;

public class ChoferEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public ChoferEntity newChoferEntity() {

		Long id = mockValues.nextLong();

		ChoferEntity choferEntity = new ChoferEntity();
		choferEntity.setId(id);
		return choferEntity;
	}
	
}
