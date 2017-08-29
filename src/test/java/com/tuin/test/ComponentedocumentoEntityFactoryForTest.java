package com.tuin.test;

import com.tuin.bean.jpa.ComponentedocumentoEntity;

public class ComponentedocumentoEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public ComponentedocumentoEntity newComponentedocumentoEntity() {

		Long id = mockValues.nextLong();

		ComponentedocumentoEntity componentedocumentoEntity = new ComponentedocumentoEntity();
		componentedocumentoEntity.setId(id);
		return componentedocumentoEntity;
	}
	
}
