package com.tuin.test;

import com.tuin.bean.Modelo;

public class ModeloFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Modelo newModelo() {

		Long id = mockValues.nextLong();

		Modelo modelo = new Modelo();
		modelo.setId(id);
		return modelo;
	}
	
}
