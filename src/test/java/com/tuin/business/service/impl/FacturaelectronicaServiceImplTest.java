/*
 * Created on 28 ago 2017 ( Time 17:51:15 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.tuin.bean.Facturaelectronica;
import com.tuin.bean.jpa.FacturaelectronicaEntity;
import java.util.Date;
import com.tuin.business.service.mapping.FacturaelectronicaServiceMapper;
import com.tuin.persistence.services.jpa.FacturaelectronicaPersistenceJPA;
import com.tuin.test.FacturaelectronicaFactoryForTest;
import com.tuin.test.FacturaelectronicaEntityFactoryForTest;
import com.tuin.test.MockValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test : Implementation of FacturaelectronicaService
 */
@RunWith(MockitoJUnitRunner.class)
public class FacturaelectronicaServiceImplTest {

	@InjectMocks
	private FacturaelectronicaServiceImpl facturaelectronicaService;
	@Mock
	private FacturaelectronicaPersistenceJPA facturaelectronicaPersistenceJPA;
	@Mock
	private FacturaelectronicaServiceMapper facturaelectronicaServiceMapper;
	
	private FacturaelectronicaFactoryForTest facturaelectronicaFactoryForTest = new FacturaelectronicaFactoryForTest();

	private FacturaelectronicaEntityFactoryForTest facturaelectronicaEntityFactoryForTest = new FacturaelectronicaEntityFactoryForTest();

	private MockValues mockValues = new MockValues();
	
	@Test
	public void findById() {
		// Given
		Long id = mockValues.nextLong();
		
		FacturaelectronicaEntity facturaelectronicaEntity = facturaelectronicaPersistenceJPA.load(id);
		
		Facturaelectronica facturaelectronica = facturaelectronicaFactoryForTest.newFacturaelectronica();
		when(facturaelectronicaServiceMapper.mapFacturaelectronicaEntityToFacturaelectronica(facturaelectronicaEntity)).thenReturn(facturaelectronica);

		// When
		Facturaelectronica facturaelectronicaFound = facturaelectronicaService.findById(id);

		// Then
		assertEquals(facturaelectronica.getId(),facturaelectronicaFound.getId());
	}

	@Test
	public void findAll() {
		// Given
		List<FacturaelectronicaEntity> facturaelectronicaEntitys = new ArrayList<FacturaelectronicaEntity>();
		FacturaelectronicaEntity facturaelectronicaEntity1 = facturaelectronicaEntityFactoryForTest.newFacturaelectronicaEntity();
		facturaelectronicaEntitys.add(facturaelectronicaEntity1);
		FacturaelectronicaEntity facturaelectronicaEntity2 = facturaelectronicaEntityFactoryForTest.newFacturaelectronicaEntity();
		facturaelectronicaEntitys.add(facturaelectronicaEntity2);
		when(facturaelectronicaPersistenceJPA.loadAll()).thenReturn(facturaelectronicaEntitys);
		
		Facturaelectronica facturaelectronica1 = facturaelectronicaFactoryForTest.newFacturaelectronica();
		when(facturaelectronicaServiceMapper.mapFacturaelectronicaEntityToFacturaelectronica(facturaelectronicaEntity1)).thenReturn(facturaelectronica1);
		Facturaelectronica facturaelectronica2 = facturaelectronicaFactoryForTest.newFacturaelectronica();
		when(facturaelectronicaServiceMapper.mapFacturaelectronicaEntityToFacturaelectronica(facturaelectronicaEntity2)).thenReturn(facturaelectronica2);

		// When
		List<Facturaelectronica> facturaelectronicasFounds = facturaelectronicaService.findAll();

		// Then
		assertTrue(facturaelectronica1 == facturaelectronicasFounds.get(0));
		assertTrue(facturaelectronica2 == facturaelectronicasFounds.get(1));
	}

	@Test
	public void create() {
		// Given
		Facturaelectronica facturaelectronica = facturaelectronicaFactoryForTest.newFacturaelectronica();

		FacturaelectronicaEntity facturaelectronicaEntity = facturaelectronicaEntityFactoryForTest.newFacturaelectronicaEntity();
		when(facturaelectronicaPersistenceJPA.load(facturaelectronica.getId())).thenReturn(null);
		
		facturaelectronicaEntity = new FacturaelectronicaEntity();
		facturaelectronicaServiceMapper.mapFacturaelectronicaToFacturaelectronicaEntity(facturaelectronica, facturaelectronicaEntity);
		FacturaelectronicaEntity facturaelectronicaEntitySaved = facturaelectronicaPersistenceJPA.save(facturaelectronicaEntity);
		
		Facturaelectronica facturaelectronicaSaved = facturaelectronicaFactoryForTest.newFacturaelectronica();
		when(facturaelectronicaServiceMapper.mapFacturaelectronicaEntityToFacturaelectronica(facturaelectronicaEntitySaved)).thenReturn(facturaelectronicaSaved);

		// When
		Facturaelectronica facturaelectronicaResult = facturaelectronicaService.create(facturaelectronica);

		// Then
		assertTrue(facturaelectronicaResult == facturaelectronicaSaved);
	}

	@Test
	public void createKOExists() {
		// Given
		Facturaelectronica facturaelectronica = facturaelectronicaFactoryForTest.newFacturaelectronica();

		FacturaelectronicaEntity facturaelectronicaEntity = facturaelectronicaEntityFactoryForTest.newFacturaelectronicaEntity();
		when(facturaelectronicaPersistenceJPA.load(facturaelectronica.getId())).thenReturn(facturaelectronicaEntity);

		// When
		Exception exception = null;
		try {
			facturaelectronicaService.create(facturaelectronica);
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
		Facturaelectronica facturaelectronica = facturaelectronicaFactoryForTest.newFacturaelectronica();

		FacturaelectronicaEntity facturaelectronicaEntity = facturaelectronicaEntityFactoryForTest.newFacturaelectronicaEntity();
		when(facturaelectronicaPersistenceJPA.load(facturaelectronica.getId())).thenReturn(facturaelectronicaEntity);
		
		FacturaelectronicaEntity facturaelectronicaEntitySaved = facturaelectronicaEntityFactoryForTest.newFacturaelectronicaEntity();
		when(facturaelectronicaPersistenceJPA.save(facturaelectronicaEntity)).thenReturn(facturaelectronicaEntitySaved);
		
		Facturaelectronica facturaelectronicaSaved = facturaelectronicaFactoryForTest.newFacturaelectronica();
		when(facturaelectronicaServiceMapper.mapFacturaelectronicaEntityToFacturaelectronica(facturaelectronicaEntitySaved)).thenReturn(facturaelectronicaSaved);

		// When
		Facturaelectronica facturaelectronicaResult = facturaelectronicaService.update(facturaelectronica);

		// Then
		verify(facturaelectronicaServiceMapper).mapFacturaelectronicaToFacturaelectronicaEntity(facturaelectronica, facturaelectronicaEntity);
		assertTrue(facturaelectronicaResult == facturaelectronicaSaved);
	}

	@Test
	public void delete() {
		// Given
		Long id = mockValues.nextLong();

		// When
		facturaelectronicaService.delete(id);

		// Then
		verify(facturaelectronicaPersistenceJPA).delete(id);
		
	}

}
