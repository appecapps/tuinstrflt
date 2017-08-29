package com.tuin.test;

import com.tuin.bean.Destinohorario;

public class DestinohorarioFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Destinohorario newDestinohorario() {

		Long id = mockValues.nextLong();

		Destinohorario destinohorario = new Destinohorario();
		destinohorario.setId(id);
		return destinohorario;
	}
	
}
