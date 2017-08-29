package com.tuin.test;

import com.tuin.bean.jpa.DestinohorarioEntity;

public class DestinohorarioEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public DestinohorarioEntity newDestinohorarioEntity() {

		Long id = mockValues.nextLong();

		DestinohorarioEntity destinohorarioEntity = new DestinohorarioEntity();
		destinohorarioEntity.setId(id);
		return destinohorarioEntity;
	}
	
}
