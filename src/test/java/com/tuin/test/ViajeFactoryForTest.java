package com.tuin.test;

import com.tuin.bean.Viaje;

public class ViajeFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Viaje newViaje() {

		Long id = mockValues.nextLong();

		Viaje viaje = new Viaje();
		viaje.setId(id);
		return viaje;
	}
	
}
