/*
 * Created on 28 ago 2017 ( Time 17:51:17 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.tuin.bean.Modelo;
import com.tuin.bean.jpa.ModeloEntity;
import java.util.List;
import com.tuin.business.service.mapping.ModeloServiceMapper;
import com.tuin.persistence.services.jpa.ModeloPersistenceJPA;
import com.tuin.test.ModeloFactoryForTest;
import com.tuin.test.ModeloEntityFactoryForTest;
import com.tuin.test.MockValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test : Implementation of ModeloService
 */
@RunWith(MockitoJUnitRunner.class)
public class ModeloServiceImplTest {

	@InjectMocks
	private ModeloServiceImpl modeloService;
	@Mock
	private ModeloPersistenceJPA modeloPersistenceJPA;
	@Mock
	private ModeloServiceMapper modeloServiceMapper;
	
	private ModeloFactoryForTest modeloFactoryForTest = new ModeloFactoryForTest();

	private ModeloEntityFactoryForTest modeloEntityFactoryForTest = new ModeloEntityFactoryForTest();

	private MockValues mockValues = new MockValues();
	
	@Test
	public void findById() {
		// Given
		Long id = mockValues.nextLong();
		
		ModeloEntity modeloEntity = modeloPersistenceJPA.load(id);
		
		Modelo modelo = modeloFactoryForTest.newModelo();
		when(modeloServiceMapper.mapModeloEntityToModelo(modeloEntity)).thenReturn(modelo);

		// When
		Modelo modeloFound = modeloService.findById(id);

		// Then
		assertEquals(modelo.getId(),modeloFound.getId());
	}

	@Test
	public void findAll() {
		// Given
		List<ModeloEntity> modeloEntitys = new ArrayList<ModeloEntity>();
		ModeloEntity modeloEntity1 = modeloEntityFactoryForTest.newModeloEntity();
		modeloEntitys.add(modeloEntity1);
		ModeloEntity modeloEntity2 = modeloEntityFactoryForTest.newModeloEntity();
		modeloEntitys.add(modeloEntity2);
		when(modeloPersistenceJPA.loadAll()).thenReturn(modeloEntitys);
		
		Modelo modelo1 = modeloFactoryForTest.newModelo();
		when(modeloServiceMapper.mapModeloEntityToModelo(modeloEntity1)).thenReturn(modelo1);
		Modelo modelo2 = modeloFactoryForTest.newModelo();
		when(modeloServiceMapper.mapModeloEntityToModelo(modeloEntity2)).thenReturn(modelo2);

		// When
		List<Modelo> modelosFounds = modeloService.findAll();

		// Then
		assertTrue(modelo1 == modelosFounds.get(0));
		assertTrue(modelo2 == modelosFounds.get(1));
	}

	@Test
	public void create() {
		// Given
		Modelo modelo = modeloFactoryForTest.newModelo();

		ModeloEntity modeloEntity = modeloEntityFactoryForTest.newModeloEntity();
		when(modeloPersistenceJPA.load(modelo.getId())).thenReturn(null);
		
		modeloEntity = new ModeloEntity();
		modeloServiceMapper.mapModeloToModeloEntity(modelo, modeloEntity);
		ModeloEntity modeloEntitySaved = modeloPersistenceJPA.save(modeloEntity);
		
		Modelo modeloSaved = modeloFactoryForTest.newModelo();
		when(modeloServiceMapper.mapModeloEntityToModelo(modeloEntitySaved)).thenReturn(modeloSaved);

		// When
		Modelo modeloResult = modeloService.create(modelo);

		// Then
		assertTrue(modeloResult == modeloSaved);
	}

	@Test
	public void createKOExists() {
		// Given
		Modelo modelo = modeloFactoryForTest.newModelo();

		ModeloEntity modeloEntity = modeloEntityFactoryForTest.newModeloEntity();
		when(modeloPersistenceJPA.load(modelo.getId())).thenReturn(modeloEntity);

		// When
		Exception exception = null;
		try {
			modeloService.create(modelo);
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
		Modelo modelo = modeloFactoryForTest.newModelo();

		ModeloEntity modeloEntity = modeloEntityFactoryForTest.newModeloEntity();
		when(modeloPersistenceJPA.load(modelo.getId())).thenReturn(modeloEntity);
		
		ModeloEntity modeloEntitySaved = modeloEntityFactoryForTest.newModeloEntity();
		when(modeloPersistenceJPA.save(modeloEntity)).thenReturn(modeloEntitySaved);
		
		Modelo modeloSaved = modeloFactoryForTest.newModelo();
		when(modeloServiceMapper.mapModeloEntityToModelo(modeloEntitySaved)).thenReturn(modeloSaved);

		// When
		Modelo modeloResult = modeloService.update(modelo);

		// Then
		verify(modeloServiceMapper).mapModeloToModeloEntity(modelo, modeloEntity);
		assertTrue(modeloResult == modeloSaved);
	}

	@Test
	public void delete() {
		// Given
		Long id = mockValues.nextLong();

		// When
		modeloService.delete(id);

		// Then
		verify(modeloPersistenceJPA).delete(id);
		
	}

}
