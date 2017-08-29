package com.tuin.test;

import com.tuin.bean.jpa.FormapagoEntity;

public class FormapagoEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public FormapagoEntity newFormapagoEntity() {

		Long id = mockValues.nextLong();

		FormapagoEntity formapagoEntity = new FormapagoEntity();
		formapagoEntity.setId(id);
		return formapagoEntity;
	}
	
}
