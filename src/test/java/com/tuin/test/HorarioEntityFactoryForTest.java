package com.tuin.test;

import com.tuin.bean.jpa.HorarioEntity;

public class HorarioEntityFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public HorarioEntity newHorarioEntity() {

		Long id = mockValues.nextLong();

		HorarioEntity horarioEntity = new HorarioEntity();
		horarioEntity.setId(id);
		return horarioEntity;
	}
	
}
