package com.tuin.test;

import com.tuin.bean.Tipocomponentefinanciero;

public class TipocomponentefinancieroFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Tipocomponentefinanciero newTipocomponentefinanciero() {

		Long id = mockValues.nextLong();

		Tipocomponentefinanciero tipocomponentefinanciero = new Tipocomponentefinanciero();
		tipocomponentefinanciero.setId(id);
		return tipocomponentefinanciero;
	}
	
}
