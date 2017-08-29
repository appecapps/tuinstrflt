package com.tuin.test;

import com.tuin.bean.jpa.FormapagodocumentoEntity;

public class FormapagodocumentoEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public FormapagodocumentoEntity newFormapagodocumentoEntity() {

		Long id = mockValues.nextLong();

		FormapagodocumentoEntity formapagodocumentoEntity = new FormapagodocumentoEntity();
		formapagodocumentoEntity.setId(id);
		return formapagodocumentoEntity;
	}
	
}
