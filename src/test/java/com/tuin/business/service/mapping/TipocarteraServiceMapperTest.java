/*
 * Created on 28 ago 2017 ( Time 17:51:19 )
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
import com.tuin.bean.Tipocartera;
import com.tuin.bean.jpa.TipocarteraEntity;
import com.tuin.test.MockValues;

/**
 * Test : Mapping between entity beans and display beans.
 */
public class TipocarteraServiceMapperTest {

	private TipocarteraServiceMapper tipocarteraServiceMapper;

	private static ModelMapper modelMapper = new ModelMapper();

	private MockValues mockValues = new MockValues();
	
	
	@BeforeClass
	public static void setUp() {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
	}
	
	@Before
	public void before() {
		tipocarteraServiceMapper = new TipocarteraServiceMapper();
		tipocarteraServiceMapper.setModelMapper(modelMapper);
	}
	
	/**
	 * Mapping from 'TipocarteraEntity' to 'Tipocartera'
	 * @param tipocarteraEntity
	 */
	@Test
	public void testMapTipocarteraEntityToTipocartera() {
		// Given
		TipocarteraEntity tipocarteraEntity = new TipocarteraEntity();
		tipocarteraEntity.setNombre(mockValues.nextString(45));
		
		// When
		Tipocartera tipocartera = tipocarteraServiceMapper.mapTipocarteraEntityToTipocartera(tipocarteraEntity);
		
		// Then
		assertEquals(tipocarteraEntity.getNombre(), tipocartera.getNombre());
	}
	
	/**
	 * Test : Mapping from 'Tipocartera' to 'TipocarteraEntity'
	 */
	@Test
	public void testMapTipocarteraToTipocarteraEntity() {
		// Given
		Tipocartera tipocartera = new Tipocartera();
		tipocartera.setNombre(mockValues.nextString(45));

		TipocarteraEntity tipocarteraEntity = new TipocarteraEntity();
		
		// When
		tipocarteraServiceMapper.mapTipocarteraToTipocarteraEntity(tipocartera, tipocarteraEntity);
		
		// Then
		assertEquals(tipocartera.getNombre(), tipocarteraEntity.getNombre());
	}

}