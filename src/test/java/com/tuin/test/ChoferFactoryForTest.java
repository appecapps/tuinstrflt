package com.tuin.test;

import com.tuin.bean.Chofer;

public class ChoferFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Chofer newChofer() {

		Long id = mockValues.nextLong();

		Chofer chofer = new Chofer();
		chofer.setId(id);
		return chofer;
	}
	
}
