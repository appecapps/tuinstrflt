package com.tuin.test;

import com.tuin.bean.Documento;

public class DocumentoFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Documento newDocumento() {

		Long id = mockValues.nextLong();

		Documento documento = new Documento();
		documento.setId(id);
		return documento;
	}
	
}
