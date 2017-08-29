package com.tuin.test;

import com.tuin.bean.Marca;

public class MarcaFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Marca newMarca() {

		Long id = mockValues.nextLong();

		Marca marca = new Marca();
		marca.setId(id);
		return marca;
	}
	
}
