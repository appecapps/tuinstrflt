package com.tuin.test;

import com.tuin.bean.Componentefinanciero;

public class ComponentefinancieroFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Componentefinanciero newComponentefinanciero() {

		Long id = mockValues.nextLong();

		Componentefinanciero componentefinanciero = new Componentefinanciero();
		componentefinanciero.setId(id);
		return componentefinanciero;
	}
	
}
