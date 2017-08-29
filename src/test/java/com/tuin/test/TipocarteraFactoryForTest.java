package com.tuin.test;

import com.tuin.bean.Tipocartera;

public class TipocarteraFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Tipocartera newTipocartera() {

		Long id = mockValues.nextLong();

		Tipocartera tipocartera = new Tipocartera();
		tipocartera.setId(id);
		return tipocartera;
	}
	
}
