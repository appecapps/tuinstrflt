package com.tuin.test;

import com.tuin.bean.jpa.TipocomponentefinancieroEntity;

public class TipocomponentefinancieroEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TipocomponentefinancieroEntity newTipocomponentefinancieroEntity() {

		Long id = mockValues.nextLong();

		TipocomponentefinancieroEntity tipocomponentefinancieroEntity = new TipocomponentefinancieroEntity();
		tipocomponentefinancieroEntity.setId(id);
		return tipocomponentefinancieroEntity;
	}
	
}
