package com.tuin.test;

import com.tuin.bean.Cliente;

public class ClienteFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Cliente newCliente() {

		Long id = mockValues.nextLong();

		Cliente cliente = new Cliente();
		cliente.setId(id);
		return cliente;
	}
	
}
