package com.tuin.test;

import com.tuin.bean.jpa.TipovehiculoEntity;

public class TipovehiculoEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TipovehiculoEntity newTipovehiculoEntity() {

		Long id = mockValues.nextLong();

		TipovehiculoEntity tipovehiculoEntity = new TipovehiculoEntity();
		tipovehiculoEntity.setId(id);
		return tipovehiculoEntity;
	}
	
}
