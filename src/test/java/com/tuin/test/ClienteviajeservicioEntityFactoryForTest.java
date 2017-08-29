package com.tuin.test;

import com.tuin.bean.jpa.ClienteviajeservicioEntity;

public class ClienteviajeservicioEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public ClienteviajeservicioEntity newClienteviajeservicioEntity() {

		Long id = mockValues.nextLong();

		ClienteviajeservicioEntity clienteviajeservicioEntity = new ClienteviajeservicioEntity();
		clienteviajeservicioEntity.setId(id);
		return clienteviajeservicioEntity;
	}
	
}
