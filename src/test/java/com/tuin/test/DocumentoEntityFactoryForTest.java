package com.tuin.test;

import com.tuin.bean.jpa.DocumentoEntity;

public class DocumentoEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public DocumentoEntity newDocumentoEntity() {

		Long id = mockValues.nextLong();

		DocumentoEntity documentoEntity = new DocumentoEntity();
		documentoEntity.setId(id);
		return documentoEntity;
	}
	
}
