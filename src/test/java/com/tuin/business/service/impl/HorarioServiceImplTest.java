/*
 * Created on 28 ago 2017 ( Time 17:51:16 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.tuin.bean.Horario;
import com.tuin.bean.jpa.HorarioEntity;
import java.util.Date;
import java.util.List;
import com.tuin.business.service.mapping.HorarioServiceMapper;
import com.tuin.persistence.services.jpa.HorarioPersistenceJPA;
import com.tuin.test.HorarioFactoryForTest;
import com.tuin.test.HorarioEntityFactoryForTest;
import com.tuin.test.MockValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test : Implementation of HorarioService
 */
@RunWith(MockitoJUnitRunner.class)
public class HorarioServiceImplTest {

	@InjectMocks
	private HorarioServiceImpl horarioService;
	@Mock
	private HorarioPersistenceJPA horarioPersistenceJPA;
	@Mock
	private HorarioServiceMapper horarioServiceMapper;
	
	private HorarioFactoryForTest horarioFactoryForTest = new HorarioFactoryForTest();

	private HorarioEntityFactoryForTest horarioEntityFactoryForTest = new HorarioEntityFactoryForTest();

	private MockValues mockValues = new MockValues();
	
	@Test
	public void findById() {
		// Given
		Long id = mockValues.nextLong();
		
		HorarioEntity horarioEntity = horarioPersistenceJPA.load(id);
		
		Horario horario = horarioFactoryForTest.newHorario();
		when(horarioServiceMapper.mapHorarioEntityToHorario(horarioEntity)).thenReturn(horario);

		// When
		Horario horarioFound = horarioService.findById(id);

		// Then
		assertEquals(horario.getId(),horarioFound.getId());
	}

	@Test
	public void findAll() {
		// Given
		List<HorarioEntity> horarioEntitys = new ArrayList<HorarioEntity>();
		HorarioEntity horarioEntity1 = horarioEntityFactoryForTest.newHorarioEntity();
		horarioEntitys.add(horarioEntity1);
		HorarioEntity horarioEntity2 = horarioEntityFactoryForTest.newHorarioEntity();
		horarioEntitys.add(horarioEntity2);
		when(horarioPersistenceJPA.loadAll()).thenReturn(horarioEntitys);
		
		Horario horario1 = horarioFactoryForTest.newHorario();
		when(horarioServiceMapper.mapHorarioEntityToHorario(horarioEntity1)).thenReturn(horario1);
		Horario horario2 = horarioFactoryForTest.newHorario();
		when(horarioServiceMapper.mapHorarioEntityToHorario(horarioEntity2)).thenReturn(horario2);

		// When
		List<Horario> horariosFounds = horarioService.findAll();

		// Then
		assertTrue(horario1 == horariosFounds.get(0));
		assertTrue(horario2 == horariosFounds.get(1));
	}

	@Test
	public void create() {
		// Given
		Horario horario = horarioFactoryForTest.newHorario();

		HorarioEntity horarioEntity = horarioEntityFactoryForTest.newHorarioEntity();
		when(horarioPersistenceJPA.load(horario.getId())).thenReturn(null);
		
		horarioEntity = new HorarioEntity();
		horarioServiceMapper.mapHorarioToHorarioEntity(horario, horarioEntity);
		HorarioEntity horarioEntitySaved = horarioPersistenceJPA.save(horarioEntity);
		
		Horario horarioSaved = horarioFactoryForTest.newHorario();
		when(horarioServiceMapper.mapHorarioEntityToHorario(horarioEntitySaved)).thenReturn(horarioSaved);

		// When
		Horario horarioResult = horarioService.create(horario);

		// Then
		assertTrue(horarioResult == horarioSaved);
	}

	@Test
	public void createKOExists() {
		// Given
		Horario horario = horarioFactoryForTest.newHorario();

		HorarioEntity horarioEntity = horarioEntityFactoryForTest.newHorarioEntity();
		when(horarioPersistenceJPA.load(horario.getId())).thenReturn(horarioEntity);

		// When
		Exception exception = null;
		try {
			horarioService.create(horario);
		} catch(Exception e) {
			exception = e;
		}

		// Then
		assertTrue(exception instanceof IllegalStateException);
		assertEquals("already.exists", exception.getMessage());
	}

	@Test
	public void update() {
		// Given
		Horario horario = horarioFactoryForTest.newHorario();

		HorarioEntity horarioEntity = horarioEntityFactoryForTest.newHorarioEntity();
		when(horarioPersistenceJPA.load(horario.getId())).thenReturn(horarioEntity);
		
		HorarioEntity horarioEntitySaved = horarioEntityFactoryForTest.newHorarioEntity();
		when(horarioPersistenceJPA.save(horarioEntity)).thenReturn(horarioEntitySaved);
		
		Horario horarioSaved = horarioFactoryForTest.newHorario();
		when(horarioServiceMapper.mapHorarioEntityToHorario(horarioEntitySaved)).thenReturn(horarioSaved);

		// When
		Horario horarioResult = horarioService.update(horario);

		// Then
		verify(horarioServiceMapper).mapHorarioToHorarioEntity(horario, horarioEntity);
		assertTrue(horarioResult == horarioSaved);
	}

	@Test
	public void delete() {
		// Given
		Long id = mockValues.nextLong();

		// When
		horarioService.delete(id);

		// Then
		verify(horarioPersistenceJPA).delete(id);
		
	}

}
