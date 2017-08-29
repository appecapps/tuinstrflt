package com.tuin.test;

import com.tuin.bean.Vehiculoservicio;

public class VehiculoservicioFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Vehiculoservicio newVehiculoservicio() {

		Long id = mockValues.nextLong();

		Vehiculoservicio vehiculoservicio = new Vehiculoservicio();
		vehiculoservicio.setId(id);
		return vehiculoservicio;
	}
	
}
