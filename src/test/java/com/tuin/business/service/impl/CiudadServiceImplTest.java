/*
 * Created on 28 ago 2017 ( Time 17:51:12 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.tuin.bean.Ciudad;
import com.tuin.bean.jpa.CiudadEntity;
import java.math.BigDecimal;
import java.util.List;
import com.tuin.business.service.mapping.CiudadServiceMapper;
import com.tuin.persistence.services.jpa.CiudadPersistenceJPA;
import com.tuin.test.CiudadFactoryForTest;
import com.tuin.test.CiudadEntityFactoryForTest;
import com.tuin.test.MockValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test : Implementation of CiudadService
 */
@RunWith(MockitoJUnitRunner.class)
public class CiudadServiceImplTest {

	@InjectMocks
	private CiudadServiceImpl ciudadService;
	@Mock
	private CiudadPersistenceJPA ciudadPersistenceJPA;
	@Mock
	private CiudadServiceMapper ciudadServiceMapper;
	
	private CiudadFactoryForTest ciudadFactoryForTest = new CiudadFactoryForTest();

	private CiudadEntityFactoryForTest ciudadEntityFactoryForTest = new CiudadEntityFactoryForTest();

	private MockValues mockValues = new MockValues();
	
	@Test
	public void findById() {
		// Given
		Long id = mockValues.nextLong();
		
		CiudadEntity ciudadEntity = ciudadPersistenceJPA.load(id);
		
		Ciudad ciudad = ciudadFactoryForTest.newCiudad();
		when(ciudadServiceMapper.mapCiudadEntityToCiudad(ciudadEntity)).thenReturn(ciudad);

		// When
		Ciudad ciudadFound = ciudadService.findById(id);

		// Then
		assertEquals(ciudad.getId(),ciudadFound.getId());
	}

	@Test
	public void findAll() {
		// Given
		List<CiudadEntity> ciudadEntitys = new ArrayList<CiudadEntity>();
		CiudadEntity ciudadEntity1 = ciudadEntityFactoryForTest.newCiudadEntity();
		ciudadEntitys.add(ciudadEntity1);
		CiudadEntity ciudadEntity2 = ciudadEntityFactoryForTest.newCiudadEntity();
		ciudadEntitys.add(ciudadEntity2);
		when(ciudadPersistenceJPA.loadAll()).thenReturn(ciudadEntitys);
		
		Ciudad ciudad1 = ciudadFactoryForTest.newCiudad();
		when(ciudadServiceMapper.mapCiudadEntityToCiudad(ciudadEntity1)).thenReturn(ciudad1);
		Ciudad ciudad2 = ciudadFactoryForTest.newCiudad();
		when(ciudadServiceMapper.mapCiudadEntityToCiudad(ciudadEntity2)).thenReturn(ciudad2);

		// When
		List<Ciudad> ciudadsFounds = ciudadService.findAll();

		// Then
		assertTrue(ciudad1 == ciudadsFounds.get(0));
		assertTrue(ciudad2 == ciudadsFounds.get(1));
	}

	@Test
	public void create() {
		// Given
		Ciudad ciudad = ciudadFactoryForTest.newCiudad();

		CiudadEntity ciudadEntity = ciudadEntityFactoryForTest.newCiudadEntity();
		when(ciudadPersistenceJPA.load(ciudad.getId())).thenReturn(null);
		
		ciudadEntity = new CiudadEntity();
		ciudadServiceMapper.mapCiudadToCiudadEntity(ciudad, ciudadEntity);
		CiudadEntity ciudadEntitySaved = ciudadPersistenceJPA.save(ciudadEntity);
		
		Ciudad ciudadSaved = ciudadFactoryForTest.newCiudad();
		when(ciudadServiceMapper.mapCiudadEntityToCiudad(ciudadEntitySaved)).thenReturn(ciudadSaved);

		// When
		Ciudad ciudadResult = ciudadService.create(ciudad);

		// Then
		assertTrue(ciudadResult == ciudadSaved);
	}

	@Test
	public void createKOExists() {
		// Given
		Ciudad ciudad = ciudadFactoryForTest.newCiudad();

		CiudadEntity ciudadEntity = ciudadEntityFactoryForTest.newCiudadEntity();
		when(ciudadPersistenceJPA.load(ciudad.getId())).thenReturn(ciudadEntity);

		// When
		Exception exception = null;
		try {
			ciudadService.create(ciudad);
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
		Ciudad ciudad = ciudadFactoryForTest.newCiudad();

		CiudadEntity ciudadEntity = ciudadEntityFactoryForTest.newCiudadEntity();
		when(ciudadPersistenceJPA.load(ciudad.getId())).thenReturn(ciudadEntity);
		
		CiudadEntity ciudadEntitySaved = ciudadEntityFactoryForTest.newCiudadEntity();
		when(ciudadPersistenceJPA.save(ciudadEntity)).thenReturn(ciudadEntitySaved);
		
		Ciudad ciudadSaved = ciudadFactoryForTest.newCiudad();
		when(ciudadServiceMapper.mapCiudadEntityToCiudad(ciudadEntitySaved)).thenReturn(ciudadSaved);

		// When
		Ciudad ciudadResult = ciudadService.update(ciudad);

		// Then
		verify(ciudadServiceMapper).mapCiudadToCiudadEntity(ciudad, ciudadEntity);
		assertTrue(ciudadResult == ciudadSaved);
	}

	@Test
	public void delete() {
		// Given
		Long id = mockValues.nextLong();

		// When
		ciudadService.delete(id);

		// Then
		verify(ciudadPersistenceJPA).delete(id);
		
	}

}
