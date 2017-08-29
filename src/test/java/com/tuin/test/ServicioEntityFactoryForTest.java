package com.tuin.test;

import com.tuin.bean.jpa.ServicioEntity;

public class ServicioEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public ServicioEntity newServicioEntity() {

		Long id = mockValues.nextLong();

		ServicioEntity servicioEntity = new ServicioEntity();
		servicioEntity.setId(id);
		return servicioEntity;
	}
	
}
