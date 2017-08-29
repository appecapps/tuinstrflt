package com.tuin.test;

import com.tuin.bean.jpa.ClienteEntity;

public class ClienteEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public ClienteEntity newClienteEntity() {

		Long id = mockValues.nextLong();

		ClienteEntity clienteEntity = new ClienteEntity();
		clienteEntity.setId(id);
		return clienteEntity;
	}
	
}
