package com.tuin.test;

import com.tuin.bean.jpa.ComponentefinancieroEntity;

public class ComponentefinancieroEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public ComponentefinancieroEntity newComponentefinancieroEntity() {

		Long id = mockValues.nextLong();

		ComponentefinancieroEntity componentefinancieroEntity = new ComponentefinancieroEntity();
		componentefinancieroEntity.setId(id);
		return componentefinancieroEntity;
	}
	
}
