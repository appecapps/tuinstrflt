package com.tuin.test;

import com.tuin.bean.jpa.RolEntity;

public class RolEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public RolEntity newRolEntity() {

		Long id = mockValues.nextLong();

		RolEntity rolEntity = new RolEntity();
		rolEntity.setId(id);
		return rolEntity;
	}
	
}
