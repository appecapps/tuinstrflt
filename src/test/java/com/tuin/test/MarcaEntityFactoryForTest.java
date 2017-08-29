package com.tuin.test;

import com.tuin.bean.jpa.MarcaEntity;

public class MarcaEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public MarcaEntity newMarcaEntity() {

		Long id = mockValues.nextLong();

		MarcaEntity marcaEntity = new MarcaEntity();
		marcaEntity.setId(id);
		return marcaEntity;
	}
	
}
