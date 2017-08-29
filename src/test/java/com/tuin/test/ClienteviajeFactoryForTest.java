package com.tuin.test;

import com.tuin.bean.Clienteviaje;

public class ClienteviajeFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Clienteviaje newClienteviaje() {

		Long id = mockValues.nextLong();

		Clienteviaje clienteviaje = new Clienteviaje();
		clienteviaje.setId(id);
		return clienteviaje;
	}
	
}
