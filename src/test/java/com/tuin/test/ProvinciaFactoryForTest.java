package com.tuin.test;

import com.tuin.bean.Provincia;

public class ProvinciaFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Provincia newProvincia() {

		Long id = mockValues.nextLong();

		Provincia provincia = new Provincia();
		provincia.setId(id);
		return provincia;
	}
	
}
