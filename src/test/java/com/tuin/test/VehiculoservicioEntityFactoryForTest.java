package com.tuin.test;

import com.tuin.bean.jpa.VehiculoservicioEntity;

public class VehiculoservicioEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public VehiculoservicioEntity newVehiculoservicioEntity() {

		Long id = mockValues.nextLong();

		VehiculoservicioEntity vehiculoservicioEntity = new VehiculoservicioEntity();
		vehiculoservicioEntity.setId(id);
		return vehiculoservicioEntity;
	}
	
}
