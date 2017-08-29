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
import com.tuin.bean.Clienteviajeservicio;
import com.tuin.bean.jpa.ClienteviajeservicioEntity;
import com.tuin.bean.jpa.ClienteviajeEntity;
import com.tuin.bean.jpa.VehiculoservicioEntity;
import com.tuin.test.MockValues;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class ClienteviajeservicioServiceMapperTest {

	private ClienteviajeservicioServiceMapper clienteviajeservicioServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();

	private MockValues mockValues = new MockValues();
	
	
	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Before
	public void before() {
		clienteviajeservicioServiceMapper = new ClienteviajeservicioServiceMapper();
		clienteviajeservicioServiceMapper.setModelMapper(modelMapper);
	}
	
	/**
	 * Mapping from 'ClienteviajeservicioEntity' to 'Clienteviajeservicio'
	 * @param clienteviajeservicioEntity
	 */
	@Test
	public void testMapClienteviajeservicioEntityToClienteviajeservicio() {
		// Given
		ClienteviajeservicioEntity clienteviajeservicioEntity = new ClienteviajeservicioEntity();
		clienteviajeservicioEntity.setCantidad(mockValues.nextInteger());
		clienteviajeservicioEntity.setCosto(mockValues.nextBigDecimal());
		clienteviajeservicioEntity.setClienteviaje(new ClienteviajeEntity());
		clienteviajeservicioEntity.getClienteviaje().setId(mockValues.nextLong());
		clienteviajeservicioEntity.setVehiculoservicio(new VehiculoservicioEntity());
		clienteviajeservicioEntity.getVehiculoservicio().setId(mockValues.nextLong());
		
		// When
		Clienteviajeservicio clienteviajeservicio = clienteviajeservicioServiceMapper.mapClienteviajeservicioEntityToClienteviajeservicio(clienteviajeservicioEntity);
		
		// Then
		assertEquals(clienteviajeservicioEntity.getCantidad(), clienteviajeservicio.getCantidad());
		assertEquals(clienteviajeservicioEntity.getCosto(), clienteviajeservicio.getCosto());
		assertEquals(clienteviajeservicioEntity.getClienteviaje().getId(), clienteviajeservicio.getClienteviajeid());
		assertEquals(clienteviajeservicioEntity.getVehiculoservicio().getId(), clienteviajeservicio.getVehiculoservicioid());
	}
	
	/**
	 * Test : Mapping from 'Clienteviajeservicio' to 'ClienteviajeservicioEntity'
	 */
	@Test
	public void testMapClienteviajeservicioToClienteviajeservicioEntity() {
		// Given
		Clienteviajeservicio clienteviajeservicio = new Clienteviajeservicio();
		clienteviajeservicio.setCantidad(mockValues.nextInteger());
		clienteviajeservicio.setCosto(mockValues.nextBigDecimal());
		clienteviajeservicio.setClienteviajeid(mockValues.nextLong());
		clienteviajeservicio.setVehiculoservicioid(mockValues.nextLong());

		ClienteviajeservicioEntity clienteviajeservicioEntity = new ClienteviajeservicioEntity();
		
		// When
		clienteviajeservicioServiceMapper.mapClienteviajeservicioToClienteviajeservicioEntity(clienteviajeservicio, clienteviajeservicioEntity);
		
		// Then
		assertEquals(clienteviajeservicio.getCantidad(), clienteviajeservicioEntity.getCantidad());
		assertEquals(clienteviajeservicio.getCosto(), clienteviajeservicioEntity.getCosto());
		assertEquals(clienteviajeservicio.getClienteviajeid(), clienteviajeservicioEntity.getClienteviaje().getId());
		assertEquals(clienteviajeservicio.getVehiculoservicioid(), clienteviajeservicioEntity.getVehiculoservicio().getId());
	}

}