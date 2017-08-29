/*
 * Created on 28 ago 2017 ( Time 17:51:20 )
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
import com.tuin.bean.Vehiculoservicio;
import com.tuin.bean.jpa.VehiculoservicioEntity;
import com.tuin.bean.jpa.ServicioEntity;
import com.tuin.bean.jpa.VehiculoEntity;
import com.tuin.test.MockValues;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class VehiculoservicioServiceMapperTest {

	private VehiculoservicioServiceMapper vehiculoservicioServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();

	private MockValues mockValues = new MockValues();
	
	
	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Before
	public void before() {
		vehiculoservicioServiceMapper = new VehiculoservicioServiceMapper();
		vehiculoservicioServiceMapper.setModelMapper(modelMapper);
	}
	
	/**
	 * Mapping from 'VehiculoservicioEntity' to 'Vehiculoservicio'
	 * @param vehiculoservicioEntity
	 */
	@Test
	public void testMapVehiculoservicioEntityToVehiculoservicio() {
		// Given
		VehiculoservicioEntity vehiculoservicioEntity = new VehiculoservicioEntity();
		vehiculoservicioEntity.setServicio(new ServicioEntity());
		vehiculoservicioEntity.getServicio().setId(mockValues.nextLong());
		vehiculoservicioEntity.setVehiculo(new VehiculoEntity());
		vehiculoservicioEntity.getVehiculo().setId(mockValues.nextLong());
		
		// When
		Vehiculoservicio vehiculoservicio = vehiculoservicioServiceMapper.mapVehiculoservicioEntityToVehiculoservicio(vehiculoservicioEntity);
		
		// Then
		assertEquals(vehiculoservicioEntity.getServicio().getId(), vehiculoservicio.getServicioid());
		assertEquals(vehiculoservicioEntity.getVehiculo().getId(), vehiculoservicio.getVehiculoid());
	}
	
	/**
	 * Test : Mapping from 'Vehiculoservicio' to 'VehiculoservicioEntity'
	 */
	@Test
	public void testMapVehiculoservicioToVehiculoservicioEntity() {
		// Given
		Vehiculoservicio vehiculoservicio = new Vehiculoservicio();
		vehiculoservicio.setServicioid(mockValues.nextLong());
		vehiculoservicio.setVehiculoid(mockValues.nextLong());

		VehiculoservicioEntity vehiculoservicioEntity = new VehiculoservicioEntity();
		
		// When
		vehiculoservicioServiceMapper.mapVehiculoservicioToVehiculoservicioEntity(vehiculoservicio, vehiculoservicioEntity);
		
		// Then
		assertEquals(vehiculoservicio.getServicioid(), vehiculoservicioEntity.getServicio().getId());
		assertEquals(vehiculoservicio.getVehiculoid(), vehiculoservicioEntity.getVehiculo().getId());
	}

}