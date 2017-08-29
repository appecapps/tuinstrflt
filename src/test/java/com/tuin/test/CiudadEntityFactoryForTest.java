package com.tuin.test;

import com.tuin.bean.jpa.CiudadEntity;

public class CiudadEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public CiudadEntity newCiudadEntity() {

		Long id = mockValues.nextLong();

		CiudadEntity ciudadEntity = new CiudadEntity();
		ciudadEntity.setId(id);
		return ciudadEntity;
	}
	
}
