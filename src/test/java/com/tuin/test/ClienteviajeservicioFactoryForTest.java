package com.tuin.test;

import com.tuin.bean.Clienteviajeservicio;

public class ClienteviajeservicioFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Clienteviajeservicio newClienteviajeservicio() {

		Long id = mockValues.nextLong();

		Clienteviajeservicio clienteviajeservicio = new Clienteviajeservicio();
		clienteviajeservicio.setId(id);
		return clienteviajeservicio;
	}
	
}
