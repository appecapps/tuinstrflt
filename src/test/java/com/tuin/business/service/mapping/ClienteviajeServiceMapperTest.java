/*
 * Created on 28 ago 2017 ( Time 17:51:13 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.tuin.business.service.mapping;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import com.tuin.bean.Clienteviaje;
import com.tuin.bean.jpa.ClienteviajeEntity;
import com.tuin.bean.jpa.ViajeEntity;
import com.tuin.bean.jpa.ClienteEntity;
import com.tuin.bean.jpa.EstadoEntity;
import com.tuin.test.MockValues;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class ClienteviajeServiceMapperTest {

	private ClienteviajeServiceMapper clienteviajeServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();

	private MockValues mockValues = new MockValues();
	
	
	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Before
	public void before() {
		clienteviajeServiceMapper = new ClienteviajeServiceMapper();
		clienteviajeServiceMapper.setModelMapper(modelMapper);
	}
	
	/**
	 * Mapping from 'ClienteviajeEntity' to 'Clienteviaje'
	 * @param clienteviajeEntity
	 */
	@Test
	public void testMapClienteviajeEntityToClienteviaje() {
		// Given
		ClienteviajeEntity clienteviajeEntity = new ClienteviajeEntity();
		clienteviajeEntity.setLatitud(mockValues.nextBigDecimal());
		clienteviajeEntity.setLongitud(mockValues.nextBigDecimal());
		clienteviajeEntity.setPasajeros(mockValues.nextInteger());
		clienteviajeEntity.setCosto(mockValues.nextBigDecimal());
		clienteviajeEntity.setViaje(new ViajeEntity());
		clienteviajeEntity.getViaje().setId(mockValues.nextLong());
		clienteviajeEntity.setCliente(new ClienteEntity());
		clienteviajeEntity.getCliente().setId(mockValues.nextLong());
		clienteviajeEntity.setEstado(new EstadoEntity());
		clienteviajeEntity.getEstado().setId(mockValues.nextLong());
		
		// When
		Clienteviaje clienteviaje = clienteviajeServiceMapper.mapClienteviajeEntityToClienteviaje(clienteviajeEntity);
		
		// Then
		assertEquals(clienteviajeEntity.getLatitud(), clienteviaje.getLatitud());
		assertEquals(clienteviajeEntity.getLongitud(), clienteviaje.getLongitud());
		assertEquals(clienteviajeEntity.getPasajeros(), clienteviaje.getPasajeros());
		assertEquals(clienteviajeEntity.getCosto(), clienteviaje.getCosto());
		assertEquals(clienteviajeEntity.getViaje().getId(), clienteviaje.getViajeid());
		assertEquals(clienteviajeEntity.getCliente().getId(), clienteviaje.getClienteid());
		assertEquals(clienteviajeEntity.getEstado().getId(), clienteviaje.getEstadoid());
	}
	
	/**
	 * Test : Mapping from 'Clienteviaje' to 'ClienteviajeEntity'
	 */
	@Test
	public void testMapClienteviajeToClienteviajeEntity() {
		// Given
		Clienteviaje clienteviaje = new Clienteviaje();
		clienteviaje.setLatitud(mockValues.nextBigDecimal());
		clienteviaje.setLongitud(mockValues.nextBigDecimal());
		clienteviaje.setPasajeros(mockValues.nextInteger());
		clienteviaje.setCosto(mockValues.nextBigDecimal());
		clienteviaje.setViajeid(mockValues.nextLong());
		clienteviaje.setClienteid(mockValues.nextLong());
		clienteviaje.setEstadoid(mockValues.nextLong());

		ClienteviajeEntity clienteviajeEntity = new ClienteviajeEntity();
		
		// When
		clienteviajeServiceMapper.mapClienteviajeToClienteviajeEntity(clienteviaje, clienteviajeEntity);
		
		// Then
		assertEquals(clienteviaje.getLatitud(), clienteviajeEntity.getLatitud());
		assertEquals(clienteviaje.getLongitud(), clienteviajeEntity.getLongitud());
		assertEquals(clienteviaje.getPasajeros(), clienteviajeEntity.getPasajeros());
		assertEquals(clienteviaje.getCosto(), clienteviajeEntity.getCosto());
		assertEquals(clienteviaje.getViajeid(), clienteviajeEntity.getViaje().getId());
		assertEquals(clienteviaje.getClienteid(), clienteviajeEntity.getCliente().getId());
		assertEquals(clienteviaje.getEstadoid(), clienteviajeEntity.getEstado().getId());
	}

}