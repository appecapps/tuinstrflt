package com.tuin.test;

import com.tuin.bean.Facturaelectronica;

public class FacturaelectronicaFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Facturaelectronica newFacturaelectronica() {

		Long id = mockValues.nextLong();

		Facturaelectronica facturaelectronica = new Facturaelectronica();
		facturaelectronica.setId(id);
		return facturaelectronica;
	}
	
}
