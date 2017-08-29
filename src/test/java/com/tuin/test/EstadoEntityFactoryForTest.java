package com.tuin.test;

import com.tuin.bean.jpa.EstadoEntity;

public class EstadoEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public EstadoEntity newEstadoEntity() {

		Long id = mockValues.nextLong();

		EstadoEntity estadoEntity = new EstadoEntity();
		estadoEntity.setId(id);
		return estadoEntity;
	}
	
}
