package com.tuin.test;

import com.tuin.bean.jpa.MenuEntity;

public class MenuEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public MenuEntity newMenuEntity() {

		Long id = mockValues.nextLong();

		MenuEntity menuEntity = new MenuEntity();
		menuEntity.setId(id);
		return menuEntity;
	}
	
}
