package com.tuin.test;

import com.tuin.bean.jpa.VehiculoEntity;

public class VehiculoEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public VehiculoEntity newVehiculoEntity() {

		Long id = mockValues.nextLong();

		VehiculoEntity vehiculoEntity = new VehiculoEntity();
		vehiculoEntity.setId(id);
		return vehiculoEntity;
	}
	
}
