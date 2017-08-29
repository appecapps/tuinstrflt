package com.tuin.test;

import com.tuin.bean.Rol;

public class RolFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Rol newRol() {

		Long id = mockValues.nextLong();

		Rol rol = new Rol();
		rol.setId(id);
		return rol;
	}
	
}
