package com.tuin.test;

import com.tuin.bean.jpa.ModeloEntity;

public class ModeloEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public ModeloEntity newModeloEntity() {

		Long id = mockValues.nextLong();

		ModeloEntity modeloEntity = new ModeloEntity();
		modeloEntity.setId(id);
		return modeloEntity;
	}
	
}
