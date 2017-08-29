package com.tuin.test;

import com.tuin.bean.jpa.ColorEntity;

public class ColorEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public ColorEntity newColorEntity() {

		Long id = mockValues.nextLong();

		ColorEntity colorEntity = new ColorEntity();
		colorEntity.setId(id);
		return colorEntity;
	}
	
}
