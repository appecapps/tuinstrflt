package com.tuin.test;

import com.tuin.bean.jpa.FacturaelectronicaEntity;

public class FacturaelectronicaEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public FacturaelectronicaEntity newFacturaelectronicaEntity() {

		Long id = mockValues.nextLong();

		FacturaelectronicaEntity facturaelectronicaEntity = new FacturaelectronicaEntity();
		facturaelectronicaEntity.setId(id);
		return facturaelectronicaEntity;
	}
	
}
