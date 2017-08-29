package com.tuin.test;

import com.tuin.bean.Entidad;

public class EntidadFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Entidad newEntidad() {

		Long id = mockValues.nextLong();

		Entidad entidad = new Entidad();
		entidad.setId(id);
		return entidad;
	}
	
}
