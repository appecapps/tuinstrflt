package com.tuin.test;

import com.tuin.bean.Servicio;

public class ServicioFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Servicio newServicio() {

		Long id = mockValues.nextLong();

		Servicio servicio = new Servicio();
		servicio.setId(id);
		return servicio;
	}
	
}
