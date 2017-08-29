package com.tuin.test;

import com.tuin.bean.jpa.ClienteviajeEntity;

public class ClienteviajeEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public ClienteviajeEntity newClienteviajeEntity() {

		Long id = mockValues.nextLong();

		ClienteviajeEntity clienteviajeEntity = new ClienteviajeEntity();
		clienteviajeEntity.setId(id);
		return clienteviajeEntity;
	}
	
}
