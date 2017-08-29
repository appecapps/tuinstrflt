package com.tuin.test;

import com.tuin.bean.Itemmenu;

public class ItemmenuFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Itemmenu newItemmenu() {

		Long id = mockValues.nextLong();

		Itemmenu itemmenu = new Itemmenu();
		itemmenu.setId(id);
		return itemmenu;
	}
	
}
