package com.tuin.test;

import com.tuin.bean.Formapagodocumento;

public class FormapagodocumentoFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Formapagodocumento newFormapagodocumento() {

		Long id = mockValues.nextLong();

		Formapagodocumento formapagodocumento = new Formapagodocumento();
		formapagodocumento.setId(id);
		return formapagodocumento;
	}
	
}
