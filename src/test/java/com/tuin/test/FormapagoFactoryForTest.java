package com.tuin.test;

import com.tuin.bean.Formapago;

public class FormapagoFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Formapago newFormapago() {

		Long id = mockValues.nextLong();

		Formapago formapago = new Formapago();
		formapago.setId(id);
		return formapago;
	}
	
}
