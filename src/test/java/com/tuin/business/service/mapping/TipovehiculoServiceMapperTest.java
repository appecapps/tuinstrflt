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
import com.tuin.bean.Tipovehiculo;
import com.tuin.bean.jpa.TipovehiculoEntity;
import com.tuin.test.MockValues;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class TipovehiculoServiceMapperTest {

	private TipovehiculoServiceMapper tipovehiculoServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();

	private MockValues mockValues = new MockValues();
	
	
	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Before
	public void before() {
		tipovehiculoServiceMapper = new TipovehiculoServiceMapper();
		tipovehiculoServiceMapper.setModelMapper(modelMapper);
	}
	
	/**
	 * Mapping from 'TipovehiculoEntity' to 'Tipovehiculo'
	 * @param tipovehiculoEntity
	 */
	@Test
	public void testMapTipovehiculoEntityToTipovehiculo() {
		// Given
		TipovehiculoEntity tipovehiculoEntity = new TipovehiculoEntity();
		tipovehiculoEntity.setNombre(mockValues.nextString(45));
		
		// When
		Tipovehiculo tipovehiculo = tipovehiculoServiceMapper.mapTipovehiculoEntityToTipovehiculo(tipovehiculoEntity);
		
		// Then
		assertEquals(tipovehiculoEntity.getNombre(), tipovehiculo.getNombre());
	}
	
	/**
	 * Test : Mapping from 'Tipovehiculo' to 'TipovehiculoEntity'
	 */
	@Test
	public void testMapTipovehiculoToTipovehiculoEntity() {
		// Given
		Tipovehiculo tipovehiculo = new Tipovehiculo();
		tipovehiculo.setNombre(mockValues.nextString(45));

		TipovehiculoEntity tipovehiculoEntity = new TipovehiculoEntity();
		
		// When
		tipovehiculoServiceMapper.mapTipovehiculoToTipovehiculoEntity(tipovehiculo, tipovehiculoEntity);
		
		// Then
		assertEquals(tipovehiculo.getNombre(), tipovehiculoEntity.getNombre());
	}

}