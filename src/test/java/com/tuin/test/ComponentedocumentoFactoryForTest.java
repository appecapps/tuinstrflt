package com.tuin.test;

import com.tuin.bean.Componentedocumento;

public class ComponentedocumentoFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Componentedocumento newComponentedocumento() {

		Long id = mockValues.nextLong();

		Componentedocumento componentedocumento = new Componentedocumento();
		componentedocumento.setId(id);
		return componentedocumento;
	}
	
}
