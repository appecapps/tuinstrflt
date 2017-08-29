package com.tuin.test;

import com.tuin.bean.Tipodocumento;

public class TipodocumentoFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Tipodocumento newTipodocumento() {

		Long id = mockValues.nextLong();

		Tipodocumento tipodocumento = new Tipodocumento();
		tipodocumento.setId(id);
		return tipodocumento;
	}
	
}
