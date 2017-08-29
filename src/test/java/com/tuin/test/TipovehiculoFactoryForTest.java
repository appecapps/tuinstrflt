package com.tuin.test;

import com.tuin.bean.Tipovehiculo;

public class TipovehiculoFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Tipovehiculo newTipovehiculo() {

		Long id = mockValues.nextLong();

		Tipovehiculo tipovehiculo = new Tipovehiculo();
		tipovehiculo.setId(id);
		return tipovehiculo;
	}
	
}
