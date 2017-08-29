package com.tuin.test;

import com.tuin.bean.jpa.TipodocumentoEntity;

public class TipodocumentoEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public TipodocumentoEntity newTipodocumentoEntity() {

		Long id = mockValues.nextLong();

		TipodocumentoEntity tipodocumentoEntity = new TipodocumentoEntity();
		tipodocumentoEntity.setId(id);
		return tipodocumentoEntity;
	}
	
}
