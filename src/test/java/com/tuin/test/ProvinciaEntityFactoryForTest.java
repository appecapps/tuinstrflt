package com.tuin.test;

import com.tuin.bean.jpa.ProvinciaEntity;

public class ProvinciaEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public ProvinciaEntity newProvinciaEntity() {

		Long id = mockValues.nextLong();

		ProvinciaEntity provinciaEntity = new ProvinciaEntity();
		provinciaEntity.setId(id);
		return provinciaEntity;
	}
	
}
