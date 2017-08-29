package com.tuin.test;

import com.tuin.bean.Horario;

public class HorarioFactoryForTest {

	private MockValues mockValues = new MockValues();
	
	public Horario newHorario() {

		Long id = mockValues.nextLong();

		Horario horario = new Horario();
		horario.setId(id);
		return horario;
	}
	
}
