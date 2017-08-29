package com.tuin.test;

import com.tuin.bean.Vehiculo;

public class VehiculoFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Vehiculo newVehiculo() {

		Long id = mockValues.nextLong();

		Vehiculo vehiculo = new Vehiculo();
		vehiculo.setId(id);
		return vehiculo;
	}
	
}
