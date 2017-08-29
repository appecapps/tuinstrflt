package com.tuin.test;

import com.tuin.bean.jpa.TipocarteraEntity;

public class TipocarteraEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TipocarteraEntity newTipocarteraEntity() {

		Long id = mockValues.nextLong();

		TipocarteraEntity tipocarteraEntity = new TipocarteraEntity();
		tipocarteraEntity.setId(id);
		return tipocarteraEntity;
	}
	
}
