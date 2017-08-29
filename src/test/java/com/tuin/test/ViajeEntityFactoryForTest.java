package com.tuin.test;

import com.tuin.bean.jpa.ViajeEntity;

public class ViajeEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public ViajeEntity newViajeEntity() {

		Long id = mockValues.nextLong();

		ViajeEntity viajeEntity = new ViajeEntity();
		viajeEntity.setId(id);
		return viajeEntity;
	}
	
}
