package com.tuin.test;

import com.tuin.bean.jpa.ChofervehiculoEntity;

public class ChofervehiculoEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public ChofervehiculoEntity newChofervehiculoEntity() {

		Long id = mockValues.nextLong();

		ChofervehiculoEntity chofervehiculoEntity = new ChofervehiculoEntity();
		chofervehiculoEntity.setId(id);
		return chofervehiculoEntity;
	}
	
}
