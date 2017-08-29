package com.tuin.test;

import com.tuin.bean.jpa.EntidadEntity;

public class EntidadEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public EntidadEntity newEntidadEntity() {

		Long id = mockValues.nextLong();

		EntidadEntity entidadEntity = new EntidadEntity();
		entidadEntity.setId(id);
		return entidadEntity;
	}
	
}
