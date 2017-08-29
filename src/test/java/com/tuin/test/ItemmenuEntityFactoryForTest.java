package com.tuin.test;

import com.tuin.bean.jpa.ItemmenuEntity;

public class ItemmenuEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public ItemmenuEntity newItemmenuEntity() {

		Long id = mockValues.nextLong();

		ItemmenuEntity itemmenuEntity = new ItemmenuEntity();
		itemmenuEntity.setId(id);
		return itemmenuEntity;
	}
	
}
