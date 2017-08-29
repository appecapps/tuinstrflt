/*
 * Created on 28 ago 2017 ( Time 17:51:13 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.tuin.bean.Clienteviaje;
import com.tuin.bean.jpa.ClienteviajeEntity;
import java.math.BigDecimal;
import java.util.List;
import com.tuin.business.service.mapping.ClienteviajeServiceMapper;
import com.tuin.persistence.services.jpa.ClienteviajePersistenceJPA;
import com.tuin.test.ClienteviajeFactoryForTest;
import com.tuin.test.ClienteviajeEntityFactoryForTest;
import com.tuin.test.MockValues;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Test : Implementation of ClienteviajeService
 */
@RunWith(MockitoJUnitRunner.class)
public class ClienteviajeServiceImplTest {

	@InjectMocks
	private ClienteviajeServiceImpl clienteviajeService;
	@Mock
	private ClienteviajePersistenceJPA clienteviajePersistenceJPA;
	@Mock
	private ClienteviajeServiceMapper clienteviajeServiceMapper;
	
	private ClienteviajeFactoryForTest clienteviajeFactoryForTest = new ClienteviajeFactoryForTest();

	private ClienteviajeEntityFactoryForTest clienteviajeEntityFactoryForTest = new ClienteviajeEntityFactoryForTest();

	private MockValues mockValues = new MockValues();
	
	@Test
	public void findById() {
		// Given
		Long id = mockValues.nextLong();
		
		ClienteviajeEntity clienteviajeEntity = clienteviajePersistenceJPA.load(id);
		
		Clienteviaje clienteviaje = clienteviajeFactoryForTest.newClienteviaje();
		when(clienteviajeServiceMapper.mapClienteviajeEntityToClienteviaje(clienteviajeEntity)).thenReturn(clienteviaje);

		// When
		Clienteviaje clienteviajeFound = clienteviajeService.findById(id);

		// Then
		assertEquals(clienteviaje.getId(),clienteviajeFound.getId());
	}

	@Test
	public void findAll() {
		// Given
		List<ClienteviajeEntity> clienteviajeEntitys = new ArrayList<ClienteviajeEntity>();
		ClienteviajeEntity clienteviajeEntity1 = clienteviajeEntityFactoryForTest.newClienteviajeEntity();
		clienteviajeEntitys.add(clienteviajeEntity1);
		ClienteviajeEntity clienteviajeEntity2 = clienteviajeEntityFactoryForTest.newClienteviajeEntity();
		clienteviajeEntitys.add(clienteviajeEntity2);
		when(clienteviajePersistenceJPA.loadAll()).thenReturn(clienteviajeEntitys);
		
		Clienteviaje clienteviaje1 = clienteviajeFactoryForTest.newClienteviaje();
		when(clienteviajeServiceMapper.mapClienteviajeEntityToClienteviaje(clienteviajeEntity1)).thenReturn(clienteviaje1);
		Clienteviaje clienteviaje2 = clienteviajeFactoryForTest.newClienteviaje();
		when(clienteviajeServiceMapper.mapClienteviajeEntityToClienteviaje(clienteviajeEntity2)).thenReturn(clienteviaje2);

		// When
		List<Clienteviaje> clienteviajesFounds = clienteviajeService.findAll();

		// Then
		assertTrue(clienteviaje1 == clienteviajesFounds.get(0));
		assertTrue(clienteviaje2 == clienteviajesFounds.get(1));
	}

	@Test
	public void create() {
		// Given
		Clienteviaje clienteviaje = clienteviajeFactoryForTest.newClienteviaje();

		ClienteviajeEntity clienteviajeEntity = clienteviajeEntityFactoryForTest.newClienteviajeEntity();
		when(clienteviajePersistenceJPA.load(clienteviaje.getId())).thenReturn(null);
		
		clienteviajeEntity = new ClienteviajeEntity();
		clienteviajeServiceMapper.mapClienteviajeToClienteviajeEntity(clienteviaje, clienteviajeEntity);
		ClienteviajeEntity clienteviajeEntitySaved = clienteviajePersistenceJPA.save(clienteviajeEntity);
		
		Clienteviaje clienteviajeSaved = clienteviajeFactoryForTest.newClienteviaje();
		when(clienteviajeServiceMapper.mapClienteviajeEntityToClienteviaje(clienteviajeEntitySaved)).thenReturn(clienteviajeSaved);

		// When
		Clienteviaje clienteviajeResult = clienteviajeService.create(clienteviaje);

		// Then
		assertTrue(clienteviajeResult == clienteviajeSaved);
	}

	@Test
	public void createKOExists() {
		// Given
		Clienteviaje clienteviaje = clienteviajeFactoryForTest.newClienteviaje();

		ClienteviajeEntity clienteviajeEntity = clienteviajeEntityFactoryForTest.newClienteviajeEntity();
		when(clienteviajePersistenceJPA.load(clienteviaje.getId())).thenReturn(clienteviajeEntity);

		// When
		Exception exception = null;
		try {
			clienteviajeService.create(clienteviaje);
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
		Clienteviaje clienteviaje = clienteviajeFactoryForTest.newClienteviaje();

		ClienteviajeEntity clienteviajeEntity = clienteviajeEntityFactoryForTest.newClienteviajeEntity();
		when(clienteviajePersistenceJPA.load(clienteviaje.getId())).thenReturn(clienteviajeEntity);
		
		ClienteviajeEntity clienteviajeEntitySaved = clienteviajeEntityFactoryForTest.newClienteviajeEntity();
		when(clienteviajePersistenceJPA.save(clienteviajeEntity)).thenReturn(clienteviajeEntitySaved);
		
		Clienteviaje clienteviajeSaved = clienteviajeFactoryForTest.newClienteviaje();
		when(clienteviajeServiceMapper.mapClienteviajeEntityToClienteviaje(clienteviajeEntitySaved)).thenReturn(clienteviajeSaved);

		// When
		Clienteviaje clienteviajeResult = clienteviajeService.update(clienteviaje);

		// Then
		verify(clienteviajeServiceMapper).mapClienteviajeToClienteviajeEntity(clienteviaje, clienteviajeEntity);
		assertTrue(clienteviajeResult == clienteviajeSaved);
	}

	@Test
	public void delete() {
		// Given
		Long id = mockValues.nextLong();

		// When
		clienteviajeService.delete(id);

		// Then
		verify(clienteviajePersistenceJPA).delete(id);
		
	}

}
