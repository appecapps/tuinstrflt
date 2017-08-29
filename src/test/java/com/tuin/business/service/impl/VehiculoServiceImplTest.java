/*
 * Created on 28 ago 2017 ( Time 17:51:20 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.tuin.bean.Vehiculo;
import com.tuin.bean.jpa.VehiculoEntity;
import java.util.List;
import com.tuin.business.service.mapping.VehiculoServiceMapper;
import com.tuin.persistence.services.jpa.VehiculoPersistenceJPA;
import com.tuin.test.VehiculoFactoryForTest;
import com.tuin.test.VehiculoEntityFactoryForTest;
import com.tuin.test.MockValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test : Implementation of VehiculoService
 */
@RunWith(MockitoJUnitRunner.class)
public class VehiculoServiceImplTest {

	@InjectMocks
	private VehiculoServiceImpl vehiculoService;
	@Mock
	private VehiculoPersistenceJPA vehiculoPersistenceJPA;
	@Mock
	private VehiculoServiceMapper vehiculoServiceMapper;
	
	private VehiculoFactoryForTest vehiculoFactoryForTest = new VehiculoFactoryForTest();

	private VehiculoEntityFactoryForTest vehiculoEntityFactoryForTest = new VehiculoEntityFactoryForTest();

	private MockValues mockValues = new MockValues();
	
	@Test
	public void findById() {
		// Given
		Long id = mockValues.nextLong();
		
		VehiculoEntity vehiculoEntity = vehiculoPersistenceJPA.load(id);
		
		Vehiculo vehiculo = vehiculoFactoryForTest.newVehiculo();
		when(vehiculoServiceMapper.mapVehiculoEntityToVehiculo(vehiculoEntity)).thenReturn(vehiculo);

		// When
		Vehiculo vehiculoFound = vehiculoService.findById(id);

		// Then
		assertEquals(vehiculo.getId(),vehiculoFound.getId());
	}

	@Test
	public void findAll() {
		// Given
		List<VehiculoEntity> vehiculoEntitys = new ArrayList<VehiculoEntity>();
		VehiculoEntity vehiculoEntity1 = vehiculoEntityFactoryForTest.newVehiculoEntity();
		vehiculoEntitys.add(vehiculoEntity1);
		VehiculoEntity vehiculoEntity2 = vehiculoEntityFactoryForTest.newVehiculoEntity();
		vehiculoEntitys.add(vehiculoEntity2);
		when(vehiculoPersistenceJPA.loadAll()).thenReturn(vehiculoEntitys);
		
		Vehiculo vehiculo1 = vehiculoFactoryForTest.newVehiculo();
		when(vehiculoServiceMapper.mapVehiculoEntityToVehiculo(vehiculoEntity1)).thenReturn(vehiculo1);
		Vehiculo vehiculo2 = vehiculoFactoryForTest.newVehiculo();
		when(vehiculoServiceMapper.mapVehiculoEntityToVehiculo(vehiculoEntity2)).thenReturn(vehiculo2);

		// When
		List<Vehiculo> vehiculosFounds = vehiculoService.findAll();

		// Then
		assertTrue(vehiculo1 == vehiculosFounds.get(0));
		assertTrue(vehiculo2 == vehiculosFounds.get(1));
	}

	@Test
	public void create() {
		// Given
		Vehiculo vehiculo = vehiculoFactoryForTest.newVehiculo();

		VehiculoEntity vehiculoEntity = vehiculoEntityFactoryForTest.newVehiculoEntity();
		when(vehiculoPersistenceJPA.load(vehiculo.getId())).thenReturn(null);
		
		vehiculoEntity = new VehiculoEntity();
		vehiculoServiceMapper.mapVehiculoToVehiculoEntity(vehiculo, vehiculoEntity);
		VehiculoEntity vehiculoEntitySaved = vehiculoPersistenceJPA.save(vehiculoEntity);
		
		Vehiculo vehiculoSaved = vehiculoFactoryForTest.newVehiculo();
		when(vehiculoServiceMapper.mapVehiculoEntityToVehiculo(vehiculoEntitySaved)).thenReturn(vehiculoSaved);

		// When
		Vehiculo vehiculoResult = vehiculoService.create(vehiculo);

		// Then
		assertTrue(vehiculoResult == vehiculoSaved);
	}

	@Test
	public void createKOExists() {
		// Given
		Vehiculo vehiculo = vehiculoFactoryForTest.newVehiculo();

		VehiculoEntity vehiculoEntity = vehiculoEntityFactoryForTest.newVehiculoEntity();
		when(vehiculoPersistenceJPA.load(vehiculo.getId())).thenReturn(vehiculoEntity);

		// When
		Exception exception = null;
		try {
			vehiculoService.create(vehiculo);
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
		Vehiculo vehiculo = vehiculoFactoryForTest.newVehiculo();

		VehiculoEntity vehiculoEntity = vehiculoEntityFactoryForTest.newVehiculoEntity();
		when(vehiculoPersistenceJPA.load(vehiculo.getId())).thenReturn(vehiculoEntity);
		
		VehiculoEntity vehiculoEntitySaved = vehiculoEntityFactoryForTest.newVehiculoEntity();
		when(vehiculoPersistenceJPA.save(vehiculoEntity)).thenReturn(vehiculoEntitySaved);
		
		Vehiculo vehiculoSaved = vehiculoFactoryForTest.newVehiculo();
		when(vehiculoServiceMapper.mapVehiculoEntityToVehiculo(vehiculoEntitySaved)).thenReturn(vehiculoSaved);

		// When
		Vehiculo vehiculoResult = vehiculoService.update(vehiculo);

		// Then
		verify(vehiculoServiceMapper).mapVehiculoToVehiculoEntity(vehiculo, vehiculoEntity);
		assertTrue(vehiculoResult == vehiculoSaved);
	}

	@Test
	public void delete() {
		// Given
		Long id = mockValues.nextLong();

		// When
		vehiculoService.delete(id);

		// Then
		verify(vehiculoPersistenceJPA).delete(id);
		
	}

}
