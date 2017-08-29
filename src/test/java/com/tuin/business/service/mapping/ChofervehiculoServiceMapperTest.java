/*
 * Created on 28 ago 2017 ( Time 17:51:12 )
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
import com.tuin.bean.Chofervehiculo;
import com.tuin.bean.jpa.ChofervehiculoEntity;
import com.tuin.bean.jpa.ChoferEntity;
import com.tuin.bean.jpa.VehiculoEntity;
import com.tuin.test.MockValues;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class ChofervehiculoServiceMapperTest {

	private ChofervehiculoServiceMapper chofervehiculoServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();

	private MockValues mockValues = new MockValues();
	
	
	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Before
	public void before() {
		chofervehiculoServiceMapper = new ChofervehiculoServiceMapper();
		chofervehiculoServiceMapper.setModelMapper(modelMapper);
	}
	
	/**
	 * Mapping from 'ChofervehiculoEntity' to 'Chofervehiculo'
	 * @param chofervehiculoEntity
	 */
	@Test
	public void testMapChofervehiculoEntityToChofervehiculo() {
		// Given
		ChofervehiculoEntity chofervehiculoEntity = new ChofervehiculoEntity();
		chofervehiculoEntity.setPredeterminado(mockValues.nextByte());
		chofervehiculoEntity.setChofer(new ChoferEntity());
		chofervehiculoEntity.getChofer().setId(mockValues.nextLong());
		chofervehiculoEntity.setVehiculo(new VehiculoEntity());
		chofervehiculoEntity.getVehiculo().setId(mockValues.nextLong());
		
		// When
		Chofervehiculo chofervehiculo = chofervehiculoServiceMapper.mapChofervehiculoEntityToChofervehiculo(chofervehiculoEntity);
		
		// Then
		assertEquals(chofervehiculoEntity.getPredeterminado(), chofervehiculo.getPredeterminado());
		assertEquals(chofervehiculoEntity.getChofer().getId(), chofervehiculo.getChoferid());
		assertEquals(chofervehiculoEntity.getVehiculo().getId(), chofervehiculo.getVehiculoid());
	}
	
	/**
	 * Test : Mapping from 'Chofervehiculo' to 'ChofervehiculoEntity'
	 */
	@Test
	public void testMapChofervehiculoToChofervehiculoEntity() {
		// Given
		Chofervehiculo chofervehiculo = new Chofervehiculo();
		chofervehiculo.setPredeterminado(mockValues.nextByte());
		chofervehiculo.setChoferid(mockValues.nextLong());
		chofervehiculo.setVehiculoid(mockValues.nextLong());

		ChofervehiculoEntity chofervehiculoEntity = new ChofervehiculoEntity();
		
		// When
		chofervehiculoServiceMapper.mapChofervehiculoToChofervehiculoEntity(chofervehiculo, chofervehiculoEntity);
		
		// Then
		assertEquals(chofervehiculo.getPredeterminado(), chofervehiculoEntity.getPredeterminado());
		assertEquals(chofervehiculo.getChoferid(), chofervehiculoEntity.getChofer().getId());
		assertEquals(chofervehiculo.getVehiculoid(), chofervehiculoEntity.getVehiculo().getId());
	}

}