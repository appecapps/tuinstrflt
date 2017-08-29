package com.tuin.test;

import com.tuin.bean.Rolitemmenu;

public class RolitemmenuFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Rolitemmenu newRolitemmenu() {

		Long id = mockValues.nextLong();

		Rolitemmenu rolitemmenu = new Rolitemmenu();
		rolitemmenu.setId(id);
		return rolitemmenu;
	}
	
}
