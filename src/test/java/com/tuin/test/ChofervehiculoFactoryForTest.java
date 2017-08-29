package com.tuin.test;

import com.tuin.bean.Chofervehiculo;

public class ChofervehiculoFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Chofervehiculo newChofervehiculo() {

		Long id = mockValues.nextLong();

		Chofervehiculo chofervehiculo = new Chofervehiculo();
		chofervehiculo.setId(id);
		return chofervehiculo;
	}
	
}
