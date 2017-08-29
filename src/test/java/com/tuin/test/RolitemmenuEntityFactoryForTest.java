package com.tuin.test;

import com.tuin.bean.jpa.RolitemmenuEntity;

public class RolitemmenuEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public RolitemmenuEntity newRolitemmenuEntity() {

		Long id = mockValues.nextLong();

		RolitemmenuEntity rolitemmenuEntity = new RolitemmenuEntity();
		rolitemmenuEntity.setId(id);
		return rolitemmenuEntity;
	}
	
}
