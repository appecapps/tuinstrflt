package com.tuin.test;

import com.tuin.bean.Ciudad;

public class CiudadFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Ciudad newCiudad() {

		Long id = mockValues.nextLong();

		Ciudad ciudad = new Ciudad();
		ciudad.setId(id);
		return ciudad;
	}
	
}
