package com.tuin.test;

import com.tuin.bean.jpa.EntidadrolEntity;

public class EntidadrolEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public EntidadrolEntity newEntidadrolEntity() {

		Long id = mockValues.nextLong();

		EntidadrolEntity entidadrolEntity = new EntidadrolEntity();
		entidadrolEntity.setId(id);
		return entidadrolEntity;
	}
	
}
