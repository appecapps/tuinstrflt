package com.tuin.test;

import com.tuin.bean.Entidadrol;

public class EntidadrolFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Entidadrol newEntidadrol() {

		Long id = mockValues.nextLong();

		Entidadrol entidadrol = new Entidadrol();
		entidadrol.setId(id);
		return entidadrol;
	}
	
}
