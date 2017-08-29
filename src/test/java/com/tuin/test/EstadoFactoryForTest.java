package com.tuin.test;

import com.tuin.bean.Estado;

public class EstadoFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Estado newEstado() {

		Long id = mockValues.nextLong();

		Estado estado = new Estado();
		estado.setId(id);
		return estado;
	}
	
}
