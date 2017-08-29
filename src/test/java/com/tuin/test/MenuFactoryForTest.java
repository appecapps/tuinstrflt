package com.tuin.test;

import com.tuin.bean.Menu;

public class MenuFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Menu newMenu() {

		Long id = mockValues.nextLong();

		Menu menu = new Menu();
		menu.setId(id);
		return menu;
	}
	
}
